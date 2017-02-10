package ca.rbon.iostream;

import java.io.IOException;

public interface Resource<T> {
    
    T getResource() throws IOException;
    
}
