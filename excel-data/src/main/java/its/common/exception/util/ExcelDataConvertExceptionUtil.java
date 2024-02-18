package its.common.exception.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.exception.ExcelDataConvertException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 26/12/2023 下午 1:17
 */
public class ExcelDataConvertExceptionUtil {
    public static String  GetMessage(ExcelDataConvertException exception, HttpServletRequest request) throws IOException {
        String result="";
        if(exception!=null && request !=null){
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
            Field field=exception.getExcelContentProperty().getField();
            ExcelProperty excelProperty=field.getAnnotation(ExcelProperty.class);
            String[] excelPropertyValue=excelProperty.value();
            List<String> values=Arrays.stream(excelPropertyValue).filter(t->StrUtil.isNotEmpty(t)).collect(Collectors.toList());
            String fieldName= field.getName();
            if(values.size()>0){
                fieldName= values.get(values.size()-1);
            }
            String fieldMsg=  fieldName +": "+props.get("constraintViolationPattern").toString();
            String detailMsg=String.format("%s%d: [%s]", lineStr, exception.getRowIndex(), fieldMsg);
            result=msgStr+"\r\n"+detailMsg;
        }
        return result;
    }
}
