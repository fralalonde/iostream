package iostream.proxy.stream;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import iostream.Closer;

public class ZipInputProxy extends ZipInputStream {

    final Closer closer;

    public ZipInputProxy(Closer cl, InputStream is) {
	super(is);
	cl.register(is);
	closer = cl;
    }

    public void close() {
	closer.closeAll();
    }

}