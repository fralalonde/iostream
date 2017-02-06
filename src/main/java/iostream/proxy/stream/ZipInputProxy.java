package iostream.proxy.stream;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import iostream.Closer;
import iostream.SubjectHolder;

public class ZipInputProxy<T> extends ZipInputStream implements SubjectHolder<T> {

    final Closer closer;
    final SubjectHolder<T> realTarget;

    public ZipInputProxy(SubjectHolder<T> t, Closer cl, InputStream is) {
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