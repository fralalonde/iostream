package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * ObjectOutputOf class.
 * </p>
 *
 * @param <T> The resource type
 */
public class ObjectOutputOf<T> extends ObjectOutputStream implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for ObjectOutputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    public ObjectOutputOf(Resource<T> cl, OutputStream os) throws IOException {
        super(os);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

}
