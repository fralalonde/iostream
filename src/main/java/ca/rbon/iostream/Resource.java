package ca.rbon.iostream;

import java.nio.charset.Charset;

public interface Resource<T> {
    
    T getResource();
    
    Charset getEncoding();
    
}
