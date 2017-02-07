package iostream.proxy;

import java.io.IOException;
import java.nio.charset.Charset;

import iostream.Closer;
import iostream.flow.ByteSource;
import iostream.proxy.stream.BufferedInputProxy;
import iostream.proxy.stream.DataInputProxy;
import iostream.proxy.stream.ObjectInputProxy;
import iostream.proxy.stream.ZipInputProxy;

public interface InStreamBuilder<T> {

    ByteSource<T> getByteSource();

    default ZipInputProxy<T> zipInputStream() throws IOException {
	Closer toClose = new Closer();
	return new ZipInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));
    }

    default ZipInputProxy<T> zipInputStream(Charset cs) throws IOException {
	Closer toClose = new Closer();
	return new ZipInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose), cs);
    }
    
    default BufferedInputProxy<T> bufferedInputStream() throws IOException {
	Closer toClose = new Closer();
	return new BufferedInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));
    }
    
    default BufferedInputProxy<T> bufferedInputStream(int bufferSize) throws IOException {
	Closer toClose = new Closer();
	return new BufferedInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose), bufferSize);
    }    

    default DataInputProxy<T> dataInputStream() throws IOException {
	Closer toClose = new Closer();
	return new DataInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));

    }

    default ObjectInputProxy<T> objectInputStream() throws IOException {
	Closer toClose = new Closer();
	return new ObjectInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));

    }

}
