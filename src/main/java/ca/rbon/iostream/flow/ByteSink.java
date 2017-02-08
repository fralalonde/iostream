package ca.rbon.iostream.flow;

import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.CloseChain;
import ca.rbon.iostream.ResourceHolder;

public interface ByteSink<T> extends ResourceHolder<T> {
    
    OutputStream getOutputStream(CloseChain toClose) throws IOException;
    
}
