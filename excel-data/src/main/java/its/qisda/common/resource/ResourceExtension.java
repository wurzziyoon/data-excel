package its.qisda.common.resource;

import cn.hutool.core.io.FileUtil;
import its.qisda.common.util.HttpUtil;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/21 17:39
 */
public class ResourceExtension {
    private final HttpServletResponse response;
    private final Resource resource;
    private String fileName="file";

    public ResourceExtension(@NotNull  HttpServletResponse response,@NotNull Resource resource){
        this.response=response;
        this.resource=resource;
    }

    public ResourceExtension setFileName(@NotNull String fileName){
        this.fileName=fileName;
        return this;
    }

    public void transferToResponse() throws IOException {
        HttpUtil.writeDownloadFileHeader(response,fileName);
        response.getOutputStream().write(FileUtil.readBytes(resource.getFile()));
    }
}
