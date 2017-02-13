package ca.rbon.iostream.channel;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import ca.rbon.iostream.proxy.BufferedReaderOf;
import ca.rbon.iostream.proxy.ReaderOf;
import ca.rbon.iostream.resource.Buffering;

public interface ByteReaderChannel<T> extends CharReaderChannel<T> {
    
    // UNBUFFERED
    
    /**
     * Build an unbuffered reader using the specified charset.
     * 
     * @param charset The {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class 
     * @throws IOException If the reader can not be built
     */
    ReaderOf<T> reader(Charset charset) throws IOException;
    
    /**
     * Build an unbuffered reader using the specified charset name.
     * 
     * @param charsetName The name of the {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class 
     * @throws IOException If the reader can not be built
     */
    default ReaderOf<T> reader(String charsetName) throws IOException {
        return reader(Charset.forName(charsetName));
    }
    
    // BUFFERED
    
    /**
     * Build a buffered reader using the specified charset and the specified buffer size.
     * 
     * @param charset The {@link Charset} to use
     * @param bufferSize The size of the buffer to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class 
     * @throws IOException If the reader can not be built
     */
    BufferedReaderOf<T> bufferedReader(Charset charset, int bufferSize) throws IOException;
    
    /**
     * Build a buffered {@link Reader} using the specified charset and the default buffer size.
     * 
     * @param charset The {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class 
     * @throws IOException If the reader can not be built
     */
    default BufferedReaderOf<T> bufferedReader(Charset charset) throws IOException {
        return bufferedReader(charset, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    /**
     * Build an buffered {@link Reader} using the specified charset name and the default buffer size.
     * 
     * @param charsetName The name of the {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class 
     * @throws IOException If the reader can not be built
     */
    default BufferedReaderOf<T> bufferedReader(String charsetName) throws IOException {
        return bufferedReader(Charset.forName(charsetName), Buffering.DEFAULT_BUFFER_SIZE);
    }
    
}
