package ca.rbon.iostream.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.Resource;
import ca.rbon.iostream.resource.BaseResource;

/**
 * <p>BufferedReaderOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class BufferedReaderOf<T> extends BufferedReader implements Resource<T> {
    
    final BaseResource<T> closer;
    
    /**
     * <p>Constructor for BufferedReaderOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param r a {@link java.io.Reader} object.
     * @throws java.io.IOException if any.
     */
    public BufferedReaderOf(BaseResource<T> cl, Reader r) throws IOException {
        super(r);        
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
