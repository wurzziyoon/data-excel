package its.qisda.common.test;

import its.qisda.common.resource.ResourceLoader;
import its.qisda.common.service.impl.excel.ExcelService;
import its.qisda.common.test.dto.EmployeeCustomMultiHeaderDTO;
import its.qisda.common.test.dto.EmployeeCustomHeaderDTO;
import its.qisda.common.test.dto.EmployeeDTO;
import org.junit.Test;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public class GenerateTemplateTest {
    @Test
    public void generateTemplate() throws IOException {
        System.out.println(new ExcelService().generateTemplate(ResourceLoader.tempResource("TestExcel",".xlsx"), EmployeeDTO.class)
                .getFile().getAbsolutePath());
    }

    @Test
    public void generateCustomHeaderTemplate() throws IOException {
        System.out.println(new ExcelService().generateTemplate(ResourceLoader.tempResource("TestExcel",".xlsx"), EmployeeCustomHeaderDTO.class)
                .getFile().getAbsolutePath());
    }

    @Test
    public void generateCustomMultiHeaderTemplate() throws IOException {
        System.out.println(new ExcelService().generateTemplate(ResourceLoader.tempResource("TestExcel",".xlsx"), EmployeeCustomMultiHeaderDTO.class)
                .getFile().getAbsolutePath());
    }
}
