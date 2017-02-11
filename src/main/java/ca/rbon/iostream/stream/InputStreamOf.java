package ca.rbon.iostream.stream;

import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class InputStreamOf<T> extends InputStream implements Resource<T> {
    
    final InputStream delegate;
    
    final ClosingResource<T> closer;
    
    public InputStreamOf(ClosingResource<T> cl, InputStream os) throws IOException {
        delegate = os;        
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
    
    @Override
    public int read() throws IOException {
        return delegate.read();
    }
    
}
