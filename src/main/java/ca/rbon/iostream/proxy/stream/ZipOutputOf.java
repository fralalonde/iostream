package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipOutputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class ZipOutputOf<T> extends ZipOutputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public ZipOutputOf(ClosingResource<T> cl, OutputStream os) {
        super(os);
        cl.add(os);
        closer = cl;
    }
    
    public ZipOutputOf(ClosingResource<T> cl, OutputStream os, Charset cs) {
        super(os, cs);
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
    
}
