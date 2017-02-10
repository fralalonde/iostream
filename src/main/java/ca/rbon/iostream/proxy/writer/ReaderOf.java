package ca.rbon.iostream.proxy.writer;

import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class ReaderOf<T> extends Reader implements Resource<T> {
    
    final Reader delegate;
    
    final ClosingResource<T> closer;
    
    public ReaderOf(ClosingResource<T> cl, Reader os) throws IOException {
        delegate = os;        
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
    
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return delegate.read(cbuf, off, len);
    }
    
}
