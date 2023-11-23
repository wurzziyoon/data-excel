package its.common.service.impl.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import its.common.exception.ConstraintViolationsInfo;
import its.common.exception.DataValidateException;
import its.common.service.impl.excel.annotation.ExcelOptions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import red.zyc.desensitization.Sensitive;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/17 16:38
 */
@Slf4j
public class ExcelReadListener<T> implements ReadListener {
    static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private List<ConstraintViolationsInfo> infos = null;

    @SneakyThrows
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        ExcelOptions excelOptions = o.getClass().getDeclaredAnnotation(ExcelOptions.class);
        if(infos==null){
            infos=new ArrayList<>();
        }
        //输入有效性校验
        var result = validator.validate(o);
        if (!result.isEmpty()) {
            ConstraintViolationsInfo info = new ConstraintViolationsInfo(o, analysisContext.readRowHolder().getRowIndex(), result);
            infos.add(info);
            if(excelOptions==null || (excelOptions!=null && !excelOptions.analyzeAllRowErrors())){
                throw new DataValidateException(infos);
            }

        }

        if (o instanceof ExcelDataReadProxy) {
            o = (ExcelDataReadProxy) ((ExcelDataReadProxy) o).afterRowRead(o, analysisContext);
        }
        //脱敏
        if (excelOptions != null && !excelOptions.exportSensitiveData()) {
            analysisContext.readRowHolder().setCurrentRowAnalysisResult(Sensitive.desensitize(o));
        }
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        throw exception;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (infos != null && infos.size() > 0) {
            throw new DataValidateException(infos);
        }
    }


}
