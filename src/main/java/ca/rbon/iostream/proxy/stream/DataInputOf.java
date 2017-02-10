package ca.rbon.iostream.proxy.stream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class DataInputOf<T> extends DataInputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public DataInputOf(ClosingResource<T> cl, InputStream is) throws IOException {
        super(is);
        cl.add(is);
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
