package ca.rbon.iostream.proxy.writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class BufferedReaderProxy<T> extends BufferedReader implements Resource<T> {
    
    final ChainClose<T> closer;
    
    public BufferedReaderProxy(ChainClose<T> cl, Reader r) throws IOException {
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
