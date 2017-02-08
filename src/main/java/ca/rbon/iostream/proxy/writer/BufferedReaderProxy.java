package ca.rbon.iostream.proxy.writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.Closer;
import ca.rbon.iostream.ResourceHolder;

public class BufferedReaderProxy<T> extends BufferedReader implements ResourceHolder<T> {
    
    final Closer closer;
    
    final ResourceHolder<T> holder;
    
    public BufferedReaderProxy(ResourceHolder<T> t, Closer cl, Reader r) throws IOException {
        super(r);
        holder = t;
        cl.add(r);
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
