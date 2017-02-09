package ca.rbon.iostream.picker;

import ca.rbon.iostream.fluent.BufPick;
import ca.rbon.iostream.proxy.stream.BufferedInputProxy;
import ca.rbon.iostream.proxy.stream.BufferedOutputProxy;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;

public class InOutBufBytesPicker extends BytesPicker implements BufPick<byte[]> {

    public InOutBufBytesPicker(InOutBytesPicker inOutBytesPicker) {
        super(inOutBytesPicker);
    }

    
}
