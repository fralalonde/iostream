package ca.rbon.iostream.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class BufferedWriterOf<T> extends BufferedWriter implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public BufferedWriterOf(ClosingResource<T> cl, Writer wr) throws IOException {
        super(wr);        
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
