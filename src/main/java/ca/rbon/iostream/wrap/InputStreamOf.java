package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * InputStreamOf class.
 * </p>
 *
 * @param <T> The resource type
 */
public class InputStreamOf<T> extends InputStream implements WrapperOf<T> {

    final InputStream delegate;

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for InputStreamOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.InputStream} object.
     */
    public InputStreamOf(Resource<T> cl, InputStream os) {
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
    public int read() throws IOException {
        return delegate.read();
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws IOException {
        delegate.close();
    }

    /**
     * Create an IntStream from this InputStream's bytes. An IOException may be
     * thrown while processing the IntStream. Consuming the IntStream entirely does
     * not close this InputStream.
     * 
     * @return an IntStream
     */
    public IntStream intStream() {
        return StreamInputAdapter.toIntStream(this);
    }

    /**
     * Use the InputStream inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<InputStreamOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }
}
