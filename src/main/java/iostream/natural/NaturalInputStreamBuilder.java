package iostream.natural;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface NaturalInputStreamBuilder<IS extends InputStream> {

    IS input() throws FileNotFoundException;
    
}
