package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.GZip;
import ca.rbon.iostream.channel.part.CharOut;
import ca.rbon.iostream.channel.part.ByteOut;

/**
 * <p>
 * OutPick interface.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public interface BytesOutChannel<T> extends GZip<BytesOutChannel<T>>, CharOut<T>, ByteOut<T> {
    
}
