package iostream.proxy.builder;

import java.io.IOException;

import iostream.Closer;
import iostream.flow.CharSource;
import iostream.proxy.writer.BufferedReaderProxy;

public interface ReaderBuilder {

    CharSource getCharSource() throws IOException;

    default BufferedReaderProxy bufferedReader() throws IOException {
	Closer toClose = new Closer(ex -> {});
	return new BufferedReaderProxy(toClose, getCharSource().getReader(toClose));		
    }

}
