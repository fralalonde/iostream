package ca.rbon.iostream.proxy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>DataOutputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class DataOutputOf<T> extends DataOutputStream  {
    
    final Resource<T> closer;
    
    /**
     * <p>Constructor for DataOutputOf.</p>
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
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
