package ca.rbon.iostream.wrap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * BufferedReaderOf class.
 * </p>
 *
 * @param <T> The resource type
 */
public class BufferedReaderOf<T> extends BufferedReader implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for BufferedReaderOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param r  a {@link java.io.Reader} object.
     */
    public BufferedReaderOf(Resource<T> cl, Reader r) {
        super(r);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

}
