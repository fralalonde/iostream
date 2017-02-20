package ca.rbon.iostream.channel.part;

import java.io.IOException;

import ca.rbon.iostream.wrap.BufferedWriterOf;
import ca.rbon.iostream.wrap.PrintWriterOf;
import ca.rbon.iostream.wrap.WriterOf;

public interface CharOut<T> {
    
    WriterOf<T> writer() throws IOException;
    
    BufferedWriterOf<T> bufferedWriter(int bufferSize) throws IOException;
    
    BufferedWriterOf<T> bufferedWriter() throws IOException;
    
    PrintWriterOf<T> printWriter(int bufferSize) throws IOException;
    
    PrintWriterOf<T> printWriter() throws IOException;
    
    PrintWriterOf<T> printWriter(int bufferSize, boolean autoflush) throws IOException;
    
    PrintWriterOf<T> printWriter(boolean autoflush) throws IOException;
    
}
