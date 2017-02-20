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
import java.util.ArrayList;
import java.util.List;

import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.channel.filter.Base64Filter;
import ca.rbon.iostream.channel.filter.FilterFactory;
import ca.rbon.iostream.channel.filter.GzipFilter;
import ca.rbon.iostream.channel.part.ByteIn;
import ca.rbon.iostream.channel.part.ByteOut;
import ca.rbon.iostream.channel.part.CharIn;
import ca.rbon.iostream.channel.part.CharOut;
import ca.rbon.iostream.channel.part.Filter;
import ca.rbon.iostream.wrap.BufferedInputOf;
import ca.rbon.iostream.wrap.BufferedOutputOf;
import ca.rbon.iostream.wrap.BufferedReaderOf;
import ca.rbon.iostream.wrap.BufferedWriterOf;
import ca.rbon.iostream.wrap.DataInputOf;
import ca.rbon.iostream.wrap.DataOutputOf;
import ca.rbon.iostream.wrap.InputStreamOf;
import ca.rbon.iostream.wrap.ObjectInputOf;
import ca.rbon.iostream.wrap.ObjectOutputOf;
import ca.rbon.iostream.wrap.OutputStreamOf;
import ca.rbon.iostream.wrap.PrintWriterOf;
import ca.rbon.iostream.wrap.ReaderOf;
import ca.rbon.iostream.wrap.WriterOf;
import ca.rbon.iostream.wrap.ZipInputOf;
import ca.rbon.iostream.wrap.ZipOutputOf;

/**
 * <p>
 * Abstract BasePicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 * @param <T> The resource type
 */
public abstract class Resource<T> implements ByteIn<T>, ByteOut<T>, CharIn<T>, CharOut<T>, Filter<Resource<T>> {
    
    /**
     * No buffersize specified, use public
     */
    public static final int DEFAULT_BUFFER_SIZE = -1;
    
    static final String STREAM_NOT_SUPPORTED = "Byte-oriented stream operations not supported by this type of resource.";
    
    /**
     * No charset specified, use system public
     */
    static final Charset DEFAULT_CHARSET = null;
    
    /**
     * Do not buffer the stream
     */
    static final int UNBUFFERED = -2;
    
    /**
     * Not buffersize specified, use public
     */
    static final int NOT_GZIPPED = -2;
    
    List<FilterFactory> filters = new ArrayList<>();
    
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
     * getInputStream.
     * </p>
     * 
     * @return a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    protected InputStream getInputStream() throws IOException {
        throw new CodeFlowError(STREAM_NOT_SUPPORTED);
    }
    
