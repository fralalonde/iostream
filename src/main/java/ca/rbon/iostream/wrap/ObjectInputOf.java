package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.BiConsumer;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * ObjectInputOf class.
 * </p>
 *
 * @param <T> The resource type
 */
public class ObjectInputOf<T> extends ObjectInputStream implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for ObjectInputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param is a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    public ObjectInputOf(Resource<T> cl, InputStream is) throws IOException {
        super(is);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

    /**
     * Use the ObjectInputStream inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<ObjectInputOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }
}
