package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.ByteOut;
import ca.rbon.iostream.channel.part.CharOut;
import ca.rbon.iostream.channel.part.Filter;

/**
 * A byte or char stream builder that can be used for output only.
 *
 * @author fralalonde
 *
 * @param <T> The backing resource type
 */
public interface BytesOutChannel<T> extends Filter<BytesOutChannel<T>>, CharOut<T>, ByteOut<T> {

}
