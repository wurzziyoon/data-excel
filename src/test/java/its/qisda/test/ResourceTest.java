package its.qisda.test;

import its.qisda.service.impl.excel.ExcelService;
import its.qisda.service.impl.resource.ResourceLoader;
import its.qisda.test.dto.EmployeeDTO;
import its.qisda.test.dto.EmployeeWithProxyDTOExcel;
import org.junit.Test;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/17 15:44
 */
public class ResourceTest {

    @Test
    public void readFileResource() throws IOException {
        Resource resource= ResourceLoader.load("file:///D:\\WebSite\\test.xlsx");
        var obj=new ExcelService();
        var excelResult=obj.readData(resource, EmployeeDTO.class);
        System.out.println(excelResult);
    }

    @Test
    public void readProjectResource() throws IOException {
        Resource resource= ResourceLoader.load("classpath:test.xlsx");
        var obj=new ExcelService();
        var excelResult=obj.readData(resource, EmployeeDTO.class);
        System.out.println(excelResult);
    }

    @Test
    public void readProjectResource2() throws IOException {
        Resource resource= ResourceLoader.load("classpath:test.xlsx");
        var obj=new ExcelService();
        var excelResult=obj.readData(resource, EmployeeWithProxyDTOExcel.class);
        System.out.println(excelResult);
    }

    @Test
    public void readWebResource() throws IOException {
        Resource resource= ResourceLoader.load("http://localhost:9999/test.xlsx");
        var obj=new ExcelService();
        var excelResult=obj.readData(resource, EmployeeDTO.class);
        System.out.println(excelResult);
    }
}
