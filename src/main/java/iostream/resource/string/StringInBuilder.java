package iostream.resource.string;

import java.io.IOException;
import java.io.StringReader;

import iostream.CloseChain;
import iostream.natural.NaturalReaderBuilder;
import iostream.proxy.ReaderBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringInBuilder implements NaturalReaderBuilder<StringReader>, ReaderBuilder<String> {
    
    final InStringResource inStr;
    
    @Override
    public StringReader reader() throws IOException {
        return inStr.getReader(CloseChain.SELF_CLOSE);
    }
    
    @Override
    public InStringResource getCharSource() throws IOException {
        return inStr;
    }
    
}
