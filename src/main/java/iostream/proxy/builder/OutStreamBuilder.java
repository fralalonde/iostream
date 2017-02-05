package iostream.proxy.builder;

import java.io.IOException;
import java.nio.charset.Charset;

import iostream.Closer;
import iostream.flow.ByteSink;
import iostream.proxy.stream.BufferedOutputProxy;
import iostream.proxy.stream.DataOutputProxy;
import iostream.proxy.stream.ObjectOutputProxy;
import iostream.proxy.stream.ZipOutputProxy;

public interface OutStreamBuilder<T> {

    ByteSink<T> getByteSink();
    
    default ZipOutputProxy<T> zipOutput()  throws IOException {
	Closer toClose = new Closer(ex -> {});
	return new ZipOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));	
    }

    default ZipOutputProxy<T> zipOutput(Charset cs)  throws IOException {
	Closer toClose = new Closer(ex -> {});
	return new ZipOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose), cs);	
    }
    
    default BufferedOutputProxy<T> bufferedOutput() throws IOException {
	Closer toClose = new Closer(ex -> {});
	return new BufferedOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));	
    }

    default DataOutputProxy<T> dataOutput() throws IOException {
	Closer toClose = new Closer(ex -> {});
	return new DataOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));
    }

    default ObjectOutputProxy<T> objectOutput() throws IOException {
	Closer toClose = new Closer(ex -> {});
	return new ObjectOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));
    }

}
