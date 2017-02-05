package iostream.proxy.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import iostream.Closer;

public class ObjectInputProxy extends ObjectInputStream {

    final Closer closer;

    public ObjectInputProxy(Closer cl, InputStream is) throws IOException {
	super(is);
	cl.register(is);
	closer = cl;
    }

    public void close() {
	closer.closeAll();
    }

}