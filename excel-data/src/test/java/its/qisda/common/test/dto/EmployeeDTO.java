package its.qisda.common.test.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import its.qisda.common.service.impl.excel.annotation.ExcelOptions;
import its.qisda.common.test.convert.GenderConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@ExcelOptions(sheetName = "员工信息")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
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

    @ExcelProperty(converter = GenderConverter.class)
    private byte gender;

    @ExcelIgnore
    private String remark;
}
