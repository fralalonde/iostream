package wrap;

import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * ReaderOf class.
 * </p>
 *
 * @param <T> Resource type
 */
public class ReaderOf<T> extends Reader implements WrapperOf<T> {
    
    final Reader delegate;
    
    final Resource<T> closer;
    
    /**
     * <p>
     * Constructor for ReaderOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.Reader} object.
     * @throws java.io.IOException if any.
     */
    public ReaderOf(Resource<T> cl, Reader os) throws IOException {
        delegate = os;
        closer = cl;
    }
    
    /** {@inheritDoc} */
    @Override
    public void close() throws IOException {
        delegate.close();
    }
    
    /** {@inheritDoc} */
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
    /** {@inheritDoc} */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return delegate.read(cbuf, off, len);
    }
    
}
