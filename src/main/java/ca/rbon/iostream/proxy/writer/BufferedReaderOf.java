package ca.rbon.iostream.proxy.writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class BufferedReaderOf<T> extends BufferedReader implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public BufferedReaderOf(ClosingResource<T> cl, Reader r) throws IOException {
        super(r);        
        cl.add(r);
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
