package its.qisda.common.util;

import cn.hutool.core.io.FileUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/21 21:17
 */
public class HttpUtil {
    public  static  void writeDownloadFileHeader(@NotNull HttpServletResponse response,String fileName) throws UnsupportedEncodingException {
        String headStr = "attachment; filename=" + java.net.URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", headStr);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }
}
