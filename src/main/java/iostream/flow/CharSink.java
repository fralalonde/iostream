package iostream.flow;

import java.io.IOException;
import java.io.Writer;

import iostream.CloseChain;
import iostream.SubjectHolder;

public interface CharSink<T> extends SubjectHolder<T>  {

    Writer getWriter(CloseChain toClose) throws IOException;

}
