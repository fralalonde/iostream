package ca.rbon.iostream.proxy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>DataInputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class DataInputOf<T> extends DataInputStream  {
    
    final Resource<T> closer;
    
    /**
     * <p>Constructor for DataInputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param is a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    public DataInputOf(Resource<T> cl, InputStream is) throws IOException {
        super(is);
        closer = cl;
    }
    
    /** {@inheritDoc} */
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
