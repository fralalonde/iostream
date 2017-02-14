package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

import ca.rbon.iostream.Resource;
import ca.rbon.iostream.resource.BaseResource;

/**
 * <p>ZipInputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class ZipInputOf<T> extends ZipInputStream implements Resource<T> {
    
    final BaseResource<T> closer;
    
    /**
     * <p>Constructor for ZipInputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param is a {@link java.io.InputStream} object.
     */
    public ZipInputOf(BaseResource<T> cl, InputStream is) {
        super(is);
        closer = cl;
    }
    
    /**
     * <p>Constructor for ZipInputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param is a {@link java.io.InputStream} object.
     * @param cs a {@link java.nio.charset.Charset} object.
     */
    public ZipInputOf(BaseResource<T> cl, InputStream is, Charset cs) {
        super(is, cs);
        closer = cl;
    }
    
    /** {@inheritDoc} */
    @Override
    public void close() throws IOException {
        super.close();
        closer.close();
    }
    
    /** {@inheritDoc} */
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
