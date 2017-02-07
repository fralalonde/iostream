package iostream.proxy;

import java.io.IOException;
import java.nio.charset.Charset;

import iostream.Closer;
import iostream.flow.ByteSink;
import iostream.proxy.stream.BufferedOutputProxy;
import iostream.proxy.stream.DataOutputProxy;
import iostream.proxy.stream.ObjectOutputProxy;
import iostream.proxy.stream.ZipOutputProxy;

public interface OutStreamBuilder<T> {

    ByteSink<T> getByteSink() throws IOException;
    
    default ZipOutputProxy<T> zipOutputStream()  throws IOException {
	Closer toClose = new Closer();
	return new ZipOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));	
    }

    default ZipOutputProxy<T> zipOutputStream(Charset charset)  throws IOException {
	Closer toClose = new Closer();
	return new ZipOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose), charset);	
    }

    default ZipOutputProxy<T> zipOutputStream(String charsetName)  throws IOException {
	Closer toClose = new Closer();
	return new ZipOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose), Charset.forName(charsetName));	
    }
    
    default BufferedOutputProxy<T> bufferedOutputStream() throws IOException {
	Closer toClose = new Closer();
	return new BufferedOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));	
    }

    default DataOutputProxy<T> dataOutputStream() throws IOException {
	Closer toClose = new Closer();
	return new DataOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));
    }

    default ObjectOutputProxy<T> objectOutputStream() throws IOException {
	Closer toClose = new Closer();
	return new ObjectOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));
    }

}
