package its.common.resource;

import cn.hutool.core.io.FileUtil;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.Locale;

/**
 * @description: some desc
 * @author: Joseph.ZY.Hu
 * @email: joseph.zy.hu@qisda.com
 * @date: 2023/2/21 15:23
 */
public class TempFileResource implements Resource {
    private final File tmpFile;
    private final String PREFIX="temp:///";
    public TempFileResource(File file){
        this.tmpFile=file;
    }
    @Override
    public boolean exists() {
        return tmpFile.exists();
    }

    @Override
    public URL getURL() throws IOException {
        return new URL("temp:///"+this.getFile().getAbsolutePath());
    }

    @Override
    public URI getURI() throws IOException {
        return tmpFile.toURI();
    }

    @Override
    public File getFile() throws IOException {
        return this.tmpFile;
    }

    @Override
    public long contentLength() throws IOException {
        return FileUtil.size(this.tmpFile);
    }

    @Override
    public long lastModified() throws IOException {
        return this.tmpFile.lastModified();
    }

    @Override
    public Resource createRelative(String relativePath) throws IOException {
        if(relativePath.toLowerCase(Locale.ROOT).startsWith(PREFIX)) {
            return new TempFileResource(new File(relativePath.substring(PREFIX.length())));
        }
        return null;
    }

    @Override
    public String getFilename() {
        return this.tmpFile.getName();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        FileInputStream fi=new FileInputStream(this.tmpFile);

        return fi;
    }
}
