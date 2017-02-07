package iostream.natural;

import java.io.IOException;
import java.io.Writer;

public interface NaturalWriterBuilder<W extends Writer> {
    
    W writer() throws IOException;
    
}
