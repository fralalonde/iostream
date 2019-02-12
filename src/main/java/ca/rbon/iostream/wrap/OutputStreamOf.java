package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * OutputStreamOf class.
 * </p>
 *
 * @param <T> The resource type
 */
public class OutputStreamOf<T> extends OutputStream implements WrapperOf<T> {

    final OutputStream delegate;

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for OutputStreamOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    public OutputStreamOf(Resource<T> cl, OutputStream os) throws IOException {
        delegate = os;
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

    /** {@inheritDoc} */
    @Override
    public void write(int b) throws IOException {
        delegate.write(b);
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }
}
