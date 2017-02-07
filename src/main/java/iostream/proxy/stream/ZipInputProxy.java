package iostream.proxy.stream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

import iostream.Closer;
import iostream.ResourceHolder;

public class ZipInputProxy<T> extends ZipInputStream implements ResourceHolder<T> {
    
    final Closer closer;
    final ResourceHolder<T> holder;
    
    public ZipInputProxy(ResourceHolder<T> t, Closer cl, InputStream is) {
        super(is);
        holder = t;
        cl.add(is);
        closer = cl;
    }
    
    public ZipInputProxy(ResourceHolder<T> t, Closer cl, InputStream is, Charset cs) {
        super(is, cs);
        holder = t;
        cl.add(is);
        closer = cl;
    }
    
    @Override
    public void close() throws IOException {
        closer.closeAll();
    }
    
    @Override
    public T getResource() {
        return holder.getResource();
    }
    
}
