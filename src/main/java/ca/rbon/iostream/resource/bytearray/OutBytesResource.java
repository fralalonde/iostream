package ca.rbon.iostream.resource.bytearray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.flow.Sink;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Wither;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OutBytesResource implements Sink<byte[]> {
    
    final ByteArrayOutputStream wr;
    
    @Wither
    @Getter
    final Charset encoding;
    
    public OutBytesResource() {
        wr = new ByteArrayOutputStream();
        encoding = null;
    }
    
    /**
     * "append" constructor
     * 
     * @param prepend
     * @throws IOException
     */
    public OutBytesResource(byte[] prepend) throws IOException {
        wr = new ByteArrayOutputStream();
        wr.write(prepend);
        encoding = null;
    }
    
    @Override
    public OutputStream getOutputStream(Chain onClose) throws IOException {
        return onClose.add(wr);
    }
    
    @Override
    public byte[] getResource() {
        return wr.toByteArray();
    }
    
}
