package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class UnbufferedInputProxy<T> extends InputStream implements Resource<T> {
    
    final InputStream delegate;
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public UnbufferedInputProxy(Resource<T> t, ChainClose cl, InputStream os) throws IOException {
        delegate = os;
        holder = t;
        cl.add(os);
        closer = cl;
    }
        
    @Override
    public void close() throws IOException {
        closer.close();
    }
    
    @Override
    public T getResource() {
        return holder.getResource();
    }
    
    @Override
    public Charset getEncoding() {
        return holder.getEncoding();
    }

    @Override
    public int read() throws IOException {
        return delegate.read();
    }

}
