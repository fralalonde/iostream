package ca.rbon.iostream.flow;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import ca.rbon.iostream.CloseChain;

public interface Sink<T> extends ByteSink<T>, CharSink<T> {
    
    default Writer getWriter(CloseChain toClose) throws IOException {
        return new OutputStreamWriter(getOutputStream(toClose));
    }
    
}
