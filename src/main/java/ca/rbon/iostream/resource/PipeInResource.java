package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;

import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.channel.InputChannel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>
 * PipeInPicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class PipeInResource extends Resource<PipedInputStream> implements InputChannel<PipedInputStream> {
    
    private static final String NO_OUTPUT = "%s does not provide output facilities.";
    
    final PipedOutputStream output;
    
    PipedInputStream input;
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream() throws IOException {
        throw new CodeFlowError(NO_OUTPUT, PipeInResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader() throws IOException {
        throw new CodeFlowError(NO_OUTPUT, PipeInResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected PipedInputStream getInputStream() throws IOException {
        if (input == null) {
            input = output == null
                    ? new PipedInputStream()
                    : new PipedInputStream(output);
        }
        return input;
    }
    
    /** {@inheritDoc} */
    @Override
    public PipedInputStream getResource() throws IOException {
        return getInputStream();
    }
    
}
