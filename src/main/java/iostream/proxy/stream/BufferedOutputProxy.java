package iostream.proxy.stream;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import iostream.Closer;
import iostream.ResourceHolder;

public class BufferedOutputProxy<T> extends BufferedOutputStream implements ResourceHolder<T> {
    
    final Closer closer;
    
    final ResourceHolder<T> holder;
    
    public BufferedOutputProxy(ResourceHolder<T> t, Closer cl, OutputStream os) throws IOException {
        super(os);
        holder = t;
        cl.add(os);
        closer = cl;
    }
    
    public BufferedOutputProxy(ResourceHolder<T> t, Closer cl, OutputStream os, int bufferSize) throws IOException {
        super(os, bufferSize);
        holder = t;
        cl.add(os);
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
