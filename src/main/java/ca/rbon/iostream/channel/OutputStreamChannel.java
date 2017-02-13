package ca.rbon.iostream.channel;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.proxy.BufferedOutputOf;
import ca.rbon.iostream.proxy.DataOutputOf;
import ca.rbon.iostream.proxy.GZipOutputOf;
import ca.rbon.iostream.proxy.ObjectOutputOf;
import ca.rbon.iostream.proxy.OutputStreamOf;
import ca.rbon.iostream.proxy.ZipOutputOf;
import ca.rbon.iostream.resource.Buffering;
import ca.rbon.iostream.resource.Encoding;

public interface OutputStreamChannel<T> {
    
    OutputStreamOf<T> outputStream() throws IOException;
    
    BufferedOutputOf<T> bufferedOutputStream(int bufferSize) throws IOException;
    
    default BufferedOutputOf<T> bufferedOutputStream() throws IOException {
        return bufferedOutputStream(Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    ZipOutputOf<T> zipOutputStream(Charset charset, int bufferSize) throws IOException;
    
    default ZipOutputOf<T> zipOutputStream(String charsetName, int bufferSize) throws IOException {
        return zipOutputStream(Charset.forName(charsetName), bufferSize);
    }
    
    default ZipOutputOf<T> zipOutputStream(Charset charset) throws IOException {
        return zipOutputStream(charset, Buffering.UNBUFFERED);
    }
    
    default ZipOutputOf<T> zipOutputStream(String charsetName) throws IOException {
        return zipOutputStream(charsetName, Buffering.UNBUFFERED);
    }
    
    default ZipOutputOf<T> zipOutputStream(int bufferSize) throws IOException {
        return zipOutputStream(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    default ZipOutputOf<T> zipOutputStream() throws IOException {
        return zipOutputStream(Encoding.DEFAULT_CHARSET, Buffering.UNBUFFERED);
    }
    
    GZipOutputOf<T> gzipOutputStream(int bufferSize) throws IOException;
    
    default GZipOutputOf<T> gzipOutputStream() throws IOException {
        return gzipOutputStream(Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    DataOutputOf<T> dataOutputStream(int bufferSize) throws IOException;
    
    default DataOutputOf<T> dataOutputStream() throws IOException {
        return dataOutputStream(Buffering.UNBUFFERED);
    }
    
    ObjectOutputOf<T> objectOutputStream(int bufferSize) throws IOException;
    
    default ObjectOutputOf<T> objectOutputStream() throws IOException {
        return objectOutputStream(Buffering.UNBUFFERED);
        
    }
    
}
