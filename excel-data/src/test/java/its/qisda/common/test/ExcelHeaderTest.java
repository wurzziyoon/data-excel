package its.qisda.common.test;

import its.qisda.common.resource.ResourceLoader;
import its.qisda.common.service.impl.excel.ExcelService;
import its.qisda.common.test.dto.EmployeeCustomHeaderDTO;
import its.qisda.common.test.dto.EmployeeCustomMultiHeaderDTO;

import org.junit.Test;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/20 9:20
 */
public class ExcelHeaderTest {
    @Test
    public void testSingleHeader() throws IOException {
        Resource resource= ResourceLoader.load("classpath:ExcelWithSingleHeader.xlsx");
        var obj=new ExcelService();
        var excelResult=obj.readData(resource, EmployeeCustomHeaderDTO.class);
        System.out.println(excelResult);
    }

    @Test
    public void testMultiHeader() throws IOException {
        Resource resource= ResourceLoader.load("classpath:ExcelWithMultiHeader.xlsx");
        var obj=new ExcelService();
        var excelResult=obj.readData(resource, EmployeeCustomMultiHeaderDTO.class);

        System.out.println(excelResult);
    }
}
