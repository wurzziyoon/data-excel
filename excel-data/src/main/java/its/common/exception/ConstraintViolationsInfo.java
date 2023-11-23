package its.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/3/3 10:54
 */
@Data
@AllArgsConstructor
public class ConstraintViolationsInfo {
    /**
     *行数据
     */
    private Object rowData;
    /**
     * Excel中的行数
     */
    private int rowIndex;

    /**
     * 所有未校验通过的集合
     */
    private Set<ConstraintViolation<Object>> constraintViolationsList;
}
