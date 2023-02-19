package its.qisda.service;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface Writeable {

    <T extends Object> void writeData(Resource resource, List<T> data, Class<T> clazz);

    <T extends Object> void generateTemplate(File file, Class<T> clazz) throws IOException;
}
