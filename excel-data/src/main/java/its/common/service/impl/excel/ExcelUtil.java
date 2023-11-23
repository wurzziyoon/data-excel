package its.common.service.impl.excel;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import its.common.service.impl.excel.annotation.ExcelOptions;
import its.common.util.HttpUtil;
import its.common.resource.ResourceExtension;
import its.common.resource.ResourceLoader;
import its.common.service.Readable;
import its.common.service.Writeable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import red.zyc.desensitization.Sensitive;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @description: 基于Excel对数据进行导入导出
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/17 15:15
 */
@Slf4j
public class ExcelUtil implements Readable, Writeable {
    /**
     * 将数据写入Excel
     * @param resourceUrl Excel资源路徑
     * @param data 数据集合
     * @param clazz 数据类型
     * @param <T>
     * @throws IOException
     */
    public <T extends Object> Resource exportExcel(@NotNull String resourceUrl,@NotNull List<T> data,@NotNull Class<T> clazz) throws IOException {
        data=desensitiveData(data,clazz);
        Resource resource=ResourceLoader.load(resourceUrl);
        getWriterSheetBuilder(resource, clazz).doWrite(data);
        return resource;
    }
    /**
     * 将数据写入Excel
     * @param resource Excel资源
     * @param data 数据集合
     * @param clazz 数据类型
     * @param <T>
     * @throws IOException
     */
    @Override
    public <T extends Object> Resource exportExcel(@NotNull  Resource resource,@NotNull List<T> data,@NotNull Class<T> clazz) throws IOException {
        data=desensitiveData(data,clazz);
        getWriterSheetBuilder(resource, clazz).doWrite(data);
        return resource;
    }

    /**
     * 将数据在资源文件中写入并在response中输出
     * @param response response
     * @param resourceUri 资源路径
     * @param data 数据集合
     * @param clazz 数据类型
     * @param fileName 提供下载的文件名
     * @param <T>
     * @throws IOException
     */
    public <T extends Object> void exportExcel(@NotNull HttpServletResponse response,@NotNull  String resourceUri
            ,@NotNull List<T> data,@NotNull Class<T> clazz,@NotNull String fileName) throws IOException {
        new ResourceExtension(response,exportExcel(ResourceLoader.load(resourceUri),data,clazz)).setFileName(fileName).transferToResponse();
    }

    private <T extends Object> List<T> desensitiveData(@NotNull List<T> data,@NotNull Class<T> clazz){
        ExcelOptions options= clazz.getAnnotation(ExcelOptions.class);
        if(options!=null && !options.exportSensitiveData()){
            List<T> newData=new ArrayList<>();
            for(int i=0;i<data.size();i++){
                newData.add(Sensitive.desensitize(data.get(i)));
            }
            data=newData;
        }
        return data;
    }

    private <T extends Object> String getSheetName(Class<T> clazz){
        ExcelOptions options = clazz.getAnnotation(ExcelOptions.class);
        String sheetName = clazz.getSimpleName();
        if (options != null && !StrUtil.isEmpty(options.sheetName())) {
            sheetName = options.sheetName();
        } else {
            String className = clazz.getSimpleName();
            if (className.toLowerCase(Locale.ROOT).endsWith("dto")) {
                sheetName = className.substring(0, className.length() - 3);
            }
        }
        return sheetName;
    }

    /**
     * 在内存中写入Excel后将文件输出到请求响应中
     * @param response 请求响应
     * @param data 数据集合
     * @param clazz 数据类型
     * @param fileName 浏览器中下载的文件名
     * @param <T>
     * @throws IOException
     */
    public <T extends Object> void exportExcel(@NotNull HttpServletResponse response, @NotNull List<T> data, @NotNull Class<T> clazz,@NotNull String fileName) throws IOException {
        data=desensitiveData(data,clazz);
        HttpUtil.writeDownloadFileHeader(response,fileName);
        EasyExcel.write().inMemory(true).file(response.getOutputStream()).charset(Charset.forName("UTF-8"))
                .head(clazz).sheet(getSheetName(clazz)).doWrite(data);
    }

    /**
     * 读取Excel中的数据
     * @param resource Excel资源
     * @param clazz 数据类型
     * @param <T>
     * @return 基于填写class的List
     * @throws IOException
     */
    @Override
    public <T extends Object> List<T> getData(@NotNull Resource resource,@NotNull Class<T> clazz) throws IOException {
        return getExcelReaderBuilder(resource, clazz).doReadAllSync();
    }

