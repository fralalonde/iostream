package ca.rbon.iostream.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>ObjectInputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class ObjectInputOf<T> extends ObjectInputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    /**
     * <p>Constructor for ObjectInputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param is a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    public ObjectInputOf(ClosingResource<T> cl, InputStream is) throws IOException {
        super(is);
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
