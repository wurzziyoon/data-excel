package its.common.service.impl.excel;

import com.alibaba.excel.context.AnalysisContext;

public interface ExcelDataReadProxy<T> {
   default T afterRowRead(T obj, AnalysisContext analysisContext){
       return obj;
   };
}
