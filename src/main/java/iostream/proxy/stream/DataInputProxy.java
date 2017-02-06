package iostream.proxy.stream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import iostream.Closer;
import iostream.SubjectHolder;

public class DataInputProxy<T> extends DataInputStream implements SubjectHolder<T> {

    final Closer closer;

    final SubjectHolder<T> realTarget;

    public DataInputProxy(SubjectHolder<T> t, Closer cl, InputStream is) throws IOException {
	super(is);
	realTarget = t;
	cl.register(is);
	closer = cl;
    }

    public void close() {
	closer.closeAll();
    }

    @Override
    public T getSubject() {
	return realTarget.getSubject();
    }

}