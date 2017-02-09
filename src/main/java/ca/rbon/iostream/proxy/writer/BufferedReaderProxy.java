package ca.rbon.iostream.proxy.writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class BufferedReaderProxy<T> extends BufferedReader implements Resource<T> {
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public BufferedReaderProxy(Resource<T> t, ChainClose cl, Reader r) throws IOException {
        super(r);
        holder = t;
        cl.add(r);
        closer = cl;
    }
    
    public void close() throws IOException {
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
