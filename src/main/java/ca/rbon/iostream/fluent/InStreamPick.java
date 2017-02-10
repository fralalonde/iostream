package ca.rbon.iostream.fluent;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.picker.Encoding;
import ca.rbon.iostream.proxy.stream.BufferedInputProxy;
import ca.rbon.iostream.proxy.stream.DataInputProxy;
import ca.rbon.iostream.proxy.stream.ObjectInputProxy;
import ca.rbon.iostream.proxy.stream.InputStreamProxy;
import ca.rbon.iostream.proxy.stream.ZipInputProxy;

public interface InStreamPick<T> {
    
    InputStreamProxy<T> inputStream() throws IOException;
    
    BufferedInputProxy<T> bufferedInputStream(int bufferSize) throws IOException;
    
    default BufferedInputProxy<T> bufferedInputStream() throws IOException {
        return bufferedInputStream(Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    ZipInputProxy<T> zipInputStream(Charset charset, int bufferSize) throws IOException;
    
    default ZipInputProxy<T> zipInputStream(String charsetName, int bufferSize) throws IOException {
        return zipInputStream(Charset.forName(charsetName), bufferSize);
    }
    
    default ZipInputProxy<T> zipInputStream(Charset charset) throws IOException {
        return zipInputStream(charset, Buffering.UNBUFFERED);
    }
    
    default ZipInputProxy<T> zipInputStream(String charsetName) throws IOException {
        return zipInputStream(charsetName, Buffering.UNBUFFERED);
    }
    
    default ZipInputProxy<T> zipInputStream(int bufferSize) throws IOException {
        return zipInputStream(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    default ZipInputProxy<T> zipInputStream() throws IOException {
        return zipInputStream(Encoding.DEFAULT_CHARSET, Buffering.UNBUFFERED);
    }
    
    DataInputProxy<T> dataInputStream(int bufferSize) throws IOException;

    default DataInputProxy<T> dataInputStream() throws IOException {
        return dataInputStream(Buffering.UNBUFFERED);
    }
    
    ObjectInputProxy<T> objectInputStream(int bufferSize) throws IOException;

    default ObjectInputProxy<T> objectInputStream() throws IOException {
        return objectInputStream(Buffering.UNBUFFERED);
    }
    
}
