package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.Resource;
import ca.rbon.iostream.resource.BaseResource;

/**
 * <p>InputStreamOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class InputStreamOf<T> extends InputStream implements Resource<T> {
    
    final InputStream delegate;
    
    final BaseResource<T> closer;
    
    /**
     * <p>Constructor for InputStreamOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param os a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    public InputStreamOf(BaseResource<T> cl, InputStream os) throws IOException {
        delegate = os;        
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
    
    /** {@inheritDoc} */
    @Override
    public int read() throws IOException {
        return delegate.read();
    }
    
}
