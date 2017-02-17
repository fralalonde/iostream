package ca.rbon.iostream.resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import ca.rbon.iostream.channel.BytesBiChannel;
import ca.rbon.iostream.proxy.BufferedInputOf;
import ca.rbon.iostream.proxy.BufferedOutputOf;
import ca.rbon.iostream.proxy.BufferedReaderOf;
import ca.rbon.iostream.proxy.BufferedWriterOf;
import ca.rbon.iostream.proxy.DataInputOf;
import ca.rbon.iostream.proxy.DataOutputOf;
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
 * <p>
 * Abstract BasePicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public abstract class Resource<T> implements BytesBiChannel<T> {
    
    /**
     * No charset specified, use system default
     */
    public static final Charset DEFAULT_CHARSET = null;
    
    /**
     * No buffersize specified, use default
     */
    public static final int DEFAULT_BUFFER_SIZE = -1;
    
    /**
     * Do not buffer the stream
     */
    public static final int UNBUFFERED = -2;
    
    /**
     * Not buffersize specified, use default
     */
    public static final int NOT_GZIPPED = -2;
    
    static InputStreamReader streamReader(InputStream stream, Charset charset) {
        return charset == DEFAULT_CHARSET
                ? new InputStreamReader(stream)
                : new InputStreamReader(stream, charset);
    }
    
    static OutputStreamWriter streamWriter(OutputStream stream, Charset charset) {
        return charset == DEFAULT_CHARSET
                ? new OutputStreamWriter(stream)
                : new OutputStreamWriter(stream, charset);
    }
    
    private static int validBufferSize(int size) {
        if (size < UNBUFFERED) {
            throw new IllegalArgumentException("BufferSize '" + size + "' is invalid.");
        }
        return size;
    }
    
    static InputStream buffer(InputStream is, int size) {
        switch (validBufferSize(size)) {
            case UNBUFFERED:
                return is;
            case DEFAULT_BUFFER_SIZE:
                return new BufferedInputStream(is);
            default:
                return new BufferedInputStream(is, size);
        }
    }
    
    static OutputStream buffer(OutputStream is, int size) {
        switch (validBufferSize(size)) {
            case UNBUFFERED:
                return is;
            case DEFAULT_BUFFER_SIZE:
                return new BufferedOutputStream(is);
            default:
                return new BufferedOutputStream(is, size);
        }
    }
    
    static Reader buffer(Reader is, int size) {
        switch (validBufferSize(size)) {
            case UNBUFFERED:
                return is;
            case DEFAULT_BUFFER_SIZE:
                return new BufferedReader(is);
            default:
                return new BufferedReader(is, size);
        }
    }
    
    static Writer buffer(Writer is, int size) {
        switch (validBufferSize(size)) {
            case UNBUFFERED:
                return is;
            case DEFAULT_BUFFER_SIZE:
                return new BufferedWriter(is);
            default:
                return new BufferedWriter(is, size);
        }
    }
    
    private int gzipBufferSize = NOT_GZIPPED;
    
    public abstract T getResource() throws IOException;
    
    /**
     * <p>
     * getReader.
     * </p>
     *
     * @return a {@link java.io.Reader} object.
     * @throws java.io.IOException if any.
     */
    protected Reader getReader() throws IOException {
        return null;
    }
    
    /**
     * <p>
     * getWriter.
     * </p>
     *
     * @return a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    protected Writer getWriter() throws IOException {
        return null;
    }
    
    /**
     * <p>
     * getOutputStream.
     * </p>
     * 
     * @return a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    protected abstract OutputStream getOutputStream() throws IOException;
    
    /**
     * <p>
     * getInputStream.
     * </p>
     * 
     * @return a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    protected abstract InputStream getInputStream() throws IOException;
    
    /**
     * <p>
     * filteredOut.
     * </p>
     * 
     * @return a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    private OutputStream filteredOut() throws IOException {
        switch (gzipBufferSize) {
            case NOT_GZIPPED:
                return getOutputStream();
            case DEFAULT_BUFFER_SIZE:
                return new GZIPOutputStream(getOutputStream());
            default:
                return new GZIPOutputStream(getOutputStream(), gzipBufferSize);
        }
    }
    
    /**
     * <p>
     * filteredIn.
     * </p>
     * 
     * @return a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    private InputStream filteredIn() throws IOException {
        switch (gzipBufferSize) {
            case NOT_GZIPPED:
                return getInputStream();
            case DEFAULT_BUFFER_SIZE:
                return new GZIPInputStream(getInputStream());
            default:
                return new GZIPInputStream(getInputStream(), gzipBufferSize);
        }
    }
    
    // SOURCE
    
    private InputStream wrappedBufferedInput(Charset charset, int bufferSize) throws IOException {
        return buffer(filteredIn(), bufferSize);
    }
    
    private Reader wrappedBufferedReader(Charset charset, int bufferSize) throws IOException {
        Reader encoded = wrappedEncodedReader(charset);
        return buffer(encoded, bufferSize);
    }
    
    private Reader wrappedEncodedReader(Charset charset) throws IOException {
        Reader natural = getReader();
        Reader encoded = natural != null
                ? natural
                : streamReader(filteredIn(), charset);
        return encoded;
    }
    
    // SINK
    
    private OutputStream wrappedBufferOut(Charset charset, int bufferSize) throws IOException {
        return buffer(filteredOut(), bufferSize);
    }
    
    private Writer wrappedWriter(Charset charset, int bufferSize) throws IOException {
        Writer encoded = wrappedEncodedWriter(charset);
        return buffer(encoded, bufferSize);
    }
    
    private Writer wrappedEncodedWriter(Charset charset) throws IOException {
        Writer natural = getWriter();
        Writer encoded = natural != null
                ? natural
                : streamWriter(filteredOut(), charset);
        return encoded;
    }
    
    // INPUT
    
    /**
     * <p>
     * zipInputStream.
     * </p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.ZipInputOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public ZipInputOf<T> zipInputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipInputOf<>(this, wrappedBufferedInput(null, bufferSize))
                : new ZipInputOf<>(this, wrappedBufferedInput(null, bufferSize), charset);
    }
    
    /**
     * <p>
     * bufferedInputStream.
     * </p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedInputOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public BufferedInputOf<T> bufferedInputStream(int bufferSize) throws IOException {
        return bufferSize > DEFAULT_BUFFER_SIZE
                ? new BufferedInputOf<>(this, filteredIn(), bufferSize)
                : new BufferedInputOf<>(this, filteredIn());
    }
    
    /**
     * <p>
     * inputStream.
     * </p>
     *
     * @return a {@link ca.rbon.iostream.proxy.InputStreamOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public InputStreamOf<T> inputStream() throws IOException {
        return new InputStreamOf<>(this, filteredIn());
    }
    
    /**
     * <p>
     * dataInputStream.
     * </p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.DataInputOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public DataInputOf<T> dataInputStream(int bufferSize) throws IOException {
        return new DataInputOf<>(this, wrappedBufferedInput(null, bufferSize));
    }
    
    /**
     * <p>
     * objectInputStream.
     * </p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.ObjectInputOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public ObjectInputOf<T> objectInputStream(int bufferSize) throws IOException {
        return new ObjectInputOf<>(this, wrappedBufferedInput(null, bufferSize));
    }
    
    /**
     * <p>
     * bufferedReader.
     * </p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedReaderOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public BufferedReaderOf<T> bufferedReader(Charset charset, int bufferSize) throws IOException {
        return new BufferedReaderOf<>(this, wrappedBufferedReader(charset, bufferSize));
    }
    
    /**
     * <p>
     * reader.
     * </p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @return a {@link ca.rbon.iostream.proxy.ReaderOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public ReaderOf<T> reader(Charset charset) throws IOException {
        return new ReaderOf<>(this, wrappedBufferedReader(charset, UNBUFFERED));
    }
    
    // OUTPUT
    
    /**
     * <p>
     * zipOutputStream.
     * </p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.ZipOutputOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public ZipOutputOf<T> zipOutputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipOutputOf<>(this, wrappedBufferOut(null, bufferSize))
                : new ZipOutputOf<>(this, wrappedBufferOut(null, bufferSize), charset);
    }
    
    /**
     * <p>
     * bufferedOutputStream.
     * </p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedOutputOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public BufferedOutputOf<T> bufferedOutputStream(int bufferSize) throws IOException {
        return bufferSize > DEFAULT_BUFFER_SIZE
                ? new BufferedOutputOf<>(this, filteredOut(), bufferSize)
                : new BufferedOutputOf<>(this, filteredOut());
    }
    
    /**
     * <p>
     * outputStream.
     * </p>
     *
     * @return a {@link ca.rbon.iostream.proxy.OutputStreamOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public OutputStreamOf<T> outputStream() throws IOException {
        return new OutputStreamOf<>(this, filteredOut());
    }
    
    /**
     * <p>
     * dataOutputStream.
     * </p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.DataOutputOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public DataOutputOf<T> dataOutputStream(int bufferSize) throws IOException {
        return new DataOutputOf<>(this, wrappedBufferOut(null, bufferSize));
    }
    
    /**
     * <p>
     * objectOutputStream.
     * </p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.ObjectOutputOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public ObjectOutputOf<T> objectOutputStream(int bufferSize) throws IOException {
        return new ObjectOutputOf<>(this, wrappedBufferOut(null, bufferSize));
    }
    
    /**
     * <p>
     * printWriter.
     * </p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.PrintWriterOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public PrintWriterOf<T> printWriter(Charset charset, int bufferSize) throws IOException {
        return new PrintWriterOf<>(this, wrappedWriter(charset, bufferSize));
    }
    
    /**
     * <p>
     * printWriter.
     * </p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @param autoflush a boolean.
     * @return a {@link ca.rbon.iostream.proxy.PrintWriterOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public PrintWriterOf<T> printWriter(Charset charset, int bufferSize, boolean autoflush) throws IOException {
        return new PrintWriterOf<>(this, wrappedWriter(charset, bufferSize), autoflush);
    }
    
    /**
     * <p>
     * bufferedWriter.
     * </p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedWriterOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public BufferedWriterOf<T> bufferedWriter(Charset charset, int bufferSize) throws IOException {
        return new BufferedWriterOf<>(this, wrappedWriter(charset, bufferSize));
    }
    
    /**
     * <p>
     * writer.
     * </p>
     *
     * @param charset a {@link java.nio.charset.Charset} object.
     * @return a {@link ca.rbon.iostream.proxy.WriterOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public WriterOf<T> writer(Charset charset) throws IOException {
        return new WriterOf<>(this, wrappedWriter(charset, UNBUFFERED));
    }
    
    // STRAIGHT
    
    /**
     * <p>
     * reader.
     * </p>
     *
     * @return a {@link ca.rbon.iostream.proxy.ReaderOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public ReaderOf<T> reader() throws IOException {
        return reader(DEFAULT_CHARSET);
    }
    
    /**
     * <p>
     * bufferedReader.
     * </p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedReaderOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public BufferedReaderOf<T> bufferedReader(int bufferSize) throws IOException {
        return bufferedReader(DEFAULT_CHARSET, bufferSize);
    }
    
    /**
     * <p>
     * writer.
     * </p>
     *
     * @return a {@link ca.rbon.iostream.proxy.WriterOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public WriterOf<T> writer() throws IOException {
        return writer(DEFAULT_CHARSET);
    }
    
    /**
     * <p>
     * bufferedWriter.
     * </p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.BufferedWriterOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public BufferedWriterOf<T> bufferedWriter(int bufferSize) throws IOException {
        return bufferedWriter(DEFAULT_CHARSET, bufferSize);
    }
    
    /**
     * <p>
     * printWriter.
     * </p>
     *
     * @param bufferSize a int.
     * @return a {@link ca.rbon.iostream.proxy.PrintWriterOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public PrintWriterOf<T> printWriter(int bufferSize) throws IOException {
        return printWriter(DEFAULT_CHARSET, bufferSize);
    }
    
    /**
     * <p>
     * printWriter.
     * </p>
     *
     * @param bufferSize a int.
     * @param autoflush a boolean.
     * @return a {@link ca.rbon.iostream.proxy.PrintWriterOf} object.
     * @throws java.io.IOException if any.
     */
    @Override
    public PrintWriterOf<T> printWriter(int bufferSize, boolean autoflush) throws IOException {
        return printWriter(DEFAULT_CHARSET, bufferSize, autoflush);
    }
    
    @Override
    public BytesBiChannel<T> gzip(int bufferSize) {
        gzipBufferSize = bufferSize;
        return this;
    }
    
}
