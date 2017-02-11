package ca.rbon.iostream.fluent;

import java.io.IOException;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.writer.BufferedReaderOf;
import ca.rbon.iostream.writer.ReaderOf;

public interface StraightReaderPick<T> {
    
    ReaderOf<T> reader() throws IOException;
    
    BufferedReaderOf<T> bufferedReader(int bufferSize) throws IOException;
    
    default BufferedReaderOf<T> bufferedReader() throws IOException {
        return bufferedReader(Buffering.DEFAULT_BUFFER_SIZE);
    }    
    
}