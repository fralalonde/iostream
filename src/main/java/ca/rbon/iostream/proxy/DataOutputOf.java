package ca.rbon.iostream.proxy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.Resource;
import ca.rbon.iostream.resource.BaseResource;

/**
 * <p>DataOutputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class DataOutputOf<T> extends DataOutputStream implements Resource<T> {
    
    final BaseResource<T> closer;
    
    /**
     * <p>Constructor for DataOutputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    public DataOutputOf(BaseResource<T> cl, OutputStream os) throws IOException {
        super(os);
        closer = cl;
    }
    
    /** {@inheritDoc} */
    @Override
    public void close() throws IOException {
        super.close();
        closer.close();
    }
    
    /** {@inheritDoc} */
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
