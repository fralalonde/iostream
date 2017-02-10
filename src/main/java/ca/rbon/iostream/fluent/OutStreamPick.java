package ca.rbon.iostream.fluent;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.picker.Encoding;
import ca.rbon.iostream.proxy.stream.BufferedOutputProxy;
import ca.rbon.iostream.proxy.stream.DataOutputProxy;
import ca.rbon.iostream.proxy.stream.ObjectOutputProxy;
import ca.rbon.iostream.proxy.stream.OutputStreamProxy;
import ca.rbon.iostream.proxy.stream.ZipOutputProxy;

public interface OutStreamPick<T> {
    
    OutputStreamProxy<T> outputStream() throws IOException;
    
    BufferedOutputProxy<T> bufferedOutputStream(int bufferSize) throws IOException;
    
    default BufferedOutputProxy<T> bufferedOutputStream() throws IOException {
        return bufferedOutputStream(Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    ZipOutputProxy<T> zipOutputStream(Charset charset, int bufferSize) throws IOException;
    
    default ZipOutputProxy<T> zipOutputStream(String charsetName, int bufferSize) throws IOException {
        return zipOutputStream(Charset.forName(charsetName), bufferSize);
    }
    
    default ZipOutputProxy<T> zipOutputStream(Charset charset) throws IOException {
        return zipOutputStream(charset, Buffering.UNBUFFERED);
    }
    
    default ZipOutputProxy<T> zipOutputStream(String charsetName) throws IOException {
        return zipOutputStream(charsetName, Buffering.UNBUFFERED);
    }
    
    default ZipOutputProxy<T> zipOutputStream(int bufferSize) throws IOException {
        return zipOutputStream(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    default ZipOutputProxy<T> zipOutputStream() throws IOException {
        return zipOutputStream(Encoding.DEFAULT_CHARSET, Buffering.UNBUFFERED);
    }
    
    DataOutputProxy<T> dataOutputStream(int bufferSize) throws IOException;
    
    default DataOutputProxy<T> dataOutputStream() throws IOException {
        return dataOutputStream(Buffering.UNBUFFERED);
    }
    
    ObjectOutputProxy<T> objectOutputStream(int bufferSize) throws IOException;
    
    default ObjectOutputProxy<T> objectOutputStream() throws IOException {
        return objectOutputStream(Buffering.UNBUFFERED);
        
    }
    
}
