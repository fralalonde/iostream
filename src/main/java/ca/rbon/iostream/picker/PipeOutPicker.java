package ca.rbon.iostream.picker;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.fluent.OutPick;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>PipeOutPicker class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class PipeOutPicker extends BasePicker<PipedOutputStream> implements OutPick<PipedOutputStream> {
    
    private static final String NO_INPUT = "PipeOutPicker does not provide input facilities.";

    final PipedInputStream input;
    
    PipedOutputStream output;
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream() throws IOException {
        throw new CodeFlowError(NO_INPUT);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        throw new CodeFlowError(NO_INPUT);
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
    protected Writer getWriter(Chain chain) throws IOException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public PipedOutputStream getResource() throws IOException {
        return getOutputStream();
    }
    
}
