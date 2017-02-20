package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

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
    public T get() throws IOException {
        return closer.getResource();
    }
    
}
