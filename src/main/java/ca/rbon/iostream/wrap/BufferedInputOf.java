package ca.rbon.iostream.wrap;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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
     * @throws java.io.IOException if any.
     */
    public BufferedInputOf(Resource<T> cl, InputStream is) throws IOException {
        super(is);
        closer = cl;
    }
    
    /**
     * <p>
     * Constructor for BufferedInputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param is a {@link java.io.InputStream} object.
     * @param bufferSize a int.
     * @throws java.io.IOException if any.
     */
    public BufferedInputOf(Resource<T> cl, InputStream is, int bufferSize) throws IOException {
        super(is, bufferSize);
        closer = cl;
    }
    
    /** {@inheritDoc} */
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
    /**
     * Create an IntStream from this InputStream's bytes.
     * An IOException may be thrown while processing the IntStream.
     * Consuming the IntStream entirely does not close this InputStream.
     * 
     * @return an IntStream
     */
    public IntStream intStream() {
        return StreamInputAdapter.toIntStream(this);
    }
    
}
