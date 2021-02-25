package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.function.BiConsumer;

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
     */
    public ReaderOf(Resource<T> cl, Reader os) {
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
    public int read(char[] cbuf, int off, int len) throws IOException {
        return delegate.read(cbuf, off, len);
    }

    /**
     * Use the Reader inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<ReaderOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }

}
