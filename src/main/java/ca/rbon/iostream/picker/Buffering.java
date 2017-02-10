package ca.rbon.iostream.picker;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;

public class Buffering {
    
    public static final int DEFAULT_BUFFER_SIZE = -1;
    
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
                return chain.add(new BufferedInputStream(is));
            default:
                return chain.add(new BufferedInputStream(is, size));
        }
    }

    static OutputStream buffer(OutputStream is, int size, Chain chain) {
        switch (valid(size)) {
            case UNBUFFERED:
                return is;
            case DEFAULT_BUFFER_SIZE:
                return chain.add(new BufferedOutputStream(is));
            default:
                return chain.add(new BufferedOutputStream(is, size));
        }
    }
    
    static Reader buffer(Reader is, int size, Chain chain) {
        switch (valid(size)) {
            case UNBUFFERED:                
                return is;
            case DEFAULT_BUFFER_SIZE:
                return chain.add(new BufferedReader(is));
            default:
                return chain.add(new BufferedReader(is, size));
        }
    }

    
    static Writer buffer(Writer is, int size, Chain chain) {
        switch (valid(size)) {
            case UNBUFFERED:
                return is;
            case DEFAULT_BUFFER_SIZE:
                return chain.add(new BufferedWriter(is));
            default:
                return chain.add(new BufferedWriter(is, size));
        }
    }      
    
}
