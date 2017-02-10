package ca.rbon.iostream.picker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.Resource;
import ca.rbon.iostream.fluent.BiPick;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BytesPicker extends BasePicker<byte[]> implements Resource<byte[]>, BiPick<byte[]> {
    
    public static final int DEFAULT_CAPACITY = -1;
    
    private static final String PREVENTED =
            " This should error should have been prevented by the fluent IoStream builder constraints." +
                    " Please create report this issue at https://github.com/fralalonde/iostream/issues";
    
    private static final String NO_BYTE_ARRAY_SET = "No byte array was provided for this operation." + PREVENTED;
    
    final byte[] bytes;
    
    final int initialCapacity;
    
    ByteArrayOutputStream stream;
    
    public byte[] getResource() {
        if (stream != null) {
            return stream.toByteArray();
        }
        if (bytes == null) {
            throw new IllegalStateException(NO_BYTE_ARRAY_SET);
        }
        return bytes;
    }
    
    @Override
    protected Resource<byte[]> getSupplier() {
        return this;
    }
    
    @Override
    protected ByteArrayInputStream getInputStream() throws IOException {
        if (bytes == null) {
            throw new IllegalStateException(NO_BYTE_ARRAY_SET);
        }
        return new ByteArrayInputStream(bytes);
    }
    
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        return null;
    }
    
    @Override
    protected ByteArrayOutputStream getOutputStream() throws IOException {
        return initialCapacity > DEFAULT_CAPACITY
                ? new ByteArrayOutputStream(initialCapacity)
                : new ByteArrayOutputStream();
    }
    
    @Override
    protected Writer getWriter(Chain chain) throws IOException {
        return null;
    }
    
}
