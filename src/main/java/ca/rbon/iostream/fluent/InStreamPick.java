package ca.rbon.iostream.fluent;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.picker.Encoding;
import ca.rbon.iostream.stream.BufferedInputOf;
import ca.rbon.iostream.stream.DataInputOf;
import ca.rbon.iostream.stream.InputStreamOf;
import ca.rbon.iostream.stream.ObjectInputOf;
import ca.rbon.iostream.stream.ZipInputOf;

public interface InStreamPick<T> {
    
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
    
    DataInputOf<T> dataInputStream(int bufferSize) throws IOException;

    default DataInputOf<T> dataInputStream() throws IOException {
        return dataInputStream(Buffering.UNBUFFERED);
    }
    
    ObjectInputOf<T> objectInputStream(int bufferSize) throws IOException;

    default ObjectInputOf<T> objectInputStream() throws IOException {
        return objectInputStream(Buffering.UNBUFFERED);
    }
    
}
