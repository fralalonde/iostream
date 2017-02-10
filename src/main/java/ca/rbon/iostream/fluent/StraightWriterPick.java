package ca.rbon.iostream.fluent;

import java.io.IOException;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;
import ca.rbon.iostream.proxy.writer.WriterProxy;

public interface StraightWriterPick<T> {
    
    WriterProxy<T> writer() throws IOException;
    
    BufferedWriterProxy<T> bufferedWriter(int bufferSize) throws IOException;
    
    default BufferedWriterProxy<T> bufferedWriter() throws IOException {
        return bufferedWriter(Buffering.DEFAULT_BUFFER_SIZE);        
    }
    
    PrintWriterProxy<T> printWriter(int bufferSize) throws IOException;
    
    default PrintWriterProxy<T> printWriter() throws IOException {
        return printWriter(Buffering.DEFAULT_BUFFER_SIZE);        
    }
    
    PrintWriterProxy<T> printWriter(int bufferSize, boolean autoflush) throws IOException;
    
    default PrintWriterProxy<T> printWriter(boolean autoflush) throws IOException {
        return printWriter(Buffering.DEFAULT_BUFFER_SIZE, autoflush);
    }
    
}
