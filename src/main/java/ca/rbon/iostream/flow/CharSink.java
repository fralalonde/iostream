package ca.rbon.iostream.flow;

import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.CloseChain;
import ca.rbon.iostream.ResourceHolder;

public interface CharSink<T> extends ResourceHolder<T> {
    
    Writer getWriter(CloseChain toClose) throws IOException;
    
}
