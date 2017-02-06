package iostream.proxy.writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import iostream.Closer;
import iostream.SubjectHolder;

public class BufferedReaderProxy<T> extends BufferedReader implements SubjectHolder<T> {

    final Closer closer;
    
    final SubjectHolder<T> realTarget;

    public BufferedReaderProxy(SubjectHolder<T> t, Closer cl, Reader r) throws IOException {
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