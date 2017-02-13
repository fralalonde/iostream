package ca.rbon.iostream.writer;

import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>WriterOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class WriterOf<T> extends Writer implements Resource<T> {
    
    final Writer delegate;
    
    final ClosingResource<T> closer;
    
    /**
     * <p>Constructor for WriterOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param os a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public WriterOf(ClosingResource<T> cl, Writer os) throws IOException {
        delegate = os;
        closer = cl;
    }
    
    /** {@inheritDoc} */
    @Override
    public void close() throws IOException {
        closer.close();
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
