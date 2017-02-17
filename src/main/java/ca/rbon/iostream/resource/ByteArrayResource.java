package ca.rbon.iostream.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import ca.rbon.iostream.CodeFlowError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>
 * BytesPicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class ByteArrayResource extends Resource<byte[]> {
    
    /** Constant <code>DEFAULT_CAPACITY=-1</code> */
    public static final int DEFAULT_CAPACITY = -1;
    
    private static final String NO_BYTE_ARRAY_SET = "No byte array was provided for this operation.";
    
    final byte[] bytes;
    
    final int initialCapacity;
    
    ByteArrayOutputStream outStream;
    
    /**
     * <p>
     * getResource.
     * </p>
     *
     * @return an array of byte.
     */
    @Override
    public byte[] getResource() {
        if (outStream != null) {
            return outStream.toByteArray();
        }
        if (bytes == null) {
            throw new CodeFlowError(NO_BYTE_ARRAY_SET);
        }
        return bytes;
    }
    
    /** {@inheritDoc} */
    @Override
    protected ByteArrayInputStream getInputStream() throws IOException {
        if (bytes == null) {
            throw new CodeFlowError(NO_BYTE_ARRAY_SET);
        }
        return new ByteArrayInputStream(bytes);
    }
    
    /** {@inheritDoc} */
    @Override
    protected ByteArrayOutputStream getOutputStream() throws IOException {
        if (bytes != null) {
            outStream = initialCapacity > DEFAULT_CAPACITY
                    ? new ByteArrayOutputStream(initialCapacity + bytes.length)
                    : new ByteArrayOutputStream();
            outStream.write(bytes, 0, bytes.length);
        } else {
            outStream = initialCapacity > DEFAULT_CAPACITY
                    ? new ByteArrayOutputStream(initialCapacity)
                    : new ByteArrayOutputStream();
        }
        return outStream;
    }
    
}
