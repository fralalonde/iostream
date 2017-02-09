package ca.rbon.iostream.fluent;

import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.UnbufferedReaderProxy;

public interface ReaderPick<T> {
    
    UnbufferedReaderProxy<T> writer();
    
    BufferedReaderProxy<T> bufferedReader();    
    
}
