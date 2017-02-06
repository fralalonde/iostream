package iostream.proxy.stream;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipOutputStream;

import iostream.Closer;
import iostream.SinkTarget;
import lombok.Getter;

public class ZipOutputProxy<T> extends ZipOutputStream implements SinkTarget<T> {

    final Closer closer;

    @Getter
    final SinkTarget<T> realTarget;

    public ZipOutputProxy(SinkTarget<T> t, Closer cl, OutputStream os) {
	super(os);
	realTarget = t;
	cl.register(os);
	closer = cl;
    }

    public ZipOutputProxy(SinkTarget<T> t, Closer cl, OutputStream os, Charset cs) {
	super(os, cs);
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