package iostream.proxy.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import iostream.Closer;
import iostream.SubjectHolder;

public class ObjectInputProxy<T> extends ObjectInputStream implements SubjectHolder<T> {

    final Closer closer;

    final SubjectHolder<T> realTarget;

    public ObjectInputProxy(SubjectHolder<T> t, Closer cl, InputStream is) throws IOException {
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