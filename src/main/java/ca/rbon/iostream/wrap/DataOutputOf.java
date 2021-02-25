package ca.rbon.iostream.wrap;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.BiConsumer;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * DataOutputOf class.
 * </p>
 *
 * @param <T> The resource type
 */
public class DataOutputOf<T> extends DataOutputStream implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for DataOutputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.OutputStream} object.
     */
    public DataOutputOf(Resource<T> cl, OutputStream os) {
        super(os);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

    /**
     * Use the DataOutputStream inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<DataOutputOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }
}
