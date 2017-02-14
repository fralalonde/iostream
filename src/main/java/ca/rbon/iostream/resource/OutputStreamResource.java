package ca.rbon.iostream.resource;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.channel.OutputChannel;
import lombok.RequiredArgsConstructor;

/**
 * <p>PipeOutPicker class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
@RequiredArgsConstructor
public class OutputStreamResource extends BaseResource<OutputStream> implements OutputChannel<OutputStream> {
    
    private static final String NO_INPUT = "%s does not provide input facilities.";
    
    final OutputStream outputStream;
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream(Chain chain) throws IOException {
        throw new CodeFlowError(NO_INPUT, OutputStreamResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        throw new CodeFlowError(NO_INPUT, OutputStreamResource.class);
    }
        
    /** {@inheritDoc} */
    @Override
    protected Writer getWriter(Chain chain) throws IOException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public OutputStream getResource() throws IOException {
        return getOutputStream(null);
    }

    @Override
    public <T extends Closeable> T addLink(T closeable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected OutputStream getOutputStream(Chain chain) throws IOException {
        return outputStream;
    }
    
}
