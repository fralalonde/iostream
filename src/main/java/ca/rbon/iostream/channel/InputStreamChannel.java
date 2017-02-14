package ca.rbon.iostream.channel;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.proxy.BufferedInputOf;
import ca.rbon.iostream.proxy.DataInputOf;
import ca.rbon.iostream.proxy.GZipInputOf;
import ca.rbon.iostream.proxy.InputStreamOf;
import ca.rbon.iostream.proxy.ObjectInputOf;
import ca.rbon.iostream.proxy.ZipInputOf;
import ca.rbon.iostream.resource.Buffering;
import ca.rbon.iostream.resource.Encoding;

public interface InputStreamChannel<T> {
    
    InputStreamOf<T> inputStream() throws IOException;
    
    BufferedInputOf<T> bufferedInputStream(int bufferSize) throws IOException;
    
    default BufferedInputOf<T> bufferedInputStream() throws IOException {
        return bufferedInputStream(Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    ZipInputOf<T> zipInputStream(Charset charset, int bufferSize) throws IOException;
    
    default ZipInputOf<T> zipInputStream(String charsetName, int bufferSize) throws IOException {
        return zipInputStream(Charset.forName(charsetName), bufferSize);
    }
    
    default ZipInputOf<T> zipInputStream(Charset charset) throws IOException {
        return zipInputStream(charset, Buffering.UNBUFFERED);
    }
    
    default ZipInputOf<T> zipInputStream(String charsetName) throws IOException {
        return zipInputStream(charsetName, Buffering.UNBUFFERED);
    }
    
    default ZipInputOf<T> zipInputStream(int bufferSize) throws IOException {
        return zipInputStream(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    default ZipInputOf<T> zipInputStream() throws IOException {
        return zipInputStream(Encoding.DEFAULT_CHARSET, Buffering.UNBUFFERED);
    }
    
    GZipInputOf<T> gzipInputStream(int bufferSize) throws IOException;
    
    default GZipInputOf<T> gzipInputStream() throws IOException {
        return gzipInputStream(Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    DataInputOf<T> dataInputStream(int bufferSize) throws IOException;
    
    default DataInputOf<T> dataInputStream() throws IOException {
        return dataInputStream(Buffering.UNBUFFERED);
    }
    
    ObjectInputOf<T> objectInputStream(int bufferSize) throws IOException;
    
    default ObjectInputOf<T> objectInputStream() throws IOException {
        return objectInputStream(Buffering.UNBUFFERED);
    }
    
}
