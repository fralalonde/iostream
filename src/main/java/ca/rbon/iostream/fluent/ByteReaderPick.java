package ca.rbon.iostream.fluent;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.writer.BufferedReaderOf;
import ca.rbon.iostream.writer.ReaderOf;

public interface ByteReaderPick<T> extends CharReaderPick<T> {
    
    // UNBUFFERED
    
    ReaderOf<T> reader(Charset charset) throws IOException;
    
    default ReaderOf<T> reader(String charsetName) throws IOException {
        return reader(Charset.forName(charsetName));
    }
    
    // BUFFERED
    
    BufferedReaderOf<T> bufferedReader(Charset charset, int bufferSize) throws IOException;
    
    default BufferedReaderOf<T> bufferedReader(Charset charset) throws IOException {
        return bufferedReader(charset, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    default BufferedReaderOf<T> bufferedReader(String charsetName) throws IOException {
        return bufferedReader(Charset.forName(charsetName), Buffering.DEFAULT_BUFFER_SIZE);
    }
    
}
