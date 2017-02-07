package iostream.flow;

import java.io.IOException;
import java.io.InputStream;

import iostream.CloseChain;
import iostream.ResourceHolder;

public interface ByteSource<T> extends ResourceHolder<T> {
    
    InputStream getInputStream(CloseChain toClose) throws IOException;
    
}
