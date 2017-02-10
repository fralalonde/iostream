package ca.rbon.iostream.proxy.writer;

import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class WriterProxy<T> extends Writer implements Resource<T> {
    
    final Writer delegate;
    
    final ChainClose<T> closer;
    
    public WriterProxy(ChainClose<T> cl, Writer os) throws IOException {
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
    public void write(char[] cbuf, int off, int len) throws IOException {
        delegate.write(cbuf, off, len);
    }
    
    @Override
    public void flush() throws IOException {
        delegate.flush();
    }
    
}
