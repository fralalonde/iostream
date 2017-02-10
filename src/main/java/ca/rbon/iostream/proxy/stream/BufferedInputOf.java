package ca.rbon.iostream.proxy.stream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class BufferedInputOf<T> extends BufferedInputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public BufferedInputOf(ClosingResource<T> cl, InputStream is) throws IOException {
        super(is);        
        cl.add(is);
        closer = cl;
    }
    
    public BufferedInputOf(Resource<T> t, ClosingResource<T> cl, InputStream is, int bufferSize) throws IOException {
        super(is, bufferSize);        
        cl.add(is);
        closer = cl;
    }
    
    public void close() throws IOException {
        closer.close();
    }
    
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
