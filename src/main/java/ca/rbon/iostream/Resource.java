package ca.rbon.iostream;

import java.io.IOException;

/**
 * <p>Resource interface.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public interface Resource<T> {
    
    /**
     * <p>getResource.</p>
     *
     * @return a T object.
     * @throws java.io.IOException if any.
     */
    T getResource() throws IOException;
    
}
