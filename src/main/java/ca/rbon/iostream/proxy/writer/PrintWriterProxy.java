package ca.rbon.iostream.proxy.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;
import lombok.SneakyThrows;

public class PrintWriterProxy<T> extends PrintWriter implements Resource<T> {
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public PrintWriterProxy(Resource<T> t, ChainClose cl, Writer w) {
        super(w);
        holder = t;
        cl.add(w);
        closer = cl;
    }
    
    public PrintWriterProxy(Resource<T> t, ChainClose cl, OutputStream os) {
        super(os);
        holder = t;
        cl.add(os);
        closer = cl;
    }
    
    public PrintWriterProxy(Resource<T> t, ChainClose cl, Writer w, boolean autoFlush) {
        super(w, autoFlush);
        holder = t;
        cl.add(w);
        closer = cl;
    }
    
    public PrintWriterProxy(Resource<T> t, ChainClose cl, OutputStream os, boolean autoFlush) {
        super(os, autoFlush);
        holder = t;
        cl.add(os);
        closer = cl;
    }
    
    @SneakyThrows(IOException.class)
    public void close() {
        closer.close();
    }
    
    @Override
    public T getResource() {
        return holder.getResource();
    }
    
    @Override
    public Charset getEncoding() {
        return holder.getEncoding();
    }
    
}
