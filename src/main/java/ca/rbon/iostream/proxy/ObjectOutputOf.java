package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * ObjectOutputOf class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class ObjectOutputOf<T> extends ObjectOutputStream  {
    
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
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
