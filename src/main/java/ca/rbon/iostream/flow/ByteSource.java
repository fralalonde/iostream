package ca.rbon.iostream.flow;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.Resource;

public interface ByteSource<T> extends Resource<T> {
    
    InputStream getInputStream(Chain toClose) throws IOException;
    
    Charset getEncoding();    
    
}
