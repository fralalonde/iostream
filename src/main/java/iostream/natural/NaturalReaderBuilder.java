package iostream.natural;

import java.io.IOException;
import java.io.Reader;

public interface NaturalReaderBuilder<R extends Reader> {
    
    R reader() throws IOException;
    
}
