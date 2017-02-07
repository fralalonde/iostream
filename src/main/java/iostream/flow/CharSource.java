package iostream.flow;

import java.io.IOException;
import java.io.Reader;

import iostream.CloseChain;
import iostream.ResourceHolder;

public interface CharSource<T> extends ResourceHolder<T> {

    Reader getReader(CloseChain toClose) throws IOException;

}
