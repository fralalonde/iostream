package ca.rbon.iostream.resource.string;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.flow.CharSource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InStringResource implements CharSource<String> {
    
    final String str;
    
    @Override
    public StringReader getReader(Chain onClose) throws IOException {
        return onClose.add(new StringReader(str));
    }
    
    @Override
    public String getResource() {
        return str;
    }

    @Override
    public Charset getEncoding() {
        return null;
    }
    
}
