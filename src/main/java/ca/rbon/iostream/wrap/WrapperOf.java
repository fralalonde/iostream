package ca.rbon.iostream.wrap;

import java.io.IOException;

/**
 * Base interface for *Of wrappers.
 *
 * @param <T> The underlying resource type
 */
public interface WrapperOf<T> {

    /**
     * Access the inner resource wrapped by the stream.
     *
     * @return The underlying resource
     * @throws IOException if something bad happens
     */
    T getInner() throws IOException;

}
