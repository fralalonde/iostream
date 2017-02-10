package ca.rbon.iostream.fluent;

import java.io.IOException;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.ReaderProxy;

public interface StraightReaderPick<T> {
    
    ReaderProxy<T> reader() throws IOException;
    
    BufferedReaderProxy<T> bufferedReader(int bufferSize) throws IOException;
    
    default BufferedReaderProxy<T> bufferedReader() throws IOException {
        return bufferedReader(Buffering.DEFAULT_BUFFER_SIZE);
    }    
    
}