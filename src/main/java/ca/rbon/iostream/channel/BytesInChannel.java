package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.ByteIn;
import ca.rbon.iostream.channel.part.CharIn;
import ca.rbon.iostream.channel.part.Filter;

/**
 * A byte or char stream builder that can be used only for input.
 *
 * @author fralalonde
 *
 * @param <T> The backing resource type
 */
public interface BytesInChannel<T> extends Filter<BytesInChannel<T>>, CharIn<T>, ByteIn<T> {
}
