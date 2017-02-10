package ca.rbon.iostream.proxy.writer;

import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class UnbufferedWriterProxy<T> extends Writer implements Resource<T> {
    
    final Writer delegate;
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public UnbufferedWriterProxy(Resource<T> t, ChainClose cl, Writer os) throws IOException {
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
    public void write(char[] cbuf, int off, int len) throws IOException {
        delegate.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        delegate.flush();
    }

}
