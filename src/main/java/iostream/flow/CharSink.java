package iostream.flow;

import java.io.IOException;
import java.io.Writer;

import iostream.CloseChain;
import iostream.ResourceHolder;

public interface CharSink<T> extends ResourceHolder<T> {
    
    Writer getWriter(CloseChain toClose) throws IOException;
    
}
