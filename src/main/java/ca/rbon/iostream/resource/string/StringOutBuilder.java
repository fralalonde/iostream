package ca.rbon.iostream.resource.string;

import java.io.IOException;
import java.io.StringWriter;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.natural.NaturalWriter;
import ca.rbon.iostream.proxy.WriterBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StringOutBuilder implements WriterBuilder<String>, NaturalWriter<StringWriter> {
    
    final OutStringResource outStr;
    
    @Override
    public StringWriter writer() throws IOException {
        return outStr.getWriter(Chain.NO_PROXY);
    }
    
    @Override
    public OutStringResource getCharSink() throws IOException {
        return outStr;
    }
    
}
