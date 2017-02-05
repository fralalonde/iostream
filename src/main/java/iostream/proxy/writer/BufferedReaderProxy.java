package iostream.proxy.writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import iostream.Closer;

public class BufferedReaderProxy extends BufferedReader {

    final Closer closer;

    public BufferedReaderProxy(Closer cl, Reader r) throws IOException {
	super(r);
	cl.register(r);
	closer = cl;
    }

    public void close() {
	closer.closeAll();
    }

}