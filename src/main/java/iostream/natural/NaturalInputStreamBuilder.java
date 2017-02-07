package iostream.natural;

import java.io.IOException;
import java.io.InputStream;

public interface NaturalInputStreamBuilder<IS extends InputStream> {

    IS inputStream() throws IOException;
    
}
