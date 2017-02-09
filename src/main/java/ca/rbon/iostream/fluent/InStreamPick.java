package ca.rbon.iostream.fluent;

import java.io.IOException;

import ca.rbon.iostream.proxy.stream.BufferedInputProxy;
import ca.rbon.iostream.proxy.stream.DataInputProxy;
import ca.rbon.iostream.proxy.stream.ObjectInputProxy;
import ca.rbon.iostream.proxy.stream.UnbufferedInputProxy;

public interface InStreamPick<T> {
    
    UnbufferedInputProxy<T> inputStream() throws IOException;
    
    BufferedInputProxy<T> bufferedInputStream() throws IOException;
    
    DataInputProxy<T> dataInputStream() throws IOException;
    
    ObjectInputProxy<T> objectInputStream() throws IOException;
    
}
