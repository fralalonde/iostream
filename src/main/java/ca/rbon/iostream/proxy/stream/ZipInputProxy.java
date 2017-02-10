package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class ZipInputProxy<T> extends ZipInputStream implements Resource<T> {
    
    final ChainClose<T> closer;
    
    public ZipInputProxy(ChainClose<T> cl, InputStream is) {
        super(is);
        cl.add(is);
        closer = cl;
    }
    
    public ZipInputProxy(ChainClose<T> cl, InputStream is, Charset cs) {
        super(is, cs);
        cl.add(is);
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
