package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.channel.OutputChannel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>PipeOutPicker class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class PipeOutResource extends BaseResource<PipedOutputStream> implements OutputChannel<PipedOutputStream> {
    
    private static final String NO_INPUT = "%s does not provide input facilities.";

    final PipedInputStream input;
    
    PipedOutputStream output;
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream(Chain chain) throws IOException {
        throw new CodeFlowError(NO_INPUT, PipeOutResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        throw new CodeFlowError(NO_INPUT, PipeOutResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected PipedOutputStream getOutputStream(Chain chain) throws IOException {
        if (output == null) {
            output = input == null
                    ? new PipedOutputStream()
                    : new PipedOutputStream(input);
        }
        return chain.addLink(output);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Writer getWriter(Chain chain) throws IOException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public PipedOutputStream getResource() throws IOException {
        return getOutputStream(null);
    }
    
}
