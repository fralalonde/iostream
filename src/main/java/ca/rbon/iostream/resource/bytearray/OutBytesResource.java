package ca.rbon.iostream.resource.bytearray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.CloseChain;
import ca.rbon.iostream.flow.Sink;

public class OutBytesResource implements Sink<byte[]> {
    
    final ByteArrayOutputStream wr;
    
    public OutBytesResource() {
        wr = new ByteArrayOutputStream();
    }
    
    /**
     * "append" constructor
     * 
     * @param append
     * @throws IOException
     */
    public OutBytesResource(byte[] append) throws IOException {
        wr = new ByteArrayOutputStream();
        wr.write(append);
    }
    
    @Override
    public OutputStream getOutputStream(CloseChain onClose) throws IOException {
        return onClose.add(wr);
    }
    
    @Override
    public byte[] getResource() {
        return wr.toByteArray();
    }
    
}
