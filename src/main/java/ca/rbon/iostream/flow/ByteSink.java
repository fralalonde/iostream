package ca.rbon.iostream.flow;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.Resource;

public interface ByteSink<T> extends Resource<T> {
    
    OutputStream getOutputStream(Chain toClose) throws IOException;
    
    Charset getEncoding();
        
}
