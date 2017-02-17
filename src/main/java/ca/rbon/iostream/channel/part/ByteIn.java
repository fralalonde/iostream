package ca.rbon.iostream.channel.part;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import ca.rbon.iostream.proxy.BufferedInputOf;
import ca.rbon.iostream.proxy.BufferedReaderOf;
import ca.rbon.iostream.proxy.DataInputOf;
import ca.rbon.iostream.proxy.InputStreamOf;
import ca.rbon.iostream.proxy.ObjectInputOf;
import ca.rbon.iostream.proxy.ReaderOf;
import ca.rbon.iostream.proxy.ZipInputOf;
import ca.rbon.iostream.resource.Resource;

public interface ByteIn<T> {
    
    InputStreamOf<T> inputStream() throws IOException;
    
    BufferedInputOf<T> bufferedInputStream(int bufferSize) throws IOException;
    
    default BufferedInputOf<T> bufferedInputStream() throws IOException {
        return bufferedInputStream(Resource.DEFAULT_BUFFER_SIZE);
    }
    
    ZipInputOf<T> zipInputStream(Charset charset, int bufferSize) throws IOException;
    
    default ZipInputOf<T> zipInputStream(String charsetName, int bufferSize) throws IOException {
        return zipInputStream(Charset.forName(charsetName), bufferSize);
    }
    
    default ZipInputOf<T> zipInputStream(Charset charset) throws IOException {
        return zipInputStream(charset, Resource.UNBUFFERED);
    }
    
    default ZipInputOf<T> zipInputStream(String charsetName) throws IOException {
        return zipInputStream(charsetName, Resource.UNBUFFERED);
    }
    
    default ZipInputOf<T> zipInputStream(int bufferSize) throws IOException {
        return zipInputStream(Resource.DEFAULT_CHARSET, bufferSize);
    }
    
    default ZipInputOf<T> zipInputStream() throws IOException {
        return zipInputStream(Resource.DEFAULT_CHARSET, Resource.UNBUFFERED);
    }
    
    DataInputOf<T> dataInputStream(int bufferSize) throws IOException;
    
    default DataInputOf<T> dataInputStream() throws IOException {
        return dataInputStream(Resource.UNBUFFERED);
    }
    
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
    default ObjectInputOf<T> objectInputStream() throws IOException {
        return objectInputStream(Resource.UNBUFFERED);
    }
    
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
        return bufferedReader(charset, Resource.DEFAULT_BUFFER_SIZE);
    }
    
    /**
     * Build an buffered {@link Reader} using the specified charset name and the default buffer size.
     * 
     * @param charsetName The name of the {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class
     * @throws IOException If the reader can not be built
     */
    default BufferedReaderOf<T> bufferedReader(String charsetName) throws IOException {
        return bufferedReader(Charset.forName(charsetName), Resource.DEFAULT_BUFFER_SIZE);
    }
    
}
