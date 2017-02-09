package ca.rbon.iostream.resource.string;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.flow.CharSink;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutStringResource implements CharSink<String> {
    
    final StringWriter wr;
            
    @Override
    public StringWriter getWriter(Chain onClose) throws IOException {
        return onClose.add(wr);
    }
    
    @Override
    public String getResource() {
        return wr.toString();
    }

    @Override
    public Charset getEncoding() {
        return null;
    }    
    
}
