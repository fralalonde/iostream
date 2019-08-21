package ca.rbon.iostream.resource;

import ca.rbon.iostream.CodeFlowError;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>
 * PipeOutPicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class OutputStreamResource extends Resource<OutputStream> {

    static final String NO_INPUT = "%s does not provide input facilities.";

    final OutputStream outputStream;

    public OutputStreamResource(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * <p>
     * getOutputStream.
     * </p>
     * 
     * @return a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    protected InputStream getInputStream() throws IOException {
        throw new CodeFlowError(NO_INPUT, this.getClass());
    }

    /** {@inheritDoc} */
    @Override
    public OutputStream getResource() throws IOException {
        return getOutputStream();
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }
}
