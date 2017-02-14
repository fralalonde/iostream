package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.proxy.BufferedInputOf;
import ca.rbon.iostream.proxy.BufferedOutputOf;
import ca.rbon.iostream.proxy.BufferedReaderOf;
import ca.rbon.iostream.proxy.BufferedWriterOf;
import ca.rbon.iostream.proxy.DataInputOf;
import ca.rbon.iostream.proxy.DataOutputOf;
import ca.rbon.iostream.proxy.GZipInputOf;
import ca.rbon.iostream.proxy.GZipOutputOf;
import ca.rbon.iostream.proxy.InputStreamOf;
import ca.rbon.iostream.proxy.ObjectInputOf;
import ca.rbon.iostream.proxy.ObjectOutputOf;
import ca.rbon.iostream.proxy.OutputStreamOf;
import ca.rbon.iostream.proxy.PrintWriterOf;
import ca.rbon.iostream.proxy.ReaderOf;
import ca.rbon.iostream.proxy.WriterOf;
import ca.rbon.iostream.proxy.ZipInputOf;
import ca.rbon.iostream.proxy.ZipOutputOf;

/**
 * <p>Abstract BasePicker class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public abstract class BaseResource<T> extends ClosingResource<T> {
    
    /**
     * <p>getReader.</p>
     *
     * @param ch a {@link ca.rbon.iostream.Chain} object.
     * @return a {@link java.io.Reader} object.
     * @throws java.io.IOException if any.
     */
    protected abstract Reader getReader(Chain ch) throws IOException;
    
    /**
     * <p>getWriter.</p>
     *
     * @param ch a {@link ca.rbon.iostream.Chain} object.
     * @return a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    protected abstract Writer getWriter(Chain ch) throws IOException;
    
    /**
     * <p>getOutputStream.</p>
     *
     * @return a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    protected abstract OutputStream getOutputStream() throws IOException;
    
    /**
     * <p>getInputStream.</p>
     *
     * @return a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    protected abstract InputStream getInputStream() throws IOException;
    
    // SOURCE
    
    private InputStream wrappedBufferedInput(Charset charset, int bufferSize) throws IOException {
        return Buffering.buffer(chainAdd(getInputStream()), bufferSize, this);
    }
    
    private Reader wrappedBufferedReader(Charset charset, int bufferSize) throws IOException {
        Reader encoded = wrappedEncodedReader(charset);
        return Buffering.buffer(encoded, bufferSize, this);
    }
    
    private Reader wrappedEncodedReader(Charset charset) throws IOException {
        Reader natural = getReader(this);
        Reader encoded = natural != null
                ? natural
                : Encoding.streamReader(chainAdd(getInputStream()), charset);
        encoded = chainAdd(encoded);
        return encoded;
    }
    
    // SINK
    
    private OutputStream wrappedBufferOut(Charset charset, int bufferSize) throws IOException {
        return Buffering.buffer(chainAdd(getOutputStream()), bufferSize, this);
    }
    
    private Writer wrappedWriter(Charset charset, int bufferSize) throws IOException {
        Writer encoded = wrappedEncodedWriter(charset);
        return Buffering.buffer(encoded, bufferSize, this);
    }
    
    private Writer wrappedEncodedWriter(Charset charset) throws IOException {
        Writer natural = getWriter(this);
        Writer encoded = natural != null
                ? natural
                : Encoding.streamWriter(chainAdd(getOutputStream()), charset);
        encoded = chainAdd(encoded);
        return encoded;
    }
    
    // INPUT
    
    /**
     * <p>zipInputStream.</p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.ZipInputOf} object.
     * @throws java.io.IOException if any.
     */
    public ZipInputOf<T> zipInputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipInputOf<>(this, wrappedBufferedInput(null, bufferSize))
                : new ZipInputOf<>(this, wrappedBufferedInput(null, bufferSize), charset);
    }
    
    /**
     * <p>bufferedInputStream.</p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedInputOf} object.
     * @throws java.io.IOException if any.
     */
    public BufferedInputOf<T> bufferedInputStream(int bufferSize) throws IOException {
        return bufferSize > Buffering.DEFAULT_BUFFER_SIZE
                ? new BufferedInputOf<>(this, chainAdd(getInputStream()), bufferSize)
                : new BufferedInputOf<>(this, chainAdd(getInputStream()));
    }
    
    /**
     * <p>inputStream.</p>
     *
     * @return a {@link ca.rbon.iostream.proxy.InputStreamOf} object.
     * @throws java.io.IOException if any.
     */
    public InputStreamOf<T> inputStream() throws IOException {
        return new InputStreamOf<>(this, chainAdd(getInputStream()));
    }
    
    /**
     * <p>dataInputStream.</p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.DataInputOf} object.
     * @throws java.io.IOException if any.
     */
    public DataInputOf<T> dataInputStream(int bufferSize) throws IOException {
        return new DataInputOf<>(this, wrappedBufferedInput(null, bufferSize));
    }
    
    /**
     * <p>objectInputStream.</p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.ObjectInputOf} object.
     * @throws java.io.IOException if any.
     */
    public ObjectInputOf<T> objectInputStream(int bufferSize) throws IOException {
        return new ObjectInputOf<>(this, wrappedBufferedInput(null, bufferSize));
    }
    
    /**
     * <p>bufferedReader.</p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedReaderOf} object.
     * @throws java.io.IOException if any.
     */
    public BufferedReaderOf<T> bufferedReader(Charset charset, int bufferSize) throws IOException {
        return new BufferedReaderOf<>(this, wrappedBufferedReader(charset, bufferSize));
    }
    
    /**
     * <p>reader.</p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @return a {@link ca.rbon.iostream.proxy.ReaderOf} object.
     * @throws java.io.IOException if any.
     */
    public ReaderOf<T> reader(Charset charset) throws IOException {
        return new ReaderOf<>(this, wrappedBufferedReader(charset, Buffering.UNBUFFERED));
    }
    
    // OUTPUT
    
    /**
     * <p>zipOutputStream.</p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.ZipOutputOf} object.
     * @throws java.io.IOException if any.
     */
    public ZipOutputOf<T> zipOutputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipOutputOf<>(this, wrappedBufferOut(null, bufferSize))
                : new ZipOutputOf<>(this, wrappedBufferOut(null, bufferSize), charset);
    }
    
    /**
     * <p>bufferedOutputStream.</p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedOutputOf} object.
     * @throws java.io.IOException if any.
     */
    public BufferedOutputOf<T> bufferedOutputStream(int bufferSize) throws IOException {
        return bufferSize > Buffering.DEFAULT_BUFFER_SIZE
                ? new BufferedOutputOf<>(this, chainAdd(getOutputStream()), bufferSize)
                : new BufferedOutputOf<>(this, chainAdd(getOutputStream()));        
    }
    
    /**
     * <p>outputStream.</p>
     *
     * @return a {@link ca.rbon.iostream.proxy.OutputStreamOf} object.
     * @throws java.io.IOException if any.
     */
    public OutputStreamOf<T> outputStream() throws IOException {
        return new OutputStreamOf<>(this, chainAdd(getOutputStream()));
    }
    
    /**
     * <p>dataOutputStream.</p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.DataOutputOf} object.
     * @throws java.io.IOException if any.
     */
    public DataOutputOf<T> dataOutputStream(int bufferSize) throws IOException {
        return new DataOutputOf<>(this, wrappedBufferOut(null, bufferSize));
    }
    
    /**
     * <p>objectOutputStream.</p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.ObjectOutputOf} object.
     * @throws java.io.IOException if any.
     */
    public ObjectOutputOf<T> objectOutputStream(int bufferSize) throws IOException {
        return new ObjectOutputOf<>(this, wrappedBufferOut(null, bufferSize));
    }
    
    /**
     * <p>printWriter.</p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.PrintWriterOf} object.
     * @throws java.io.IOException if any.
     */
    public PrintWriterOf<T> printWriter(Charset charset, int bufferSize) throws IOException {
        return new PrintWriterOf<>(this, wrappedWriter(charset, bufferSize));
    }
    
    /**
     * <p>printWriter.</p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @param autoflush a boolean.
     * @return a {@link ca.rbon.iostream.proxy.PrintWriterOf} object.
     * @throws java.io.IOException if any.
     */
    public PrintWriterOf<T> printWriter(Charset charset, int bufferSize, boolean autoflush) throws IOException {
        return new PrintWriterOf<>(this, wrappedWriter(charset, bufferSize), autoflush);
    }
    
    /**
     * <p>bufferedWriter.</p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedWriterOf} object.
     * @throws java.io.IOException if any.
     */
    public BufferedWriterOf<T> bufferedWriter(Charset charset, int bufferSize) throws IOException {
        return new BufferedWriterOf<>(this, wrappedWriter(charset, bufferSize));
    }
    
    /**
     * <p>writer.</p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @return a {@link ca.rbon.iostream.proxy.WriterOf} object.
     * @throws java.io.IOException if any.
     */
    public WriterOf<T> writer(Charset charset) throws IOException {
        return new WriterOf<>(this, wrappedWriter(charset, Buffering.UNBUFFERED));
    }
    
    // STRAIGHT
    
    /**
     * <p>reader.</p>
     *
     * @return a {@link ca.rbon.iostream.proxy.ReaderOf} object.
     * @throws java.io.IOException if any.
     */
    public ReaderOf<T> reader() throws IOException {
        return reader(Encoding.DEFAULT_CHARSET);
    }
    
    /**
     * <p>bufferedReader.</p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedReaderOf} object.
     * @throws java.io.IOException if any.
     */
    public BufferedReaderOf<T> bufferedReader(int bufferSize) throws IOException {
        return bufferedReader(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    /**
     * <p>writer.</p>
     *
     * @return a {@link ca.rbon.iostream.proxy.WriterOf} object.
     * @throws java.io.IOException if any.
     */
    public WriterOf<T> writer() throws IOException {
        return writer(Encoding.DEFAULT_CHARSET);
    }
    
    /**
     * <p>bufferedWriter.</p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedWriterOf} object.
     * @throws java.io.IOException if any.
     */
    public BufferedWriterOf<T> bufferedWriter(int bufferSize) throws IOException {
        return bufferedWriter(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    /**
     * <p>printWriter.</p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.PrintWriterOf} object.
     * @throws java.io.IOException if any.
     */
    public PrintWriterOf<T> printWriter(int bufferSize) throws IOException {
        return printWriter(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    /**
     * <p>printWriter.</p>
     *
     * @param bufferSize a int.
     * @param autoflush a boolean.
     * @return a {@link ca.rbon.iostream.proxy.PrintWriterOf} object.
     * @throws java.io.IOException if any.
     */
    public PrintWriterOf<T> printWriter(int bufferSize, boolean autoflush) throws IOException {
        return printWriter(Encoding.DEFAULT_CHARSET, bufferSize, autoflush);
    }
    
    public GZipInputOf<T> gzipInputStream(int bufferSize) throws IOException {
        return new GZipInputOf<>(this, chainAdd(getInputStream()));
    }

    public GZipOutputOf<T> gzipOutputStream(int bufferSize) throws IOException {
        return new GZipOutputOf<>(this, chainAdd(getOutputStream()));
    }
    
    
    
}
