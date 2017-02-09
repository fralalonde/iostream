package ca.rbon.iostream.picker;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;

public class Encoding {
    
    static Reader encode(InputStream stream, Charset encoding, Chain chain) {
        return chain.add(encoding == null ? new InputStreamReader(stream) : new InputStreamReader(stream, encoding));
    }
    
    static Writer encode(OutputStream stream, Charset encoding, Chain chain) {
        return chain.add(encoding == null ? new OutputStreamWriter(stream) : new OutputStreamWriter(stream, encoding));
    }
    
}
