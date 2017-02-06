package iostream.flow;

import java.io.IOException;
import java.io.OutputStream;

import iostream.CloseChain;
import iostream.SubjectHolder;

public interface ByteSink<T> extends SubjectHolder<T>  {

    OutputStream getOutputStream(CloseChain toClose) throws IOException;

}
