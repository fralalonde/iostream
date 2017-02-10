package ca.rbon.iostream.proxy.stream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class BufferedInputProxy<T> extends BufferedInputStream implements Resource<T> {
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public BufferedInputProxy(Resource<T> t, ChainClose cl, InputStream is) throws IOException {
        super(is);
        holder = t;
        cl.add(is);
        closer = cl;
    }
    
    public BufferedInputProxy(Resource<T> t, ChainClose cl, InputStream is, int bufferSize) throws IOException {
        super(is, bufferSize);
        holder = t;
        cl.add(is);
        closer = cl;
    }
    
    public void close() throws IOException {
        closer.close();
    }
    
    @Override
    public T getResource() {
        return holder.getResource();
    }
        
}
