package its.qisda.service.impl.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/17 15:42
 */
public class ResourceLoader {
    private static final  PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
    public static Resource load(String resourceUrl)
    {
        return resolver.getResource(resourceUrl);
    }
}
