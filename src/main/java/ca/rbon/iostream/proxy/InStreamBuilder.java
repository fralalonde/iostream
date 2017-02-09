package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.flow.ByteSource;
import ca.rbon.iostream.proxy.stream.BufferedInputProxy;
import ca.rbon.iostream.proxy.stream.DataInputProxy;
import ca.rbon.iostream.proxy.stream.ObjectInputProxy;
import ca.rbon.iostream.proxy.stream.ZipInputProxy;

public interface InStreamBuilder<T> {
    
    ByteSource<T> getByteSource();
    
    default ZipInputProxy<T> zipInputStream() throws IOException {
        ChainClose toClose = new ChainClose();
        return new ZipInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));
    }
    
    default ZipInputProxy<T> zipInputStream(Charset charset) throws IOException {
        ChainClose toClose = new ChainClose();
        return new ZipInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose), charset);
    }
    
    default ZipInputProxy<T> zipInputStream(String charsetName) throws IOException {
        ChainClose toClose = new ChainClose();
        return new ZipInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose), Charset.forName(charsetName));
    }
    
    default BufferedInputProxy<T> bufferedInputStream() throws IOException {
        ChainClose toClose = new ChainClose();
        return new BufferedInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));
    }
    
    default BufferedInputProxy<T> bufferedInputStream(int bufferSize) throws IOException {
        ChainClose toClose = new ChainClose();
        return new BufferedInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose), bufferSize);
    }
    
    default DataInputProxy<T> dataInputStream() throws IOException {
        ChainClose toClose = new ChainClose();
        return new DataInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));
        
    }
    
    default ObjectInputProxy<T> objectInputStream() throws IOException {
        ChainClose toClose = new ChainClose();
        return new ObjectInputProxy<>(getByteSource(), toClose, getByteSource().getInputStream(toClose));
        
    }
    
}
