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
 * InputStreamResource wraps an existing InputStream to be used as input to a built IoStream chain.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
@RequiredArgsConstructor
public class InputStreamResource extends Resource<InputStream> {
    
    static final String NO_OUTPUT = "%s does not provide output facilities.";
    
    @NonNull
    @Getter
    final InputStream inputStream;
    
    /**
     * <p>
     * getOutputStream.
     * </p>
     * 
     * @return a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    protected OutputStream getOutputStream() throws IOException {
        throw new CodeFlowError(NO_OUTPUT, this.getClass());
    }
    
    /** {@inheritDoc} */
    @Override
    public InputStream getResource() throws IOException {
        return getInputStream();
    }
    
}
