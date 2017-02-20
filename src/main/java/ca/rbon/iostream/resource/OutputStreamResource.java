package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ca.rbon.iostream.CodeFlowError;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * PipeOutPicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
@RequiredArgsConstructor
public class OutputStreamResource extends Resource<OutputStream> {
    
    static final String NO_INPUT = "%s does not provide input facilities.";
    
    @NonNull
    @Getter
    final OutputStream outputStream;
    
    /**
     * <p>
     * getOutputStream.
     * </p>
     * 
     * @return a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    protected InputStream getInputStream() throws IOException {
        throw new CodeFlowError(NO_INPUT, this.getClass());
    }
    
    /** {@inheritDoc} */
    @Override
    public OutputStream getResource() throws IOException {
        return getOutputStream();
    }
    
}
