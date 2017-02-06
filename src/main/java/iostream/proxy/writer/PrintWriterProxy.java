package iostream.proxy.writer;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import iostream.Closer;
import iostream.SubjectHolder;

public class PrintWriterProxy<T> extends PrintWriter implements SubjectHolder<T> {

    final Closer closer;

    final SubjectHolder<T> realTarget;

    public PrintWriterProxy(SubjectHolder<T> t, Closer cl, Writer w) {
	super(w);
	realTarget = t;
	cl.register(w);
	closer = cl;
    }

    public PrintWriterProxy(SubjectHolder<T> t, Closer cl, OutputStream os) {
	super(os);
	realTarget = t;
	cl.register(os);
	closer = cl;
    }

    public PrintWriterProxy(SubjectHolder<T> t, Closer cl, Writer w, boolean autoFlush) {
	super(w, autoFlush);
	realTarget = t;
	cl.register(w);
	closer = cl;
    }

    public PrintWriterProxy(SubjectHolder<T> t, Closer cl, OutputStream os, boolean autoFlush) {
	super(os, autoFlush);
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