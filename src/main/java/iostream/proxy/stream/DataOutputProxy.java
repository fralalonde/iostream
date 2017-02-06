package iostream.proxy.stream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import iostream.Closer;
import iostream.SubjectHolder;

public class DataOutputProxy<T> extends DataOutputStream implements SubjectHolder<T> {

    final Closer closer;

    final SubjectHolder<T> realTarget;

    public DataOutputProxy(SubjectHolder<T> t, Closer cl, OutputStream os) throws IOException {
	super(os);
	realTarget = t;
	cl.register(os);
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