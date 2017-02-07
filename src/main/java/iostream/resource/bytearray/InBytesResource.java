package iostream.resource.bytearray;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import iostream.CloseChain;
import iostream.flow.Source;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InBytesResource implements Source<byte[]> {
    
    final byte[] bytes;
    
    @Override
    public ByteArrayInputStream getInputStream(CloseChain onClose) throws IOException {
        return onClose.add(new ByteArrayInputStream(bytes));
    }
    
    @Override
    public byte[] getResource() {
        return bytes;
    }
    
}
