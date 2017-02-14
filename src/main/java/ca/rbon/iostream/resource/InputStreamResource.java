package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
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
public class InputStreamResource extends BaseResource<InputStream> implements InputChannel<InputStream> {
    
    private static final String NO_OUTPUT = "%s does not provide output facilities.";
    
    final InputStream inputStream;
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream(Chain chain) throws IOException {
        return inputStream;
    }
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream(Chain chain) throws IOException {
        throw new CodeFlowError(NO_OUTPUT, InputStreamResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        return null;
    }
    
    /** {@inheritDoc} */
    @Override
    protected Writer getWriter(Chain chain) throws IOException {
        throw new CodeFlowError(NO_OUTPUT, InputStreamResource.class);
    }
    
    /** {@inheritDoc} */
    @Override
    public InputStream getResource() throws IOException {
        return getInputStream(null);
    }
    
}
