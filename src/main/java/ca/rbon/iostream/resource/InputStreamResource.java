package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.channel.InputChannel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <p>PipeInPicker class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
@RequiredArgsConstructor
public class InputStreamResource extends BaseResource<InputStream> implements InputChannel<InputStream> {
    
    private static final String NO_OUTPUT = "%s does not provide output facilities.";

    @Getter
    final InputStream inputStream;
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream() throws IOException {
        throw new CodeFlowError(NO_OUTPUT, InputStreamResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader(Chain chaout) throws IOException {
        throw new CodeFlowError(NO_OUTPUT, InputStreamResource.class);
    }
    
    
    /** {@inheritDoc} */
    @Override
    protected Writer getWriter(Chain chaout) throws IOException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public InputStream getResource() throws IOException {
        return getInputStream();
    }

    
}