    /**
     * <p>
     * getOutputStream.
     * </p>
     * 
     * @return a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    protected OutputStream getOutputStream() throws IOException {
        throw new CodeFlowError(STREAM_NOT_SUPPORTED);
    }
    
    /**
     * <p>
     * filteredOut.
     * </p>
     * 
     * @return a {@link java.io.OutputStream} object.
     * @throws java.io.IOException if any.
     */
    private OutputStream filteredOut() throws IOException {
        OutputStream filteredOutput = getOutputStream();
        for (FilterFactory f : filters) {
            filteredOutput = f.filterOutput(filteredOutput);
        }
        return filteredOutput;
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
        InputStream filteredInput = getInputStream();
        for (FilterFactory f : filters) {
            filteredInput = f.filterInput(filteredInput);
        }
        return filteredInput;
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
     * @return a {@link ca.rbon.iostream.wrap.ZipInputOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.BufferedInputOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.InputStreamOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.DataInputOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.ObjectInputOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.BufferedReaderOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.ReaderOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.ZipOutputOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.BufferedOutputOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.OutputStreamOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.DataOutputOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.ObjectOutputOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.PrintWriterOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.PrintWriterOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.BufferedWriterOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.WriterOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.ReaderOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.BufferedReaderOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.WriterOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.BufferedWriterOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.PrintWriterOf} object.
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
     * @return a {@link ca.rbon.iostream.wrap.PrintWriterOf} object.
     * @throws java.io.IOException if any.
     */
    
    @Override
    public PrintWriterOf<T> printWriter(int bufferSize, boolean autoflush) throws IOException {
        return printWriter(DEFAULT_CHARSET, bufferSize, autoflush);
    }
    
    // DEFAULT IN
    
    @Override
    public BufferedInputOf<T> bufferedInputStream() throws IOException {
        return bufferedInputStream(DEFAULT_BUFFER_SIZE);
    }
    
    @Override
    public ZipInputOf<T> zipInputStream(String charsetName, int bufferSize) throws IOException {
        return zipInputStream(Charset.forName(charsetName), bufferSize);
    }
    
    @Override
    public ZipInputOf<T> zipInputStream(Charset charset) throws IOException {
        return zipInputStream(charset, UNBUFFERED);
    }
    
    @Override
    public ZipInputOf<T> zipInputStream(String charsetName) throws IOException {
        return zipInputStream(charsetName, UNBUFFERED);
    }
    
    @Override
    public ZipInputOf<T> zipInputStream(int bufferSize) throws IOException {
        return zipInputStream(DEFAULT_CHARSET, bufferSize);
    }
    
    @Override
    public ZipInputOf<T> zipInputStream() throws IOException {
        return zipInputStream(DEFAULT_CHARSET, UNBUFFERED);
    }
    
    @Override
    public DataInputOf<T> dataInputStream() throws IOException {
        return dataInputStream(UNBUFFERED);
    }
    
    /**
     * Build a unbuffered object input stream.
     * NOTE: Unlike other input streams, closing an ObjectInputStream does NOT close the underlying stream.
     * 
     * @return an ObjectInputOf
     * @throws IOException if something bad happens
     */
    
    @Override
    public ObjectInputOf<T> objectInputStream() throws IOException {
        return objectInputStream(UNBUFFERED);
    }
    
    // UNBUFFERED
    
    /**
     * Build an unbuffered reader using the specified charset name.
     * 
     * @param charsetName The name of the {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class
     * @throws IOException If the reader can not be built
     */
    
    @Override
    public ReaderOf<T> reader(String charsetName) throws IOException {
        return reader(Charset.forName(charsetName));
    }
    
    // BUFFERED
    
    /**
     * Build a buffered {@link Reader} using the specified charset and the public buffer size.
     * 
     * @param charset The {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class
     * @throws IOException If the reader can not be built
     */
    
    @Override
    public BufferedReaderOf<T> bufferedReader(Charset charset) throws IOException {
        return bufferedReader(charset, DEFAULT_BUFFER_SIZE);
    }
    
    /**
     * Build an buffered {@link Reader} using the specified charset name and the public buffer size.
     * 
     * @param charsetName The name of the {@link Charset} to use
     * @return A {@link ReaderOf} proxy extending the {@link Reader} class
     * @throws IOException If the reader can not be built
     */
    
    @Override
    public BufferedReaderOf<T> bufferedReader(String charsetName) throws IOException {
        return bufferedReader(Charset.forName(charsetName), DEFAULT_BUFFER_SIZE);
    }
    
    // DEFAULT OUT
    
    @Override
    public BufferedOutputOf<T> bufferedOutputStream() throws IOException {
        return bufferedOutputStream(DEFAULT_BUFFER_SIZE);
    }
    
    @Override
    public ZipOutputOf<T> zipOutputStream(String charsetName, int bufferSize) throws IOException {
        return zipOutputStream(Charset.forName(charsetName), bufferSize);
    }
    
    @Override
    public ZipOutputOf<T> zipOutputStream(Charset charset) throws IOException {
        return zipOutputStream(charset, UNBUFFERED);
    }
    
    @Override
    public ZipOutputOf<T> zipOutputStream(String charsetName) throws IOException {
        return zipOutputStream(charsetName, UNBUFFERED);
    }
    
    @Override
    public ZipOutputOf<T> zipOutputStream(int bufferSize) throws IOException {
        return zipOutputStream(DEFAULT_CHARSET, bufferSize);
    }
    
    @Override
    public ZipOutputOf<T> zipOutputStream() throws IOException {
        return zipOutputStream(DEFAULT_CHARSET, UNBUFFERED);
    }
    
    @Override
    public DataOutputOf<T> dataOutputStream() throws IOException {
        return dataOutputStream(UNBUFFERED);
    }
    
    @Override
    public ObjectOutputOf<T> objectOutputStream() throws IOException {
        return objectOutputStream(UNBUFFERED);
        
    }
    
    @Override
    public WriterOf<T> writer(String charsetName) throws IOException {
        return writer(Charset.forName(charsetName));
    }
    
    // BUFFERED
    
    @Override
    public BufferedWriterOf<T> bufferedWriter(Charset charset) throws IOException {
        return bufferedWriter(DEFAULT_CHARSET, DEFAULT_BUFFER_SIZE);
    }
    
    @Override
    public BufferedWriterOf<T> bufferedWriter(String charsetName) throws IOException {
        return bufferedWriter(Charset.forName(charsetName), DEFAULT_BUFFER_SIZE);
    }
    
    // PRINT
    
    @Override
    public PrintWriterOf<T> printWriter(String charsetName, int bufferSize) throws IOException {
        return printWriter(Charset.forName(charsetName), bufferSize);
    }
    
    @Override
    public PrintWriterOf<T> printWriter(Charset charset) throws IOException {
        return printWriter(charset, DEFAULT_BUFFER_SIZE);
    }
    
    @Override
    public PrintWriterOf<T> printWriter(String charsetName) throws IOException {
        return printWriter(charsetName, DEFAULT_BUFFER_SIZE);
    }
    
    // PRINT AUTOFLUSH
    
    @Override
    public PrintWriterOf<T> printWriter(String charsetName, int bufferSize, boolean autoflush) throws IOException {
        return printWriter(Charset.forName(charsetName), bufferSize, autoflush);
    }
    
    @Override
    public PrintWriterOf<T> printWriter(Charset charset, boolean autoflush) throws IOException {
        return printWriter(charset, DEFAULT_BUFFER_SIZE, autoflush);
    }
    
    @Override
    public PrintWriterOf<T> printWriter(String charsetName, boolean autoflush) throws IOException {
        return printWriter(charsetName, DEFAULT_BUFFER_SIZE, autoflush);
    }
    
    // DEF CHAR IN
    
    @Override
    public BufferedReaderOf<T> bufferedReader() throws IOException {
        return bufferedReader(DEFAULT_BUFFER_SIZE);
    }
    
    // DEF CHAR OUT
    
    @Override
    public BufferedWriterOf<T> bufferedWriter() throws IOException {
        return bufferedWriter(DEFAULT_BUFFER_SIZE);
    }
    
    @Override
    public PrintWriterOf<T> printWriter() throws IOException {
        return printWriter(DEFAULT_BUFFER_SIZE);
    }
    
    @Override
    public PrintWriterOf<T> printWriter(boolean autoflush) throws IOException {
        return printWriter(DEFAULT_BUFFER_SIZE, autoflush);
    }
    
    @Override
    public Resource<T> gzip() {
        return gzip(DEFAULT_BUFFER_SIZE);
    }
    
    @Override
    public Resource<T> gzip(int bufferSize) {
        filter(new GzipFilter(bufferSize));
        return this;
    }
    
    @Override
    public Resource<T> filter(FilterFactory filter) {
        filters.add(filter);
        return this;
    }
    
    @Override
    public Resource<T> base64() {
        return filter(new Base64Filter());
    }
    
}
