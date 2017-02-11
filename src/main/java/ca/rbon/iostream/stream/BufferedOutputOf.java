package ca.rbon.iostream.stream;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class BufferedOutputOf<T> extends BufferedOutputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public BufferedOutputOf(ClosingResource<T> cl, OutputStream os) throws IOException {
        super(os);
        closer = cl;
    }
    
    public BufferedOutputOf(ClosingResource<T> cl, OutputStream os, int bufferSize) throws IOException {
        super(os, bufferSize);
        closer = cl;
    }
    
    @Override
    public void close() throws IOException {
        super.close();
        closer.close();
    }
    
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
