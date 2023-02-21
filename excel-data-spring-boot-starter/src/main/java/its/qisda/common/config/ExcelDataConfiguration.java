package its.qisda.common.config;

import its.qisda.common.service.impl.excel.ExcelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ExcelDataConfiguration {
    @Bean
    public ExcelService getExcelService(){
        return new ExcelService();
    }
}
