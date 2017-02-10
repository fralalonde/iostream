package ca.rbon.iostream.picker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.Resource;
import ca.rbon.iostream.fluent.BiCharPick;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringPicker extends BasePicker<String> implements Resource<String>, BiCharPick<String> {

    public static final int DEFAULT_CAPACITY = -1;
    
    
    private static final String PREVENTED =
            " This should error should have been prevented by the fluent IoStream builder constraints." +
                    " Please create report this issue at https://github.com/fralalonde/iostream/issues";
    
    private static final String STREAM_NOT_SUPPORTED = "Byte-oriented stream operations not supported by String resources." + PREVENTED;
    
    private static final String NO_STRING_SET = "No string was provided for this operation." + PREVENTED;
    
    final String str;
    
    final int capacity;
    
    StringWriter writer;
    
    @Override
    protected Resource<String> getSupplier() {
        return this;
    }
    
    @Override
    public String getResource() {
        if (writer != null) {
            return writer.toString();
        }
        if (str == null) {
            throw new IllegalStateException(NO_STRING_SET);
        }
        return str;
    }
    
    @Override
    protected InputStream getInputStream() throws IOException {
        throw new UnsupportedOperationException(STREAM_NOT_SUPPORTED);
    }
    
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        if (str == null) {
            throw new IllegalStateException(NO_STRING_SET);
        }
        return chain.add(new StringReader(str));
    }
    
    @Override
    protected OutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException(STREAM_NOT_SUPPORTED);
    }
    
    @Override
    protected Writer getWriter(Chain chain) throws IOException {
        if (str != null) {
            writer = capacity > DEFAULT_CAPACITY
                    ? new StringWriter(str.length() + capacity)
                    : new StringWriter(str.length());
            writer.write(str);
            
        } else {
            writer = capacity > DEFAULT_CAPACITY
                    ? new StringWriter(capacity)
                    : new StringWriter();
        }
        return chain.add(writer);
    }
    
}
