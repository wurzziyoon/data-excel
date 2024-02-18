package its.common.service.impl.excel.annotation;

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
     * 导出时是否包含脱敏数据
     * @return
     */
    boolean exportSensitiveData() default true;

    /**
     * 导入时是否包含脱敏数据
     * @return
     */
    boolean importSensitiveData() default false;

    /**
     * 数据有效性校验时是否显示所有行的错误.
     * 当为true时会校验整个sheet所有的数据,为false时当遇到错误时就抛出异常.
     * @return
     */
    boolean analyzeAllRowErrors() default  false;


}
