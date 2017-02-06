package iostream.flow;

import java.io.IOException;
import java.io.Reader;

import iostream.CloseChain;
import iostream.SinkTarget;

public interface CharSource<T> extends SinkTarget<T> {

    Reader getReader(CloseChain toClose) throws IOException;

}
