package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.CharIn;
import ca.rbon.iostream.channel.part.CharOut;

/**
 * <p>
 * InOutCharPick interface.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 * @param <T> The resource type
 */
public interface CharBiChannel<T> extends CharIn<T>, CharOut<T> {
    
}
