package its.qisda.common.service;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Writeable {

    <T extends Object> Resource writeData(Resource resource, List<T> data, Class<T> clazz) throws IOException;

    <T extends Object> Resource generateImportTemplate(Resource file, Class<T> clazz) throws IOException;

}
