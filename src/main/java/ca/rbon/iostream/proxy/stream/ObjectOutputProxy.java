package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class ObjectOutputProxy<T> extends ObjectOutputStream implements Resource<T> {
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public ObjectOutputProxy(Resource<T> t, ChainClose cl, OutputStream os) throws IOException {
        super(os);
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
    
}
