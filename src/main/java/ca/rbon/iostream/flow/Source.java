package ca.rbon.iostream.flow;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import ca.rbon.iostream.CloseChain;

public interface Source<T> extends ByteSource<T>, CharSource<T> {
    
    default Reader getReader(CloseChain toClose) throws IOException {
        return new InputStreamReader(getInputStream(toClose));
    }
    
}
