package iostream.flow;

import java.io.IOException;
import java.io.Reader;

import iostream.CloseChain;

public interface CharSource {

    Reader getReader(CloseChain toClose) throws IOException;

}
