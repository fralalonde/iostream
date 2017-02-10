package ca.rbon.iostream.proxy.writer;

import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class ReaderProxy<T> extends Reader implements Resource<T> {
    
    final Reader delegate;
    
    final ChainClose<T> closer;
    
    public ReaderProxy(ChainClose<T> cl, Reader os) throws IOException {
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
