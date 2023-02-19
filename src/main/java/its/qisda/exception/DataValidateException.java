package its.qisda.exception;

import its.qisda.service.ValidateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
public class DataValidateException extends Exception {
    private Object cellData;
    private int rowIndex;
    private Set<ConstraintViolation<Object>> constraintViolationsSet;
    public DataValidateException(String msg,Object data,Set<ConstraintViolation<Object>> constraintViolationsSet,int row){
        super(msg);
        this.cellData=data;
        this.rowIndex=row;
        this.constraintViolationsSet=constraintViolationsSet;
    }
}
