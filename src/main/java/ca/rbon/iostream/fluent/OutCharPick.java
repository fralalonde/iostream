package ca.rbon.iostream.fluent;

import java.io.IOException;

import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;

public interface OutCharPick<T> {
    
    PrintWriterProxy<T> printWriter() throws IOException;
    
    PrintWriterProxy<T> printWriter(boolean autoflush) throws IOException;
    
    BufferedWriterProxy<T> bufferedWriter() throws IOException;
    
}
