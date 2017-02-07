package iostream.proxy;

import java.io.IOException;

import iostream.Closer;
import iostream.flow.CharSource;
import iostream.proxy.writer.BufferedReaderProxy;

public interface ReaderBuilder<T> {

    CharSource<T> getCharSource() throws IOException;

    default BufferedReaderProxy<T> bufferedReader() throws IOException {
	Closer toClose = new Closer();
	return new BufferedReaderProxy<>(getCharSource(), toClose, getCharSource().getReader(toClose));		
    }

}
