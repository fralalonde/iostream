package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class ObjectOutputOf<T> extends ObjectOutputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public ObjectOutputOf(ClosingResource<T> cl, OutputStream os) throws IOException {
        super(os);        
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
