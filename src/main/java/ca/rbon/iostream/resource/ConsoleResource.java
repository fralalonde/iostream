package ca.rbon.iostream.resource;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.CodeFlowError;

/**
 * <p>
 * ConsoleResource.
 * </p>
 */
public class ConsoleResource extends Resource<Console> {
    
    private static final String STREAM_NOT_SUPPORTED = "Byte-oriented stream operations not supported by Console resources.";
    
    private static final String NO_CONSOLE = "The system console is not available.";
    
    public ConsoleResource() throws IOException {
        if (System.console() == null) {
            throw new IOException(NO_CONSOLE);
        }
    }
    
    /**
     * <p>
     * getSupplier.
     * </p>
     *
     * @return a {@link ca.rbon.iostream.resource.Resource} object.
     */
    protected Resource<Console> getSupplier() {
        return this;
    }
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream() throws IOException {
        throw new CodeFlowError(STREAM_NOT_SUPPORTED);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader() throws IOException {
        return System.console().reader();
    }
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream() throws IOException {
        throw new CodeFlowError(STREAM_NOT_SUPPORTED);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Writer getWriter() throws IOException {
        return System.console().writer();
    }
    
    /** {@inheritDoc} */
    @Override
    public Console getResource() {
        return System.console();
    }
    
}
