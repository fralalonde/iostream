package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.ByteIn;
import ca.rbon.iostream.channel.part.CharIn;
import ca.rbon.iostream.channel.part.Filter;

/**
 * <p>
 * BytesInChannel interface.
 * </p>
 *
 * @param <T> The resource type
 */
public interface BytesInChannel<T> extends Filter<BytesInChannel<T>>, /* FilterIn<BytesInChannel<T>>, */CharIn<T>, ByteIn<T> {
}
