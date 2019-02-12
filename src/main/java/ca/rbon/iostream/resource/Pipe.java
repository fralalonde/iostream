package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;

/**
 * <p>
 * PipeOutPicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class Pipe extends Resource<Pipe> {

    PipedInputStream input;

    PipedOutputStream output;

    final int pipeSize;

    public Pipe() {
        pipeSize = DEFAULT_BUFFER_SIZE;
    }

    public Pipe(PipedInputStream in) {
        input = in;
        pipeSize = DEFAULT_BUFFER_SIZE;
    }

    public Pipe(int size) {
        pipeSize = size;
    }

    /** {@inheritDoc} */
    @Override
    public PipedInputStream getInputStream() throws IOException {
        if (input == null) {
            input = output == null
                    ? pipeSize == DEFAULT_BUFFER_SIZE ? new PipedInputStream() : new PipedInputStream(pipeSize)
                    : pipeSize == DEFAULT_BUFFER_SIZE ? new PipedInputStream(output)
                            : new PipedInputStream(output, pipeSize);
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
            output = new PipedOutputStream(getInputStream());
        }
        return output;
    }

    /** {@inheritDoc} */
    @Override
    public Pipe getResource() throws IOException {
        return this;
    }

}
