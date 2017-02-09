package ca.rbon.iostream.natural;

import java.io.IOException;
import java.io.Reader;

public interface NaturalReader<R extends Reader> {
    
    R reader() throws IOException;
    
}
