package its.qisda.test.dto;

import com.alibaba.excel.context.AnalysisContext;
import its.qisda.service.impl.excel.ExcelDataReadProxy;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import red.zyc.desensitization.annotation.ChineseNameSensitive;
import red.zyc.desensitization.annotation.EmailSensitive;
import red.zyc.desensitization.annotation.PhoneNumberSensitive;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/17 16:43
 */
@Data
public  class EmployeeWithProxyDTOExcel implements ExcelDataReadProxy<EmployeeWithProxyDTOExcel> {
    @NotNull
    @Size(min = 1,max = 50)
    @ChineseNameSensitive
    private String name;

    @Range(min = 18,max = 65)
    private int age;

    @EmailSensitive
    private String email;

    @PhoneNumberSensitive
    @Size(min = 11,max = 11)
    private String phoneNumber;

    @Override
    public EmployeeWithProxyDTOExcel afterRead(EmployeeWithProxyDTOExcel o, AnalysisContext analysisContext){
        o.setAge(100);
        return o;
    }
}
