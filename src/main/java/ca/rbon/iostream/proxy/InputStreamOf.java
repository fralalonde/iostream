package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * InputStreamOf class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class InputStreamOf<T> extends InputStream  {
    
    final InputStream delegate;
    
    final Resource<T> closer;
    
    /**
     * <p>
     * Constructor for InputStreamOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    public InputStreamOf(Resource<T> cl, InputStream os) throws IOException {
        delegate = os;
        closer = cl;
    }
    
    /** {@inheritDoc} */
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
    /** {@inheritDoc} */
    @Override
    public int read() throws IOException {
        return delegate.read();
    }
    
}
