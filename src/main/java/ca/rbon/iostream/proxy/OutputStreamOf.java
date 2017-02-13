package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>OutputStreamOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class OutputStreamOf<T> extends OutputStream implements Resource<T> {
    
    final OutputStream delegate;
    
    final ClosingResource<T> closer;
    
    /**
     * <p>Constructor for OutputStreamOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    public OutputStreamOf(ClosingResource<T> cl, OutputStream os) throws IOException {
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
    public void write(int b) throws IOException {
        delegate.write(b);
    }
}
