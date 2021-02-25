package ca.rbon.iostream.wrap;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.function.BiConsumer;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * BufferedWriterOf class.
 * </p>
 *
 * @param <T> The resource type
 */
public class BufferedWriterOf<T> extends BufferedWriter implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for BufferedWriterOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param wr a {@link java.io.Writer} object.
     */
    public BufferedWriterOf(Resource<T> cl, Writer wr) {
        super(wr);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

    /**
     * Use the BufferedWriter inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<BufferedWriterOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }
}
