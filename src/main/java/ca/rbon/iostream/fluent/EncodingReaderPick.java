package ca.rbon.iostream.fluent;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.ReaderProxy;

public interface EncodingReaderPick<T> extends StraightReaderPick<T> {
    
    // UNBUFFERED
    
    ReaderProxy<T> reader(Charset charset) throws IOException;
    
    default ReaderProxy<T> reader(String charsetName) throws IOException {
        return reader(Charset.forName(charsetName));
    }
    
    // BUFFERED
    
    BufferedReaderProxy<T> bufferedReader(Charset charset, int bufferSize) throws IOException;
    
    default BufferedReaderProxy<T> bufferedReader(Charset charset) throws IOException {
        return bufferedReader(charset, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    default BufferedReaderProxy<T> bufferedReader(String charsetName) throws IOException {
        return bufferedReader(Charset.forName(charsetName), Buffering.DEFAULT_BUFFER_SIZE);
    }
    
}
