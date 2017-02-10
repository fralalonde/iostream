package ca.rbon.iostream.proxy.stream;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class BufferedOutputOf<T> extends BufferedOutputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public BufferedOutputOf(ClosingResource<T> cl, OutputStream os) throws IOException {
        super(os);
        cl.add(os);
        closer = cl;
    }
    
    public BufferedOutputOf(Resource<T> t, ClosingResource<T> cl, OutputStream os, int bufferSize) throws IOException {
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
