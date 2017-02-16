package ca.rbon.iostream.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>BufferedReaderOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class BufferedReaderOf<T> extends BufferedReader  {
    
    final Resource<T> closer;
    
    /**
     * <p>Constructor for BufferedReaderOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param r a {@link java.io.Reader} object.
     * @throws java.io.IOException if any.
     */
    public BufferedReaderOf(Resource<T> cl, Reader r) throws IOException {
        super(r);        
        closer = cl;
    }
        
    /** {@inheritDoc} */
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
