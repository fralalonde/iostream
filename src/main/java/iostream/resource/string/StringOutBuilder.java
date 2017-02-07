package iostream.resource.string;

import java.io.IOException;
import java.io.StringWriter;

import iostream.CloseChain;
import iostream.natural.NaturalWriterBuilder;
import iostream.proxy.WriterBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringOutBuilder implements WriterBuilder<String>, NaturalWriterBuilder<StringWriter> {
    
    final OutStringResource outStr;
    
    @Override
    public StringWriter writer() throws IOException {
        return outStr.getWriter(CloseChain.SELF_CLOSE);
    }
    
    @Override
    public OutStringResource getCharSink() throws IOException {
        return outStr;
    }
    
}
