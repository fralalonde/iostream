package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.ByteOut;
import ca.rbon.iostream.channel.part.CharOut;
import ca.rbon.iostream.channel.part.Filter;

/**
 * <p>
 * OutPick interface.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 * @param <T> The resource type
 */
public interface BytesOutChannel<T> extends Filter<BytesOutChannel<T>>, CharOut<T>, ByteOut<T> {
    
}
