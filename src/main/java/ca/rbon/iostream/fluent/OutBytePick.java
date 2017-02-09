package ca.rbon.iostream.fluent;

import java.io.IOException;

import ca.rbon.iostream.proxy.stream.BufferedOutputProxy;
import ca.rbon.iostream.proxy.stream.DataOutputProxy;
import ca.rbon.iostream.proxy.stream.ObjectOutputProxy;
import ca.rbon.iostream.proxy.stream.ZipOutputProxy;

public interface OutBytePick<T> {
    
    ZipOutputProxy<T> zipOutputStream() throws IOException;
        
    BufferedOutputProxy<T> bufferedOutputStream() throws IOException;
    
    DataOutputProxy<T> dataOutputStream() throws IOException;
    
    ObjectOutputProxy<T> objectOutputStream() throws IOException;
    
    
}
