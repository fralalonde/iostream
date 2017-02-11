package ca.rbon.iostream.stream;

import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class OutputStreamOf<T> extends OutputStream implements Resource<T> {
    
    final OutputStream delegate;
    
    final ClosingResource<T> closer;
    
    public OutputStreamOf(ClosingResource<T> cl, OutputStream os) throws IOException {
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
    public void write(int b) throws IOException {
        delegate.write(b);
    }
}
