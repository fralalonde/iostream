package iostream.flow;

import java.io.IOException;
import java.io.InputStream;

import iostream.CloseChain;
import iostream.SubjectHolder;

public interface ByteSource<T> extends SubjectHolder<T> {

    InputStream getInputStream(CloseChain toClose) throws IOException;

}
