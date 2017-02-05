package iostream.proxy.builder;

import java.io.IOException;

import iostream.Closer;
import iostream.flow.ByteSource;
import iostream.proxy.stream.BufferedInputProxy;
import iostream.proxy.stream.DataInputProxy;
import iostream.proxy.stream.ObjectInputProxy;
import iostream.proxy.stream.ZipInputProxy;

public interface InStreamBuilder {

    ByteSource getByteSource();
    
    default ZipInputProxy zipInput()  throws IOException {
	Closer toClose = new Closer(ex -> {});
	return new ZipInputProxy(toClose, getByteSource().getInputStream(toClose));	
    }    
    
    default BufferedInputProxy bufferedInput() throws IOException {
	Closer toClose = new Closer(ex -> {});
	return new BufferedInputProxy(toClose,getByteSource().getInputStream(toClose));		
    }

    default DataInputProxy dataInput() throws IOException {
	Closer toClose = new Closer(ex -> {});
	return new DataInputProxy(toClose,getByteSource().getInputStream(toClose));		
	
    }

    default ObjectInputProxy objectInput() throws IOException {
	Closer toClose = new Closer(ex -> {});
	return new ObjectInputProxy(toClose,getByteSource().getInputStream(toClose));		
	
    }

}
