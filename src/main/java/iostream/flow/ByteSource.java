package iostream.flow;

import java.io.IOException;
import java.io.InputStream;

import iostream.CloseChain;
import iostream.SinkTarget;

public interface ByteSource<T> extends SinkTarget<T> {

    InputStream getInputStream(CloseChain toClose) throws IOException;

}
