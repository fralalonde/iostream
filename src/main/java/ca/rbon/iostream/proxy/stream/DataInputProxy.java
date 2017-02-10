package ca.rbon.iostream.proxy.stream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class DataInputProxy<T> extends DataInputStream implements Resource<T> {
    
    final ChainClose<T> closer;
    
    public DataInputProxy(ChainClose<T> cl, InputStream is) throws IOException {
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
