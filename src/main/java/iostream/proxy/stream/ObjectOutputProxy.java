package iostream.proxy.stream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import iostream.Closer;
import iostream.SinkTarget;
import lombok.Getter;

public class ObjectOutputProxy<T> extends ObjectOutputStream implements SinkTarget<T> {

    final Closer closer;

    @Getter
    final SinkTarget<T> realTarget;

    public ObjectOutputProxy(SinkTarget<T> t, Closer cl, OutputStream os) throws IOException {
	super(os);
	realTarget = t;
	cl.register(os);
	closer = cl;
    }

    public void close() {
	closer.closeAll();
    }

    @Override
    public T getTarget() {
	return realTarget.getTarget();
    }
   
}