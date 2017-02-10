package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class UnbufferedOutputProxy<T> extends OutputStream implements Resource<T> {
    
    final OutputStream delegate;
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public UnbufferedOutputProxy(Resource<T> t, ChainClose cl, OutputStream os) throws IOException {
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
    public void write(int b) throws IOException {
        delegate.write(b);
    }
}
