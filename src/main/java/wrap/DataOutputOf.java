package wrap;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
     * @throws java.io.IOException if any.
     */
    public DataOutputOf(Resource<T> cl, OutputStream os) throws IOException {
        super(os);
        closer = cl;
    }
    
    /** {@inheritDoc} */
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
