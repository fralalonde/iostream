package ca.rbon.iostream.proxy.stream;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class BufferedOutputProxy<T> extends BufferedOutputStream implements Resource<T> {
    
    final ChainClose<T> closer;
    
    public BufferedOutputProxy(ChainClose<T> cl, OutputStream os) throws IOException {
        super(os);
        cl.add(os);
        closer = cl;
    }
    
    public BufferedOutputProxy(Resource<T> t, ChainClose<T> cl, OutputStream os, int bufferSize) throws IOException {
        super(os, bufferSize);
        cl.add(os);
        closer = cl;
    }
    
    @Override
    public void close() throws IOException {
        closer.close();
    }
    
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
