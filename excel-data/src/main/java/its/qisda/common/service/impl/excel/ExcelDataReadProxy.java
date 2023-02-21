package its.qisda.common.service.impl.excel;

import com.alibaba.excel.context.AnalysisContext;

public interface ExcelDataReadProxy<T> {
   default T afterRead(T obj, AnalysisContext analysisContext){
       return obj;
   };
}
