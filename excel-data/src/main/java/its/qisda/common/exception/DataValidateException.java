package its.qisda.common.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
/**
 * 数据输入有效性校验异常
 */
public class DataValidateException extends RuntimeException {
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
    private Set<ConstraintViolation<Object>> constraintViolationsSet;
    public DataValidateException(String msg,Object data,Set<ConstraintViolation<Object>> constraintViolationsSet,int row){
        super(msg);
        this.rowData =data;
        this.rowIndex=row;
        this.constraintViolationsSet=constraintViolationsSet;
    }
}
