package ca.rbon.iostream.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class ObjectInputOf<T> extends ObjectInputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public ObjectInputOf(ClosingResource<T> cl, InputStream is) throws IOException {
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
