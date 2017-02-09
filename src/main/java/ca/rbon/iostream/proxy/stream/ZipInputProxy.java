package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class ZipInputProxy<T> extends ZipInputStream implements Resource<T> {
    
    final ChainClose closer;
    final Resource<T> holder;
    
    public ZipInputProxy(Resource<T> t, ChainClose cl, InputStream is) {
        super(is);
        holder = t;
        cl.add(is);
        closer = cl;
    }
    
    public ZipInputProxy(Resource<T> t, ChainClose cl, InputStream is, Charset cs) {
        super(is, cs);
        holder = t;
        cl.add(is);
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
