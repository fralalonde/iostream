package ca.rbon.iostream;

import java.io.Closeable;

public interface Chain {
    
    
    <T extends Closeable> T chainAdd(T closeable);
    
}
