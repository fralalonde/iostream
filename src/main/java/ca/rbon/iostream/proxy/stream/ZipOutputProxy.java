package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipOutputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class ZipOutputProxy<T> extends ZipOutputStream implements Resource<T> {
    
    final ChainClose<T> closer;
    
    public ZipOutputProxy(ChainClose<T> cl, OutputStream os) {
        super(os);
        cl.add(os);
        closer = cl;
    }
    
    public ZipOutputProxy(ChainClose<T> cl, OutputStream os, Charset cs) {
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
