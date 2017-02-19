package ca.rbon.iostream.channel.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FilterFactory {
    
    InputStream filterInput(InputStream input) throws IOException;
    
    OutputStream filterOutput(OutputStream output) throws IOException;
    
}
