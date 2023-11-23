package its.common.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/17 15:42
 */
public class ResourceLoader {
    private static final  PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
    public static Resource load(@NotNull String resourceUrl)
    {
        return resolver.getResource(resourceUrl);
    }
    public static Resource tempResource(String prefix, String suffix) throws IOException {
        File file=File.createTempFile(prefix,suffix);
        return new TempFileResource(file);
    }
}