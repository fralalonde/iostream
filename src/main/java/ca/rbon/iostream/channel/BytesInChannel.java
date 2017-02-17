package ca.rbon.iostream.channel;

import ca.rbon.iostream.channel.part.GZip;
import ca.rbon.iostream.channel.part.CharIn;
import ca.rbon.iostream.channel.part.ByteIn;

/**
 * <p>
 * InPick interface.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public interface BytesInChannel<T> extends GZip<BytesInChannel<T>>, CharIn<T>, ByteIn<T> {
}