    /**
     * 从请求中的MultipartFile对象中读取Excel数据
     * @param file  MultipartFile对象
     * @param clazz 数据类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T extends Object> List<T> getData(@NotNull MultipartFile file, @NotNull Class<T> clazz) throws IOException {
        ExcelReaderBuilder readerBuilder= EasyExcel.read(file.getInputStream(),clazz,new ExcelReadListener()).charset(Charset.forName("UTF-8"));
        return readerBuilder.sheet(getSheetName(clazz)).doReadSync();
    }

    /**
     * 获取ExcelReaderBuilder
     * @param resource Excel资源
     * @param clazz 数据类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T extends Object> ExcelReaderBuilder getExcelReaderBuilder(@NotNull Resource resource,@NotNull Class<T> clazz) throws IOException {
        ExcelReaderBuilder builder = null;
        ExcelOptions options = clazz.getAnnotation(ExcelOptions.class);
        if (resource instanceof InputStreamResource) {
            builder = EasyExcel.read(resource.getInputStream(), clazz, new ExcelReadListener()).charset(Charset.forName("UTF-8"));
            if (options != null && !StrUtil.isEmpty(options.sheetName())) {
                builder.sheet(options.sheetName());
            }
            return builder;
        }

        if (!resource.getFile().exists() || !resource.getFile().isFile()) {
            throw new FileNotFoundException(String.format("File(%s) Not Found!", resource.getURI()));
        }
        builder = EasyExcel.read(resource.getFile(), clazz, new ExcelReadListener()).charset(Charset.forName("UTF-8"));
        if (options != null && !StrUtil.isEmpty(options.sheetName())) {
            builder.sheet(options.sheetName());
        }
        return builder;
    }

    /**
     * 对指定类生成Excel数据导入模板
     * @param resourceUrl Excel资源Url
     * @param clazz Excel中数据区的类型
     * @param <T>
     * @throws IOException
     */
    public <T extends Object> Resource downloadTemplate(@NotNull String resourceUrl,@NotNull Class<T> clazz) throws IOException {
        return this.exportExcel(resourceUrl,new ArrayList<T>(),clazz);
    }

    /**
     * 对指定类生成Excel数据导入模板
     * @param resource Excel资源
     * @param clazz Excel中数据区的类型
     * @param <T>
     * @throws IOException
     */
    @Override
    public <T extends Object> Resource downloadTemplate(@NotNull Resource resource,@NotNull Class<T> clazz) throws IOException {
        return this.exportExcel(resource,new ArrayList<T>(),clazz);
    }

    /***
     * 读取指定资源路径的Excel模板,生成导出模板后直接输出到响应中.
     * @param response 响应
     * @param resourcePath excel模板资源路径
     * @param clazz  Excel中数据区的类型
     * @param <T>
     * @throws IOException
     */
    public <T extends Object> void downloadTemplate(HttpServletResponse response,@NotNull String resourcePath,@NotNull Class<T> clazz) throws IOException {
        Resource tplResource = ResourceLoader.load(resourcePath);
        new ResourceExtension(response,tplResource).transferToResponse();
    }

    /**
     * 对指定类生成Excel数据导入模板并将结果在请求响应中输出
     * @param response 请求响应
     * @param clazz  数据类型
     * @param fileName 浏览器中下载的文件名
     * @param <T>
     * @throws IOException
     */
    public <T extends Object> void downloadTemplate(@NotNull HttpServletResponse response, @NotNull Class<T> clazz,@NotNull String fileName) throws IOException {
        this.exportExcel(response,new ArrayList<T>(),clazz,fileName);
    }

    /**
     * 获得ExcelWriterSheetBuilder
     * @param resource Excel资源
     * @param clazz Excel中数据区的类型
     * @param <T>
     * @return
     */
    public <T extends Object> ExcelWriterSheetBuilder getWriterSheetBuilder(@NotNull  Resource resource,@NotNull Class<T> clazz) throws IOException {
        ExcelWriterBuilder builder = EasyExcel.write(resource.getFile(), clazz).charset(Charset.forName("UTF-8"));
        ExcelWriterSheetBuilder sheetBuilder = null;

        sheetBuilder = builder.sheet(getSheetName(clazz));
        return sheetBuilder;
    }

}
