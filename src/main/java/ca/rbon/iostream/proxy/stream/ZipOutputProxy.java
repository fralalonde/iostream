package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipOutputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class ZipOutputProxy<T> extends ZipOutputStream implements Resource<T> {
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public ZipOutputProxy(Resource<T> t, ChainClose cl, OutputStream os) {
        super(os);
        holder = t;
        cl.add(os);
        closer = cl;
    }
    
    public ZipOutputProxy(Resource<T> t, ChainClose cl, OutputStream os, Charset cs) {
        super(os, cs);
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

    
}
