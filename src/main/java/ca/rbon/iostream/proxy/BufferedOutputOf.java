package ca.rbon.iostream.proxy;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.Resource;
import ca.rbon.iostream.resource.BaseResource;

/**
 * <p>BufferedOutputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class BufferedOutputOf<T> extends BufferedOutputStream implements Resource<T> {
    
    final BaseResource<T> closer;
    
    /**
     * <p>Constructor for BufferedOutputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    public BufferedOutputOf(BaseResource<T> cl, OutputStream os) throws IOException {
        super(os);
        closer = cl;
    }
    
    /**
     * <p>Constructor for BufferedOutputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @param bufferSize a int.
     * @throws java.io.IOException if any.
     */
    public BufferedOutputOf(BaseResource<T> cl, OutputStream os, int bufferSize) throws IOException {
        super(os, bufferSize);
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
