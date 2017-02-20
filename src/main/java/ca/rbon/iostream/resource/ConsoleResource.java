package ca.rbon.iostream.resource;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * <p>
 * ConsoleResource.
 * </p>
 */
public class ConsoleResource extends Resource<Console> {
    
    private static final String NO_CONSOLE = "The system console is not available.";
    
    public ConsoleResource() throws IOException {
        if (System.console() == null) {
            throw new IOException(NO_CONSOLE);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader() throws IOException {
        return System.console().reader();
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
