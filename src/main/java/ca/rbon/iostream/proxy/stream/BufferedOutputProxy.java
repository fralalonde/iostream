package ca.rbon.iostream.proxy.stream;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class BufferedOutputProxy<T> extends BufferedOutputStream implements Resource<T> {
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public BufferedOutputProxy(Resource<T> t, ChainClose cl, OutputStream os) throws IOException {
        super(os);
        holder = t;
        cl.add(os);
        closer = cl;
    }
    
    public BufferedOutputProxy(Resource<T> t, ChainClose cl, OutputStream os, int bufferSize) throws IOException {
        super(os, bufferSize);
        holder = t;
        cl.add(os);
        closer = cl;
    }
    
    @Override
    public void close() throws IOException {
        closer.close();
    }
    
    @Override
    public T getResource() {
        return holder.getResource();
    }
    
}
