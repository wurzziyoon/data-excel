package its.common.test;

import its.common.resource.ResourceLoader;
import its.common.service.impl.excel.ExcelUtil;
import its.common.test.dto.EmployeeCustomHeaderDTO;
import its.common.test.dto.EmployeeCustomMultiHeaderDTO;

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
        var obj=new ExcelUtil();
        var excelResult=obj.getData(resource, EmployeeCustomHeaderDTO.class);
        System.out.println(excelResult);
    }
    @Test
    public void testMultiHeader() throws IOException {
        Resource resource= ResourceLoader.load("classpath:ExcelWithMultiHeader.xlsx");
        var obj=new ExcelUtil();
        var excelResult=obj.getData(resource, EmployeeCustomMultiHeaderDTO.class);

        System.out.println(excelResult);
    }
}
