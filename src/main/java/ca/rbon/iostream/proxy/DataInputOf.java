package ca.rbon.iostream.proxy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.Resource;
import ca.rbon.iostream.resource.BaseResource;

/**
 * <p>DataInputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class DataInputOf<T> extends DataInputStream implements Resource<T> {
    
    final BaseResource<T> closer;
    
    /**
     * <p>Constructor for DataInputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param is a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    public DataInputOf(BaseResource<T> cl, InputStream is) throws IOException {
        super(is);
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
