package its.qisda.service.impl.excel;

import com.alibaba.excel.EasyExcel;
import its.qisda.service.Readable;
import its.qisda.service.Writeable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @description: 基于Excel进行导入导出
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/17 15:15
 */
@Slf4j
public class ExcelService implements Readable, Writeable {
    static ThreadLocal<List> threadLocal = new ThreadLocal<>();

    @Override
    public <T extends Object> void writeData(Resource resource, List<T> data, Class<T> clazz) {
        if (resource != null && resource.isFile()) {

        }
    }

    @Override
    public <T extends Object> List<T> readData(Resource resource, Class<T> clazz) throws IOException {
        if (resource == null) {
            throw new IllegalArgumentException("Parameter [resource] Can Not Be Null");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("Parameter [clazz] Can Not Be Null");
        }


        if (resource instanceof UrlResource) {
            return EasyExcel.read(resource.getInputStream(), clazz, new NormalExcelReadListener()).doReadAllSync();
        }

        if (!resource.getFile().exists() || !resource.getFile().isFile()) {
            throw new FileNotFoundException(String.format("File(%s) Not Found!", resource.getURI()));
        }

        return EasyExcel.read(resource.getFile(), clazz, new NormalExcelReadListener()).doReadAllSync();


    }

    @Override
    public <T> void generateTemplate(File file, Class<T> clazz) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("Parameter [file] Can Not Be Null");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("Parameter [clazz] Can Not Be Null");
        }




        var fields=clazz.getDeclaredFields();



    }
}
