package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.flow.ByteSink;
import ca.rbon.iostream.proxy.stream.BufferedOutputProxy;
import ca.rbon.iostream.proxy.stream.DataOutputProxy;
import ca.rbon.iostream.proxy.stream.ObjectOutputProxy;
import ca.rbon.iostream.proxy.stream.ZipOutputProxy;

public interface OutStreamBuilder<T> {
    
    ByteSink<T> getByteSink() throws IOException;
    
    default ZipOutputProxy<T> zipOutputStream() throws IOException {
        ChainClose toClose = new ChainClose();
        return new ZipOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));
    }
    
    default ZipOutputProxy<T> zipOutputStream(Charset charset) throws IOException {
        ChainClose toClose = new ChainClose();
        return new ZipOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose), charset);
    }
    
    default ZipOutputProxy<T> zipOutputStream(String charsetName) throws IOException {
        ChainClose toClose = new ChainClose();
        return new ZipOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose), Charset.forName(charsetName));
    }
    
    default BufferedOutputProxy<T> bufferedOutputStream() throws IOException {
        ChainClose toClose = new ChainClose();
        return new BufferedOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));
    }
    
    default DataOutputProxy<T> dataOutputStream() throws IOException {
        ChainClose toClose = new ChainClose();
        return new DataOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));
    }
    
    default ObjectOutputProxy<T> objectOutputStream() throws IOException {
        ChainClose toClose = new ChainClose();
        return new ObjectOutputProxy<>(getByteSink(), toClose, getByteSink().getOutputStream(toClose));
    }
    
}
