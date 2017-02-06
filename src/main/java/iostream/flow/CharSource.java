package iostream.flow;

import java.io.IOException;
import java.io.Reader;

import iostream.CloseChain;
import iostream.SubjectHolder;

public interface CharSource<T> extends SubjectHolder<T> {

    Reader getReader(CloseChain toClose) throws IOException;

}
