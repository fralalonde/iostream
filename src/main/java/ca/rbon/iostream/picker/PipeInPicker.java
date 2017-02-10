package ca.rbon.iostream.picker;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.fluent.InPick;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PipeInPicker extends BasePicker<PipedInputStream> implements InPick<PipedInputStream> {
    
    private static final String NO_OUTPUT = "PipeInPicker does not provide output facilities.";

    final PipedOutputStream output;
    
    PipedInputStream input;
    
    @Override
    protected OutputStream getOutputStream() throws IOException {
        throw new CodeFlowError(NO_OUTPUT);
    }
    
    @Override
    protected Reader getReader(Chain chaout) throws IOException {
        throw new CodeFlowError(NO_OUTPUT);
    }
    
    @Override
    protected PipedInputStream getInputStream() throws IOException {
        if (input == null) {
            input = output == null
                    ? new PipedInputStream()
                    : new PipedInputStream(output);
        }
        return input;
    }
    
    @Override
    protected Writer getWriter(Chain chaout) throws IOException {
        return null;
    }

    @Override
    public PipedInputStream getResource() throws IOException {
        return getInputStream();
    }
    
}
