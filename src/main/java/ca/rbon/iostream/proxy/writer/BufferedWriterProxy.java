package ca.rbon.iostream.proxy.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.Closer;
import ca.rbon.iostream.ResourceHolder;

public class BufferedWriterProxy<T> extends BufferedWriter implements ResourceHolder<T> {
    
    final Closer closer;
    
    final ResourceHolder<T> holder;
    
    public BufferedWriterProxy(ResourceHolder<T> t, Closer cl, Writer wr) throws IOException {
        super(wr);
        holder = t;
        cl.add(wr);
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
