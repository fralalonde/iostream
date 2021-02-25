package ca.rbon.iostream.wrap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiConsumer;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * DataInputOf class.
 * </p>
 *
 * @param <T> The resource type
 */
public class DataInputOf<T> extends DataInputStream implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for DataInputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param is a {@link java.io.InputStream} object.
     */
    public DataInputOf(Resource<T> cl, InputStream is) {
        super(is);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

    /**
     * Use the DataInputStream inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<DataInputOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }
}
