package ca.rbon.iostream.resource;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>
 * ConsolePicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class ConsoleResource extends Resource<Console> {
    
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
        return System.in;
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader() throws IOException {
        return System.console().reader();
    }
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream() throws IOException {
        return System.err;
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
