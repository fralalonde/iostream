package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.function.BiConsumer;

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
     */
    public WriterOf(Resource<T> cl, Writer os) {
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
    public T getInner() throws IOException {
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

    /**
     * Use the Writer inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<WriterOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }
}
