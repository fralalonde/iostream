package ca.rbon.iostream.stream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class BufferedInputOf<T> extends BufferedInputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public BufferedInputOf(ClosingResource<T> cl, InputStream is) throws IOException {
        super(is);        
        closer = cl;
    }
    
    public BufferedInputOf(ClosingResource<T> cl, InputStream is, int bufferSize) throws IOException {
        super(is, bufferSize);        
        closer = cl;
    }
    
    public void close() throws IOException {
        super.close();
        closer.close();
    }
    
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
