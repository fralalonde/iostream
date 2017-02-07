package iostream.flow;

import java.io.IOException;
import java.io.OutputStream;

import iostream.CloseChain;
import iostream.ResourceHolder;

public interface ByteSink<T> extends ResourceHolder<T>  {

    OutputStream getOutputStream(CloseChain toClose) throws IOException;

}
