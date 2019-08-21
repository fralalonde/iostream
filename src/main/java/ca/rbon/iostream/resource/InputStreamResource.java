package ca.rbon.iostream.resource;

import ca.rbon.iostream.CodeFlowError;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>
 * InputStreamResource wraps an existing InputStream to be used as input to a
 * built IoStream chain.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class InputStreamResource extends Resource<InputStream> {

    static final String NO_OUTPUT = "%s does not provide output facilities.";


    final InputStream inputStream;

    public InputStreamResource( InputStream inputStream) {
        this.inputStream = inputStream;
    }

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


    public InputStream getInputStream() {
        return this.inputStream;
    }
}
