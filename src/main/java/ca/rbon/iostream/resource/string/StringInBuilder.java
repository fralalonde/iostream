package ca.rbon.iostream.resource.string;

import java.io.IOException;
import java.io.StringReader;

import ca.rbon.iostream.CloseChain;
import ca.rbon.iostream.natural.NaturalReaderBuilder;
import ca.rbon.iostream.proxy.ReaderBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringInBuilder implements NaturalReaderBuilder<StringReader>, ReaderBuilder<String> {
    
    final InStringResource inStr;
    
    @Override
    public StringReader reader() throws IOException {
        return inStr.getReader(CloseChain.NO_PROXY);
    }
    
    @Override
    public InStringResource getCharSource() throws IOException {
        return inStr;
    }
    
}
