package iostream.flow;

import java.io.IOException;
import java.io.OutputStream;

import iostream.CloseChain;
import iostream.SinkTarget;

public interface ByteSink<T> extends SinkTarget<T>  {

    OutputStream getOutputStream(CloseChain toClose) throws IOException;

}
