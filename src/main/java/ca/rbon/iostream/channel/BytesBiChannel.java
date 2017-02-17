package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.GZip;
import ca.rbon.iostream.channel.part.CharIn;
import ca.rbon.iostream.channel.part.ByteIn;
import ca.rbon.iostream.channel.part.CharOut;
import ca.rbon.iostream.channel.part.ByteOut;

/**
 * <p>
 * InOutPick interface.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public interface BytesBiChannel<T> extends GZip<BytesBiChannel<T>>, CharIn<T>, ByteIn<T>, CharOut<T>, ByteOut<T> {
    
}
