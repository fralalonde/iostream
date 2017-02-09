package ca.rbon.iostream.resource.bytearray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.flow.Source;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InBytesResource implements Source<byte[]> {
    
    final byte[] bytes;
    
    @Getter
    final Charset encoding;

    public InBytesResource(byte[] bytes2) {
        bytes = bytes2;
        encoding = null;
    }        
    
    @Override
    public ByteArrayInputStream getInputStream(Chain onClose) throws IOException {
        return onClose.add(new ByteArrayInputStream(bytes));
    }
    
    @Override
    public byte[] getResource() {
        return bytes;
    }

}
