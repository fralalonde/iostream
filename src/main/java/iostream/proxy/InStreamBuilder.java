package iostream.proxy;

import java.io.IOException;

import iostream.Closer;
import iostream.flow.ByteSource;
import iostream.proxy.stream.BufferedInputProxy;
import iostream.proxy.stream.DataInputProxy;
import iostream.proxy.stream.ObjectInputProxy;
import iostream.proxy.stream.ZipInputProxy;

public interface InStreamBuilder<T> {

    ByteSource<T> getByteSource();

    default ZipInputProxy<T> zipInput() throws IOException {
	Closer toClose = new Closer(ex -> {
	});
	return new ZipInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));
    }

    default BufferedInputProxy<T> bufferedInput() throws IOException {
	Closer toClose = new Closer(ex -> {
	});
	return new BufferedInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));
    }

    default DataInputProxy<T> dataInput() throws IOException {
	Closer toClose = new Closer(ex -> {
	});
	return new DataInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));

    }

    default ObjectInputProxy<T> objectInput() throws IOException {
	Closer toClose = new Closer(ex -> {
	});
	return new ObjectInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));

    }

}
