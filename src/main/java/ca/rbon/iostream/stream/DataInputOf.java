package ca.rbon.iostream.stream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class DataInputOf<T> extends DataInputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public DataInputOf(ClosingResource<T> cl, InputStream is) throws IOException {
        super(is);
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
