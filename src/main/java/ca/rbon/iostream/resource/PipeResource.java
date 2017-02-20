package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;

import lombok.RequiredArgsConstructor;

/**
 * <p>
 * PipeOutPicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
@RequiredArgsConstructor
public class PipeResource extends Resource<PipeResource> {
    
    PipedInputStream input;
    
    PipedOutputStream output;
    
    final int pipeSize;
    
    public PipeResource() {
        pipeSize = DEFAULT_BUFFER_SIZE;
    }
    
    public PipeResource(PipedInputStream in) {
        input = in;
        pipeSize = DEFAULT_BUFFER_SIZE;
    }
    
    public PipeResource(PipedOutputStream out) {
        output = out;
        pipeSize = DEFAULT_BUFFER_SIZE;
    }
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream() throws IOException {
        if (input == null) {
            input = output == null
                    ? pipeSize == DEFAULT_BUFFER_SIZE ? new PipedInputStream() : new PipedInputStream(pipeSize)
                    : pipeSize == DEFAULT_BUFFER_SIZE ? new PipedInputStream(output) : new PipedInputStream(output, pipeSize);
        }
        return input;
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader() throws IOException {
        return null;
    }
    
    /** {@inheritDoc} */
    @Override
    protected PipedOutputStream getOutputStream() throws IOException {
        if (output == null) {
            output = input == null
                    ? new PipedOutputStream()
                    : new PipedOutputStream(input);
        }
        return output;
    }
    
    /** {@inheritDoc} */
    @Override
    public PipeResource getResource() throws IOException {
        return this;
    }
    
}
