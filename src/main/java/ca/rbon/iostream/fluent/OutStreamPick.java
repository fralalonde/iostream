package ca.rbon.iostream.fluent;

import java.io.IOException;

import ca.rbon.iostream.proxy.stream.BufferedOutputProxy;
import ca.rbon.iostream.proxy.stream.DataOutputProxy;
import ca.rbon.iostream.proxy.stream.ObjectOutputProxy;
import ca.rbon.iostream.proxy.stream.UnbufferedOutputProxy;

public interface OutStreamPick<T> {

    UnbufferedOutputProxy<T> outputStream();    
    
    BufferedOutputProxy<T> bufferedOutputStream();    
    
    DataOutputProxy<T> dataOutputStream() throws IOException;
    
    ObjectOutputProxy<T> objectOutputStream() throws IOException;
    
}
