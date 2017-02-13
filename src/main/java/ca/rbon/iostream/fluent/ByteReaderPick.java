package ca.rbon.iostream.fluent;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.writer.BufferedReaderOf;
import ca.rbon.iostream.writer.ReaderOf;

public interface ByteReaderPick<T> extends CharReaderPick<T> {
    
    // UNBUFFERED
    
    /**
     * Build an unbuffered {@link Reader} using the specified {@link Charset}.
     * 
     * @param charset The {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class 
     * @throws IOException If the reader can not be built
     */
    ReaderOf<T> reader(Charset charset) throws IOException;
    
    /**
     * Build an unbuffered {@link Reader} using the specified {@link Charset} name.
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
     * Build an buffered {@link Reader} using the specified {@link Charset} and the specified buffer size.
     * 
     * @param charset The {@link Charset} to use
     * @param bufferSize The size of the buffer to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class 
     * @throws IOException If the reader can not be built
     */
    BufferedReaderOf<T> bufferedReader(Charset charset, int bufferSize) throws IOException;
    
    /**
     * Build an buffered {@link Reader} using the specified {@link Charset} and the default buffer size.
     * 
     * @param charset The {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class 
     * @throws IOException If the reader can not be built
     */
    default BufferedReaderOf<T> bufferedReader(Charset charset) throws IOException {
        return bufferedReader(charset, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    /**
     * Build an buffered {@link Reader} using the specified {@link Charset} name and the default buffer size.
     * 
     * @param charsetName The name of the {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class 
     * @throws IOException If the reader can not be built
     */
    default BufferedReaderOf<T> bufferedReader(String charsetName) throws IOException {
        return bufferedReader(Charset.forName(charsetName), Buffering.DEFAULT_BUFFER_SIZE);
    }
    
}
