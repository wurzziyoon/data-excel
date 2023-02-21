package its.qisda.common.test.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import its.qisda.common.service.impl.excel.annotation.ExcelOptions;
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
 * @date: 2023/2/20 9:17
 */
@Data
@ExcelOptions(sheetName = "员工信息")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCustomHeaderDTO {
    @NotNull
    @Size(min = 1,max = 50)
    @ChineseNameSensitive
    @ExcelProperty(value = "姓名",index = 0)
    @ColumnWidth(30)
    private String name;

    @Range(min = 18,max = 65)
    @ExcelProperty(value = "年龄",index = 1)
    @ColumnWidth(30)
    private int age;

    @EmailSensitive
    @ExcelProperty(value = "邮箱地址",index = 3)
    @ColumnWidth(50)
    private String email;

    @PhoneNumberSensitive
    @Size(min = 11,max = 11)
    @ExcelProperty(value = "手机号码",index = 2)
    @ColumnWidth(50)
    private String phoneNumber;
}
