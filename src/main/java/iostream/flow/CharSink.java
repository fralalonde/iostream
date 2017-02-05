package iostream.flow;

import java.io.IOException;
import java.io.Writer;

import iostream.CloseChain;
import iostream.SinkTarget;

public interface CharSink<T> extends SinkTarget<T>  {

    Writer getWriter(CloseChain toClose) throws IOException;

}
