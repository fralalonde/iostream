package iostream.proxy.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import iostream.Closer;
import iostream.ResourceHolder;
import lombok.SneakyThrows;

public class PrintWriterProxy<T> extends PrintWriter implements ResourceHolder<T> {
    
    final Closer closer;
    
    final ResourceHolder<T> holder;
    
    public PrintWriterProxy(ResourceHolder<T> t, Closer cl, Writer w) {
        super(w);
        holder = t;
        cl.add(w);
        closer = cl;
    }
    
    public PrintWriterProxy(ResourceHolder<T> t, Closer cl, OutputStream os) {
        super(os);
        holder = t;
        cl.add(os);
        closer = cl;
    }
    
    public PrintWriterProxy(ResourceHolder<T> t, Closer cl, Writer w, boolean autoFlush) {
        super(w, autoFlush);
        holder = t;
        cl.add(w);
        closer = cl;
    }
    
    public PrintWriterProxy(ResourceHolder<T> t, Closer cl, OutputStream os, boolean autoFlush) {
        super(os, autoFlush);
        holder = t;
        cl.add(os);
        closer = cl;
    }
    
    @SneakyThrows(IOException.class)
    public void close() {
        closer.closeAll();
    }
    
    @Override
    public T getResource() {
        return holder.getResource();
    }
    
}
