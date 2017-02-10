package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class ObjectInputProxy<T> extends ObjectInputStream implements Resource<T> {
    
    final ChainClose<T> closer;
    
    public ObjectInputProxy(ChainClose<T> cl, InputStream is) throws IOException {
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
