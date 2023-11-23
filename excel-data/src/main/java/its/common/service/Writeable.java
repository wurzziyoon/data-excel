package its.common.service;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Writeable {

    <T extends Object> Resource exportExcel(Resource resource, List<T> data, Class<T> clazz) throws IOException;

    <T extends Object> Resource downloadTemplate(Resource file, Class<T> clazz) throws IOException;

}
