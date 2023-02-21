package its.qisda.common.service.impl.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import its.qisda.common.exception.DataValidateException;
import its.qisda.common.service.impl.excel.annotation.ExcelOptions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import red.zyc.desensitization.Sensitive;

import javax.validation.Validation;
import javax.validation.Validator;


/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/17 16:38
 */
@Slf4j
public class ExcelReadListener implements ReadListener {
    static  final Validator validator=Validation.buildDefaultValidatorFactory().getValidator();
    @SneakyThrows
    @Override
    public void invoke(Object o, AnalysisContext analysisContext){
        //输入有效性校验
       var result= validator.validate(o);
       if(!result.isEmpty()){
           throw new DataValidateException(result.toString(),o,result,analysisContext.readRowHolder().getRowIndex());
       }
        if(o instanceof ExcelDataReadProxy)
        {
            o=(ExcelDataReadProxy)((ExcelDataReadProxy) o).afterRead(o,analysisContext);
        }
       //脱敏
        ExcelOptions excelOptions=o.getClass().getDeclaredAnnotation(ExcelOptions.class);
        if(excelOptions != null && !excelOptions.readSensitiveData()) {
            analysisContext.readRowHolder().setCurrentRowAnalysisResult(Sensitive.desensitize(o));
        }
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception{
        throw exception;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
