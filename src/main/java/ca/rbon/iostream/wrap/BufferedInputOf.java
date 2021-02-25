package ca.rbon.iostream.wrap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * BufferedInputOf class.
 * </p>
 *
 * @param <T> The resource type
 */
public class BufferedInputOf<T> extends BufferedInputStream implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for BufferedInputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param is a {@link java.io.InputStream} object.
     */
    public BufferedInputOf(Resource<T> cl, InputStream is) {
        super(is);
        closer = cl;
    }

    /**
     * <p>
     * Constructor for BufferedInputOf.
     * </p>
     *
     * @param cl         a {@link ca.rbon.iostream.resource.Resource} object.
     * @param is         a {@link java.io.InputStream} object.
     * @param bufferSize a int.
     */
    public BufferedInputOf(Resource<T> cl, InputStream is, int bufferSize) {
        super(is, bufferSize);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
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
     * Use the BufferedInputStream inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<BufferedInputOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }
}
