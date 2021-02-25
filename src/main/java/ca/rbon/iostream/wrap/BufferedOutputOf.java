package ca.rbon.iostream.wrap;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.BiConsumer;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * BufferedOutputOf class.
 * </p>
 *
 * @param <T> The resource type
 */
public class BufferedOutputOf<T> extends BufferedOutputStream implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for BufferedOutputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.OutputStream} object.
     */
    public BufferedOutputOf(Resource<T> cl, OutputStream os) {
        super(os);
        closer = cl;
    }

    /**
     * <p>
     * Constructor for BufferedOutputOf.
     * </p>
     *
     * @param cl         a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os         a {@link java.io.OutputStream} object.
     * @param bufferSize a int.
     */
    public BufferedOutputOf(Resource<T> cl, OutputStream os, int bufferSize) {
        super(os, bufferSize);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

    /**
     * Use the BufferedOutputStream inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<BufferedOutputOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }
}
