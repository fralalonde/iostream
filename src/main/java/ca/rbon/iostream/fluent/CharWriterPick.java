package ca.rbon.iostream.fluent;

import java.io.IOException;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.writer.BufferedWriterOf;
import ca.rbon.iostream.writer.PrintWriterOf;
import ca.rbon.iostream.writer.WriterOf;

public interface CharWriterPick<T> {
    
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
