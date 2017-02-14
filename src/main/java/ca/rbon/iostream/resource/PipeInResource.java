package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.channel.InputChannel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>PipeInPicker class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class PipeInResource extends BaseResource<PipedInputStream> implements InputChannel<PipedInputStream> {
    
    private static final String NO_OUTPUT = "%s does not provide output facilities.";

    final PipedOutputStream output;
    
    PipedInputStream input;
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream(Chain chain) throws IOException {
        throw new CodeFlowError(NO_OUTPUT, PipeInResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader(Chain chaout) throws IOException {
        throw new CodeFlowError(NO_OUTPUT, PipeInResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected PipedInputStream getInputStream(Chain chain) throws IOException {
        if (input == null) {
            input = output == null
                    ? new PipedInputStream()
                    : new PipedInputStream(output);
        }
        return chain.addLink(input);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Writer getWriter(Chain chaout) throws IOException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public PipedInputStream getResource() throws IOException {
        return getInputStream(null);
    }
    
}
