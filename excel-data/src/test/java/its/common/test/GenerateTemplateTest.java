package its.common.test;

import its.common.resource.ResourceLoader;
import its.common.service.impl.excel.ExcelUtil;
import its.common.test.dto.EmployeeCustomMultiHeaderDTO;
import its.common.test.dto.EmployeeCustomHeaderDTO;
import its.common.test.dto.EmployeeDTO;
import org.junit.Test;

import java.io.IOException;

public class GenerateTemplateTest {
    @Test
    public void generateTemplate() throws IOException {
        System.out.println(new ExcelUtil().downloadTemplate(ResourceLoader.tempResource("TestExcel",".xlsx"), EmployeeDTO.class)
                .getFile().getAbsolutePath());
    }

    @Test
    public void generateCustomHeaderTemplate() throws IOException {
        System.out.println(new ExcelUtil().downloadTemplate(ResourceLoader.tempResource("TestExcel",".xlsx"), EmployeeCustomHeaderDTO.class)
                .getFile().getAbsolutePath());
    }

    @Test
    public void generateCustomMultiHeaderTemplate() throws IOException {
        System.out.println(new ExcelUtil().downloadTemplate(ResourceLoader.tempResource("TestExcel",".xlsx"), EmployeeCustomMultiHeaderDTO.class)
                .getFile().getAbsolutePath());
    }
}
