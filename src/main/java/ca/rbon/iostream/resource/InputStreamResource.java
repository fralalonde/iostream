package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.channel.InputChannel;
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
public class InputStreamResource extends Resource<InputStream> implements InputChannel<InputStream> {
    
    private static final String NO_OUTPUT = "%s does not provide output facilities.";
    
    final InputStream inputStream;
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream() throws IOException {
        return inputStream;
    }
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream() throws IOException {
        throw new CodeFlowError(NO_OUTPUT, InputStreamResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Writer getWriter() throws IOException {
        throw new CodeFlowError(NO_OUTPUT, InputStreamResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    public InputStream getResource() throws IOException {
        return getInputStream();
    }
    
}
