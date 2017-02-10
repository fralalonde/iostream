package ca.rbon.iostream.picker;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

public class Encoding {
    
    public static final Charset DEFAULT_CHARSET = null;

    static Reader encode(InputStream stream, Charset encoding) {
        return encoding == null ? new InputStreamReader(stream) : new InputStreamReader(stream, encoding);
    }
    
    static Writer encode(OutputStream stream, Charset encoding) {
        return encoding == null ? new OutputStreamWriter(stream) : new OutputStreamWriter(stream, encoding);
    }
    
}
