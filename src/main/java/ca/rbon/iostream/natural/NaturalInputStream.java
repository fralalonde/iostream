package ca.rbon.iostream.natural;

import java.io.IOException;
import java.io.InputStream;

public interface NaturalInputStream<IS extends InputStream> {
    
    IS inputStream() throws IOException;
    
}
