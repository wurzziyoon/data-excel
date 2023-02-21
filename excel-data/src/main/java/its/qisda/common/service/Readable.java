package its.qisda.common.service;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface Readable {

    <T extends Object> List<T> readData(Resource resource, Class<T> clazz) throws IOException;

}
