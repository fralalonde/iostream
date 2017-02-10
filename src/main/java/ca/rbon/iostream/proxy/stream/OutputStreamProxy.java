package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class OutputStreamProxy<T> extends OutputStream implements Resource<T> {
    
    final OutputStream delegate;
    
    final ChainClose<T> closer;
    
    public OutputStreamProxy(ChainClose<T> cl, OutputStream os) throws IOException {
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
    public void write(int b) throws IOException {
        delegate.write(b);
    }
}
