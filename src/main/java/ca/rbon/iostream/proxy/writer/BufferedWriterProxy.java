package ca.rbon.iostream.proxy.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class BufferedWriterProxy<T> extends BufferedWriter implements Resource<T> {
    
    final ChainClose<T> closer;
    
    public BufferedWriterProxy(ChainClose<T> cl, Writer wr) throws IOException {
        super(wr);        
        cl.add(wr);
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
