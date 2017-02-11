package ca.rbon.iostream.stream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class ZipInputOf<T> extends ZipInputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public ZipInputOf(ClosingResource<T> cl, InputStream is) {
        super(is);
        closer = cl;
    }
    
    public ZipInputOf(ClosingResource<T> cl, InputStream is, Charset cs) {
        super(is, cs);
        closer = cl;
    }
    
    @Override
    public void close() throws IOException {
        super.close();
        closer.close();
    }
    
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}