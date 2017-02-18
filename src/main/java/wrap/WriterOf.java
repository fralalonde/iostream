package wrap;

import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * WriterOf class.
 * </p>
 *
 * @param <T> The underlying resource type
 */
public class WriterOf<T> extends Writer implements WrapperOf<T> {
    
    final Writer delegate;
    
    final Resource<T> closer;
    
    /**
     * <p>
     * Constructor for WriterOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public WriterOf(Resource<T> cl, Writer os) throws IOException {
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
    public void write(char[] cbuf, int off, int len) throws IOException {
        delegate.write(cbuf, off, len);
    }
    
    /** {@inheritDoc} */
    @Override
    public void flush() throws IOException {
        delegate.flush();
    }
    
}
