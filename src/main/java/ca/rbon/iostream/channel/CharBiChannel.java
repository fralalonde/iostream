package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.CharIn;
import ca.rbon.iostream.channel.part.CharOut;

/**
 * A char-oriented stream builder that can be used for output only.
 *
 * @author fralalonde
 * @param <T> The backing resource type
 */
public interface CharBiChannel<T> extends CharIn<T>, CharOut<T> {
}
