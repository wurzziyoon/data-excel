package its.common.exception;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import freemarker.template.Template;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;
import freemarker.template.Configuration;


@Getter
/**
 * 数据输入有效性校验异常
 */
public class DataValidateException extends RuntimeException {

    private List<ConstraintViolationsInfo> infos;

    public DataValidateException(List<ConstraintViolationsInfo> infos){
        super("Data Validate Fail");
        this.infos=infos;
    }

    public String toString(HttpServletRequest request) throws IOException {
        String result="";
        if(this!=null){
            String local=request.getHeader("local");
            if(StrUtil.isEmpty(local)){
                local=request.getHeader("Accept-Language");
            }
            PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
            Resource resource=resolver.getResource("classpath:ValidationMessages_"+local.toLowerCase(Locale.ROOT)+".properties");
            if(!resource.exists()){
                resource=resolver.getResource("classpath:ValidationMessages.properties");
            }
            Properties props=new Properties();
            props.load(resource.getInputStream());
            String msgStr=props.get("excelDataInvalid").toString();
            String lineStr = props.get("line").toString();
            List<ConstraintViolationsInfo> errors = this.getInfos();
            List<String> detailErrors = new ArrayList<>();
            for (ConstraintViolationsInfo error : errors) {
                String fieldMsg = String.join(", ", error.getConstraintViolationsList().stream().map(t -> {
                    var violation = t.getConstraintDescriptor();
                    return t.getPropertyPath() + ": " + parseContent(props.get(violation.getAttributes().get("message").toString()).toString(), violation.getAttributes());
                }).collect(Collectors.toList()));
                detailErrors.add(String.format("%s%d: [%s]", lineStr, error.getRowIndex(), fieldMsg));
            }
            result=msgStr+"\r\n"+String.join("\r\n",detailErrors);
        }
        return result;
    }

    private String parseContent(String content, Map<String, Object> dataMap) {
        try {
            content=content.replace("#{","${");
            Configuration configuration=Configuration.getDefaultConfiguration();
            configuration.unsetCacheStorage();
            Template template = new Template(DateTime.now().toTimestamp().toString(), new StringReader(content), configuration);
            StringWriter stringWriter = new StringWriter();
            template.process(dataMap, stringWriter);
            content = stringWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Excel Data Validate Template Parse Fail");
        }
        return content;
    }

}
