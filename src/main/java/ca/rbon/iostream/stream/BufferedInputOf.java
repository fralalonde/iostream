package ca.rbon.iostream.stream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>BufferedInputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class BufferedInputOf<T> extends BufferedInputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    /**
     * <p>Constructor for BufferedInputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param is a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    public BufferedInputOf(ClosingResource<T> cl, InputStream is) throws IOException {
        super(is);        
        closer = cl;
    }
    
    /**
     * <p>Constructor for BufferedInputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param is a {@link java.io.InputStream} object.
     * @param bufferSize a int.
     * @throws java.io.IOException if any.
     */
    public BufferedInputOf(ClosingResource<T> cl, InputStream is, int bufferSize) throws IOException {
        super(is, bufferSize);        
        closer = cl;
    }
    
    /**
     * <p>close.</p>
     *
     * @throws java.io.IOException if any.
     */
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
