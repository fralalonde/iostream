package ca.rbon.iostream.resource.string;

import java.io.IOException;
import java.io.StringWriter;

import ca.rbon.iostream.CloseChain;
import ca.rbon.iostream.natural.NaturalWriterBuilder;
import ca.rbon.iostream.proxy.WriterBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringOutBuilder implements WriterBuilder<String>, NaturalWriterBuilder<StringWriter> {
    
    final OutStringResource outStr;
    
    @Override
    public StringWriter writer() throws IOException {
        return outStr.getWriter(CloseChain.NO_PROXY);
    }
    
    @Override
    public OutStringResource getCharSink() throws IOException {
        return outStr;
    }
    
}
