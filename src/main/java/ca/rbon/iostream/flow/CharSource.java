package ca.rbon.iostream.flow;

import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.CloseChain;
import ca.rbon.iostream.ResourceHolder;

public interface CharSource<T> extends ResourceHolder<T> {
    
    Reader getReader(CloseChain toClose) throws IOException;
    
}
