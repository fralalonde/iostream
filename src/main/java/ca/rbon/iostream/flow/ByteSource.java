package ca.rbon.iostream.flow;

import java.io.IOException;
import java.io.InputStream;

import ca.rbon.iostream.CloseChain;
import ca.rbon.iostream.ResourceHolder;

public interface ByteSource<T> extends ResourceHolder<T> {
    
    InputStream getInputStream(CloseChain toClose) throws IOException;
    
}
