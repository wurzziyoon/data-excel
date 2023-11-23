package its.common.config;

import its.common.service.impl.excel.ExcelUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ExcelDataConfiguration {
    @Bean
    public ExcelUtil getExcelService(){
        return new ExcelUtil();
    }
}
