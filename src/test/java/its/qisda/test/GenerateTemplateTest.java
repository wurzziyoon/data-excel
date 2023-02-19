package its.qisda.test;

import its.qisda.service.impl.excel.ExcelService;
import its.qisda.test.dto.EmployeeDTO;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class GenerateTemplateTest {
    @Test
    public void generateTemplate() throws IOException {
        new ExcelService().generateTemplate(new File("D:\\test.xlsx"), EmployeeDTO.class);
    }
}
