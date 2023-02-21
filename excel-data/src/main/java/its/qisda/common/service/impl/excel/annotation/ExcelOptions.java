package its.qisda.common.service.impl.excel.annotation;

import java.lang.annotation.*;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/20 13:04
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelOptions {
    /**
     * 指定sheet名
     * @return
     */
    String sheetName() default "";

    /**
     * 是否写入脱敏数据
     * @return
     */
    boolean writeSensitiveData() default false;

    /**
     * 是否读取脱敏数据
     * @return
     */
    boolean readSensitiveData() default false;
}
