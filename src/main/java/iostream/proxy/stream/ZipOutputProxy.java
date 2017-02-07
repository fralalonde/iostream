package iostream.proxy.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipOutputStream;

import iostream.Closer;
import iostream.ResourceHolder;

public class ZipOutputProxy<T> extends ZipOutputStream implements ResourceHolder<T> {

    final Closer closer;

    final ResourceHolder<T> holder;

    public ZipOutputProxy(ResourceHolder<T> t, Closer cl, OutputStream os) {
	super(os);
	holder = t;
	cl.add(os);
	closer = cl;
    }

    public ZipOutputProxy(ResourceHolder<T> t, Closer cl, OutputStream os, Charset cs) {
	super(os, cs);
	holder = t;
	cl.add(os);
	closer = cl;
    }

    @Override
    public void close() throws IOException {
	closer.closeAll();
    }

    @Override
    public T getResource() {
	return holder.getResource();
    }

}