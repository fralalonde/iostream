package ca.rbon.iostream.proxy.writer;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class UnbufferedReaderProxy<T> extends Reader implements Resource<T> {
    
    final Reader delegate;
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public UnbufferedReaderProxy(Resource<T> t, ChainClose cl, Reader os) throws IOException {
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
    public int read(char[] cbuf, int off, int len) throws IOException {
        return delegate.read(cbuf, off, len);
    }

}
