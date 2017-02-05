package iostream.flow;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import iostream.CloseChain;

public interface Source extends ByteSource, CharSource {

    default Reader getNativeReaderOrNull(CloseChain toClose) throws IOException {
	return null;
    }

    default Reader getReader(CloseChain toClose) throws IOException {
	Reader nativeReader = getNativeReaderOrNull(toClose);
	return nativeReader != null ? nativeReader : new InputStreamReader(getInputStream(toClose));
    }

}
