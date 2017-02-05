package iostream.flow;

import java.io.IOException;
import java.io.InputStream;

import iostream.CloseChain;

public interface ByteSource {

    InputStream getInputStream(CloseChain toClose) throws IOException;

}
