package ca.rbon.iostream;

import java.io.Closeable;

/**
 * <p>Chain interface.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public interface Chain {
    
    
    /**
     * <p>chainAdd.</p>
     *
     * @param closeable a T object.
     * @param <T> a T object.
     * @return a T object.
     */
    <T extends Closeable> T addLink(T closeable);
    
}
