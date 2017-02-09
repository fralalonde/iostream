package ca.rbon.iostream.natural;

import java.io.IOException;
import java.io.Writer;

public interface NaturalWriter<W extends Writer> {
    
    W writer() throws IOException;
    
}
