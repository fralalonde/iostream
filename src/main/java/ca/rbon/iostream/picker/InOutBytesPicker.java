package ca.rbon.iostream.picker;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import ca.rbon.iostream.fluent.BufferSizePick;
import ca.rbon.iostream.fluent.BufPick;
import ca.rbon.iostream.proxy.stream.BufferedInputProxy;
import ca.rbon.iostream.proxy.stream.DataInputProxy;
import ca.rbon.iostream.proxy.stream.ObjectInputProxy;
import ca.rbon.iostream.proxy.stream.UnbufferedInputProxy;
import ca.rbon.iostream.proxy.stream.UnbufferedOutputProxy;
import ca.rbon.iostream.proxy.stream.ZipInputProxy;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.UnbufferedWriterProxy;

public class InOutBytesPicker extends BytesPicker implements BufferSizePick<byte[]> {
        
    public InOutBytesPicker(BytesPicker picker) {
        super(picker);       
    }
    
    @Override
    public BufPick<byte[]> bufferSize(int size) {
        bufferSize = size;
        return new InOutBufBytesPicker(this);
    }

    @Override
    public UnbufferedInputProxy<byte[]> inputStream() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UnbufferedWriterProxy<byte[]> writer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UnbufferedOutputProxy<byte[]> outputStream() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
