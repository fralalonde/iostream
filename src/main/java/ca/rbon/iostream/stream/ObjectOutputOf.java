package ca.rbon.iostream.stream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>ObjectOutputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class ObjectOutputOf<T> extends ObjectOutputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    /**
     * <p>Constructor for ObjectOutputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    public ObjectOutputOf(ClosingResource<T> cl, OutputStream os) throws IOException {
        super(os);        
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
