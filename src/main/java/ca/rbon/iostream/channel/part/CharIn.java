package ca.rbon.iostream.channel.part;

import java.io.IOException;

import wrap.BufferedReaderOf;
import wrap.ReaderOf;

public interface CharIn<T> {
    
    ReaderOf<T> reader() throws IOException;
    
    BufferedReaderOf<T> bufferedReader(int bufferSize) throws IOException;
    
    BufferedReaderOf<T> bufferedReader() throws IOException;
    
}
