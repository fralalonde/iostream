package ca.rbon.iostream.channel;

import java.io.IOException;

import ca.rbon.iostream.proxy.BufferedWriterOf;
import ca.rbon.iostream.proxy.PrintWriterOf;
import ca.rbon.iostream.proxy.WriterOf;
import ca.rbon.iostream.resource.Buffering;

public interface CharWriterChannel<T> {
    
    WriterOf<T> writer() throws IOException;
    
    BufferedWriterOf<T> bufferedWriter(int bufferSize) throws IOException;
    
    default BufferedWriterOf<T> bufferedWriter() throws IOException {
        return bufferedWriter(Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    PrintWriterOf<T> printWriter(int bufferSize) throws IOException;
    
    default PrintWriterOf<T> printWriter() throws IOException {
        return printWriter(Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    PrintWriterOf<T> printWriter(int bufferSize, boolean autoflush) throws IOException;
    
    default PrintWriterOf<T> printWriter(boolean autoflush) throws IOException {
        return printWriter(Buffering.DEFAULT_BUFFER_SIZE, autoflush);
    }
    
}
