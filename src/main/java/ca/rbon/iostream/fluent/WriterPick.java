package ca.rbon.iostream.fluent;

import java.io.IOException;

import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;
import ca.rbon.iostream.proxy.writer.UnbufferedWriterProxy;

public interface WriterPick<T> {
    
    UnbufferedWriterProxy<T> writer();
    
    BufferedWriterProxy<T> bufferedWriter();
    
    PrintWriterProxy<T> printWriter() throws IOException;
    
    PrintWriterProxy<T> printWriter(boolean autoflush) throws IOException;
    
}
