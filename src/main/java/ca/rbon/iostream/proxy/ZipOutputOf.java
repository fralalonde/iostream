package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipOutputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>ZipOutputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class ZipOutputOf<T> extends ZipOutputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    /**
     * <p>Constructor for ZipOutputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param os a {@link java.io.OutputStream} object.
     */
    public ZipOutputOf(ClosingResource<T> cl, OutputStream os) {
        super(os);
        closer = cl;
    }
    
    /**
     * <p>Constructor for ZipOutputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @param cs a {@link java.nio.charset.Charset} object.
     */
    public ZipOutputOf(ClosingResource<T> cl, OutputStream os, Charset cs) {
        super(os, cs);
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
