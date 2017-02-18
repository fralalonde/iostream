package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.ByteIn;
import ca.rbon.iostream.channel.part.ByteOut;
import ca.rbon.iostream.channel.part.CharIn;
import ca.rbon.iostream.channel.part.CharOut;
import ca.rbon.iostream.channel.part.Filter;

/**
 * <p>
 * BytesBiChannel interface.
 * </p>
 *
 * @param <T> The resource type
 */
public interface BytesBiChannel<T> extends Filter<BytesBiChannel<T>>, CharIn<T>, ByteIn<T>, CharOut<T>, ByteOut<T> {
    
}
