package iostream.proxy.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import iostream.Closer;
import iostream.SubjectHolder;

public class BufferedWriterProxy<T> extends BufferedWriter implements SubjectHolder<T> {

    final Closer closer;

    final SubjectHolder<T> realTarget;

    public BufferedWriterProxy(SubjectHolder<T> t, Closer cl, Writer wr) throws IOException {
	super(wr);
	realTarget = t;
	cl.register(wr);
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