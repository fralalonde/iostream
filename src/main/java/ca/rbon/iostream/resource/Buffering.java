package ca.rbon.iostream.resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;

/**
 * <p>Buffering class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class Buffering {
    
    /** Constant <code>DEFAULT_BUFFER_SIZE=-1</code> */
    public static final int DEFAULT_BUFFER_SIZE = -1;
    
    /** Constant <code>UNBUFFERED=-2</code> */
    public static final int UNBUFFERED = -2;
    
    private static int valid(int size) {
        if (size < UNBUFFERED) {
            throw new IllegalArgumentException("BufferSize '" + size + "' is invalid.");
        }
        return size;
    }
    
    static InputStream buffer(InputStream is, int size, Chain chain) {
        switch (valid(size)) {
            case UNBUFFERED:
                return is;
            case DEFAULT_BUFFER_SIZE:
                return chain.chainAdd(new BufferedInputStream(is));
            default:
                return chain.chainAdd(new BufferedInputStream(is, size));
        }
    }

    static OutputStream buffer(OutputStream is, int size, Chain chain) {
        switch (valid(size)) {
            case UNBUFFERED:
                return is;
            case DEFAULT_BUFFER_SIZE:
                return chain.chainAdd(new BufferedOutputStream(is));
            default:
                return chain.chainAdd(new BufferedOutputStream(is, size));
        }
    }
    
    static Reader buffer(Reader is, int size, Chain chain) {
        switch (valid(size)) {
            case UNBUFFERED:                
                return is;
            case DEFAULT_BUFFER_SIZE:
                return chain.chainAdd(new BufferedReader(is));
            default:
                return chain.chainAdd(new BufferedReader(is, size));
        }
    }

    
    static Writer buffer(Writer is, int size, Chain chain) {
        switch (valid(size)) {
            case UNBUFFERED:
                return is;
            case DEFAULT_BUFFER_SIZE:
                return chain.chainAdd(new BufferedWriter(is));
            default:
                return chain.chainAdd(new BufferedWriter(is, size));
        }
    }      
    
}
