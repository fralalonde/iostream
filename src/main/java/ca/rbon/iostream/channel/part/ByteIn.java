package ca.rbon.iostream.channel.part;

import java.io.IOException;
import java.nio.charset.Charset;

import wrap.BufferedInputOf;
import wrap.BufferedReaderOf;
import wrap.DataInputOf;
import wrap.InputStreamOf;
import wrap.ObjectInputOf;
import wrap.ReaderOf;
import wrap.ZipInputOf;

public interface ByteIn<T> {
    
    InputStreamOf<T> inputStream() throws IOException;
    
    BufferedInputOf<T> bufferedInputStream(int bufferSize) throws IOException;
    
    BufferedInputOf<T> bufferedInputStream() throws IOException;
    
    ZipInputOf<T> zipInputStream(Charset charset, int bufferSize) throws IOException;
    
    ZipInputOf<T> zipInputStream(String charsetName, int bufferSize) throws IOException;
    
    ZipInputOf<T> zipInputStream(Charset charset) throws IOException;
    
    ZipInputOf<T> zipInputStream(String charsetName) throws IOException;
    
    ZipInputOf<T> zipInputStream(int bufferSize) throws IOException;
    
    ZipInputOf<T> zipInputStream() throws IOException;
    
    DataInputOf<T> dataInputStream(int bufferSize) throws IOException;
    
    DataInputOf<T> dataInputStream() throws IOException;
    
    /**
     * Build a buffered object input stream.
     * NOTE: Unlike other input streams, closing an ObjectInputStream does NOT close the underlying stream.
     * 
     * @param bufferSize the size of the read buffer
     * @return an ObjectInputOf
     * @throws IOException if something bad happens
     */
    ObjectInputOf<T> objectInputStream(int bufferSize) throws IOException;
    
    /**
     * Build a unbuffered object input stream.
     * NOTE: Unlike other input streams, closing an ObjectInputStream does NOT close the underlying stream.
     * 
     * @return an ObjectInputOf
     * @throws IOException if something bad happens
     */
    ObjectInputOf<T> objectInputStream() throws IOException;
    
    // UNBUFFERED
    
    /**
     * Build an unbuffered reader using the specified charset.
     * 
     * @param charset The ;@link Charset} to use
     * @return A ;@link ReaderOf} proxy extending the ;@link Reader} class
     * @throws IOException If the reader can not be built
     */
    ReaderOf<T> reader(Charset charset) throws IOException;
    
    /**
     * Build an unbuffered reader using the specified charset name.
     * 
     * @param charsetName The name of the ;@link Charset} to use
     * @return A ;@link ReaderOf} proxy extending the ;@link Reader} class
     * @throws IOException If the reader can not be built
     */
    ReaderOf<T> reader(String charsetName) throws IOException;
    
    // BUFFERED
    
    /**
     * Build a buffered reader using the specified charset and the specified buffer size.
     * 
     * @param charset The ;@link Charset} to use
     * @param bufferSize The size of the buffer to use
     * @return A ;@link ReaderOf} proxy extending the ;@link Reader} class
     * @throws IOException If the reader can not be built
     */
    BufferedReaderOf<T> bufferedReader(Charset charset, int bufferSize) throws IOException;
    
    /**
     * Build a buffered ;@link Reader} using the specified charset and the buffer size.
     * 
     * @param charset The ;@link Charset} to use
     * @return A ;@link ReaderOf} proxy extending the ;@link Reader} class
     * @throws IOException If the reader can not be built
     */
    BufferedReaderOf<T> bufferedReader(Charset charset) throws IOException;
    
    /**
     * Build an buffered ;@link Reader} using the specified charset name and the buffer size.
     * 
     * @param charsetName The name of the ;@link Charset} to use
     * @return A ;@link ReaderOf} proxy extending the ;@link Reader} class
     * @throws IOException If the reader can not be built
     */
    BufferedReaderOf<T> bufferedReader(String charsetName) throws IOException;
    
}
