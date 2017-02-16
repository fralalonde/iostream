package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.channel.OutputChannel;
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
public class OutputStreamResource extends Resource<OutputStream> implements OutputChannel<OutputStream> {
    
    private static final String NO_INPUT = "%s does not provide input facilities.";
    
    final OutputStream outputStream;
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream() throws IOException {
        throw new CodeFlowError(NO_INPUT, OutputStreamResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader() throws IOException {
        throw new CodeFlowError(NO_INPUT, OutputStreamResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    public OutputStream getResource() throws IOException {
        return getOutputStream();
    }
    
    @Override
    protected OutputStream getOutputStream() throws IOException {
        return outputStream;
    }
    
}
