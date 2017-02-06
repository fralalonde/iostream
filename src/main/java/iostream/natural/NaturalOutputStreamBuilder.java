package iostream.natural;

import java.io.IOException;
import java.io.OutputStream;

public interface NaturalOutputStreamBuilder<OS extends OutputStream> {

    OS output() throws IOException;
    
}
