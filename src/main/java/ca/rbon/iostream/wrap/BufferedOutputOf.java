package ca.rbon.iostream.wrap;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
     * @throws java.io.IOException if any.
     */
    public BufferedOutputOf(Resource<T> cl, OutputStream os) throws IOException {
        super(os);
        closer = cl;
    }
    
    /**
     * <p>
     * Constructor for BufferedOutputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @param bufferSize a int.
     * @throws java.io.IOException if any.
     */
    public BufferedOutputOf(Resource<T> cl, OutputStream os, int bufferSize) throws IOException {
        super(os, bufferSize);
        closer = cl;
    }
    
    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }
    
}
