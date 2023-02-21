package its.qisda.common.test;

import its.qisda.common.resource.ResourceLoader;
import its.qisda.common.service.impl.excel.ExcelService;
import its.qisda.common.test.dto.EmployeeCustomMultiHeaderDTO;
import its.qisda.common.test.dto.EmployeeCustomHeaderDTO;
import its.qisda.common.test.dto.EmployeeDTO;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/20 15:23
 */
public class ImportDataToExcelTest {
    @Test
    public void importDataToExcelWithGenerateNormalTemplate() throws IOException {
        List<EmployeeDTO> listData=new ArrayList<>();
        listData.add(new EmployeeDTO("张三",18,"848158@qq.com","15456666658", (byte) 1,null));
        listData.add(new EmployeeDTO("李四",99,"848158@qq.com","15456666658", (byte) 0,null));
        new ExcelService().writeData(ResourceLoader.load("file:///D:\\Excel1.xlsx"),listData,EmployeeDTO.class);
    }

    @Test
    public void importDataToExcelWithGenerateSingleHeaderTemplate() throws IOException {
        List<EmployeeCustomHeaderDTO> listData=new ArrayList<>();
        listData.add(new EmployeeCustomHeaderDTO("张三",18,"848158@qq.com","15456666658"));
        listData.add(new EmployeeCustomHeaderDTO("李四",99,"848158@qq.com","15456666658"));
        new ExcelService().writeData(ResourceLoader.load("file:///D:\\Excel2.xlsx"),listData,EmployeeCustomHeaderDTO.class);
    }

    @Test
    public void importDataToExcelWithGenerateMultiHeaderTemplate() throws IOException {
        List<EmployeeCustomMultiHeaderDTO> listData=new ArrayList<>();
        listData.add(new EmployeeCustomMultiHeaderDTO("张三",18,"848158@qq.com","15456666658"));
        listData.add(new EmployeeCustomMultiHeaderDTO("李四",99,"848158@qq.com","15456666658"));
        new ExcelService().writeData(ResourceLoader.load("file:///D:\\Excel3.xlsx"),listData,EmployeeCustomMultiHeaderDTO.class);
    }


}
