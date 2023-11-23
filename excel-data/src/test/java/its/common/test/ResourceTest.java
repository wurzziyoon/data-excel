package its.common.test;

import its.common.resource.ResourceLoader;
import its.common.service.impl.excel.ExcelUtil;
import its.common.test.dto.EmployeeDTO;
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
        Resource resource= ResourceLoader.load("file:///D:\\WebSite\\Excel.xlsx");
        var obj=new ExcelUtil();
        var excelResult=obj.getData(resource, EmployeeDTO.class);
        System.out.println(excelResult);
    }

    @Test
    public void readProjectResource() throws IOException {
        Resource resource= ResourceLoader.load("classpath:Excel.xlsx");
        var obj=new ExcelUtil();
        var excelResult=obj.getData(resource, EmployeeDTO.class);
        System.out.println(excelResult);
    }

}
