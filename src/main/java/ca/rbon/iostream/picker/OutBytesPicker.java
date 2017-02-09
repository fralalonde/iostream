package ca.rbon.iostream.picker;

import ca.rbon.iostream.fluent.OutBufferPick;
import ca.rbon.iostream.fluent.OutUnbufPick;

public class OutBytesPicker extends BytesPicker implements OutBufferPick<byte[]> {
    
    @Override
    public OutUnbufPick<byte[]> bufferSize(int size) {
        bufferSize = size;
        return this;
    }
    
}
