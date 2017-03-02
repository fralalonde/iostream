package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.ByteIn;
import ca.rbon.iostream.channel.part.ByteOut;
import ca.rbon.iostream.channel.part.CharIn;
import ca.rbon.iostream.channel.part.CharOut;
import ca.rbon.iostream.channel.part.Filter;

/**
 * A byte-oriented stream builder that can be used for input or output.
 *
 * @author fralalonde
 *
 * @param <T> The backing resource type
 */
public interface BytesBiChannel<T> extends Filter<BytesBiChannel<T>>, CharIn<T>, ByteIn<T>, CharOut<T>, ByteOut<T> {

}
