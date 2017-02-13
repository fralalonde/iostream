package ca.rbon.iostream.stream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>DataInputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class DataInputOf<T> extends DataInputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    /**
     * <p>Constructor for DataInputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param is a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    public DataInputOf(ClosingResource<T> cl, InputStream is) throws IOException {
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
