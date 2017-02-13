package ca.rbon.iostream.writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>BufferedReaderOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class BufferedReaderOf<T> extends BufferedReader implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    /**
     * <p>Constructor for BufferedReaderOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param r a {@link java.io.Reader} object.
     * @throws java.io.IOException if any.
     */
    public BufferedReaderOf(ClosingResource<T> cl, Reader r) throws IOException {
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
