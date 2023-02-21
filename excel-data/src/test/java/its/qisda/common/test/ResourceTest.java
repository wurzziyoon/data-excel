package its.qisda.common.test;

import its.qisda.common.resource.ResourceLoader;
import its.qisda.common.service.impl.excel.ExcelService;
import its.qisda.common.test.dto.EmployeeCustomHeaderDTO;
import its.qisda.common.test.dto.EmployeeDTO;
import org.junit.Test;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/17 15:44
 */
public class ResourceTest {

    @Test
    public void readFileResource() throws IOException {
        Resource resource= ResourceLoader.load("file:///D:\\WebSite\\Excel.xlsx");
        var obj=new ExcelService();
        var excelResult=obj.readData(resource, EmployeeDTO.class);
        System.out.println(excelResult);
    }

    @Test
    public void readProjectResource() throws IOException {
        Resource resource= ResourceLoader.load("classpath:Excel.xlsx");
        var obj=new ExcelService();
        var excelResult=obj.readData(resource, EmployeeDTO.class);
        System.out.println(excelResult);
    }

}
