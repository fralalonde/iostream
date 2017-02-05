package iostream.proxy.stream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import iostream.Closer;

public class BufferedInputProxy extends BufferedInputStream {

    final Closer closer;

    public BufferedInputProxy(Closer cl, InputStream is) throws IOException {
	super(is);
	cl.register(is);
	closer = cl;
    }

    public void close() {
	closer.closeAll();
    }

}