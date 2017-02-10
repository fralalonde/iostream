package ca.rbon.iostream.fluent;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.picker.Encoding;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.UnbufferedReaderProxy;

public interface ReaderPick<T> {
    
    // UNBUFFERED
    
    UnbufferedReaderProxy<T> reader(Charset charset) throws IOException;
    
    default UnbufferedReaderProxy<T> reader() throws IOException {
        return reader(Encoding.DEFAULT_CHARSET);
    }
    
    default UnbufferedReaderProxy<T> reader(String charsetName) throws IOException {
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
    
    default BufferedReaderProxy<T> bufferedReader(int bufferSize) throws IOException {
        return bufferedReader(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    default BufferedReaderProxy<T> bufferedReader() throws IOException {
        return bufferedReader(Encoding.DEFAULT_CHARSET, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
}
