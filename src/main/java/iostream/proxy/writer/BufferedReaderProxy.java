package iostream.proxy.writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import iostream.Closer;
import iostream.SinkTarget;

public class BufferedReaderProxy<T> extends BufferedReader implements SinkTarget<T> {

    final Closer closer;
    
    final SinkTarget<T> realTarget;    

    public BufferedReaderProxy(SinkTarget<T> t, Closer cl, Reader r) throws IOException {
	super(r);
	realTarget = t;	
	cl.register(r);
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