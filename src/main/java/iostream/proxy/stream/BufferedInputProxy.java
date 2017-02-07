package iostream.proxy.stream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import iostream.Closer;
import iostream.ResourceHolder;

public class BufferedInputProxy<T> extends BufferedInputStream implements ResourceHolder<T> {
    
    final Closer closer;
    
    final ResourceHolder<T> holder;
    
    public BufferedInputProxy(ResourceHolder<T> t, Closer cl, InputStream is) throws IOException {
        super(is);
        holder = t;
        cl.add(is);
        closer = cl;
    }
    
    public BufferedInputProxy(ResourceHolder<T> t, Closer cl, InputStream is, int bufferSize) throws IOException {
        super(is, bufferSize);
        holder = t;
        cl.add(is);
        closer = cl;
    }
    
    public void close() throws IOException {
        closer.closeAll();
    }
    
    @Override
    public T getResource() {
        return holder.getResource();
    }
    
}
