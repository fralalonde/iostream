package ca.rbon.iostream.picker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.stream.BufferedInputOf;
import ca.rbon.iostream.stream.BufferedOutputOf;
import ca.rbon.iostream.stream.DataInputOf;
import ca.rbon.iostream.stream.DataOutputOf;
import ca.rbon.iostream.stream.InputStreamOf;
import ca.rbon.iostream.stream.ObjectInputOf;
import ca.rbon.iostream.stream.ObjectOutputOf;
import ca.rbon.iostream.stream.OutputStreamOf;
import ca.rbon.iostream.stream.ZipInputOf;
import ca.rbon.iostream.stream.ZipOutputOf;
import ca.rbon.iostream.writer.BufferedReaderOf;
import ca.rbon.iostream.writer.BufferedWriterOf;
import ca.rbon.iostream.writer.PrintWriterOf;
import ca.rbon.iostream.writer.ReaderOf;
import ca.rbon.iostream.writer.WriterOf;

public abstract class BasePicker<T> extends ClosingResource<T> {
    
    protected abstract Reader getReader(Chain ch) throws IOException;
    
    protected abstract Writer getWriter(Chain ch) throws IOException;
    
    protected abstract OutputStream getOutputStream() throws IOException;
    
    protected abstract InputStream getInputStream() throws IOException;
    
    // SOURCE
    
    private InputStream wrappedInputStream(Charset charset, int bufferSize) throws IOException {
        return Buffering.buffer(chainAdd(getInputStream()), bufferSize, this);
    }
    
    private Reader wrappedReader(Charset charset, int bufferSize) throws IOException {
        Reader natural = getReader(this);
        Reader encoded = natural != null
                ? natural
                : Encoding.streamReader(chainAdd(getInputStream()), charset);
        encoded = chainAdd(encoded);
        return Buffering.buffer(encoded, bufferSize, this);
    }
    
    // SINK
    
    private OutputStream wrappedOutputStream(Charset charset, int bufferSize) throws IOException {
        return Buffering.buffer(chainAdd(getOutputStream()), bufferSize, this);
    }
    
    private Writer wrappedWriter(Charset charset, int bufferSize) throws IOException {
        Writer natural = getWriter(this);
        Writer encoded = natural != null
                ? natural
                : Encoding.streamWriter(chainAdd(getOutputStream()), charset);
        encoded = chainAdd(encoded);
        return Buffering.buffer(encoded, bufferSize, this);
    }
    
    // INPUT
    
    public ZipInputOf<T> zipInputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipInputOf<>(this, wrappedInputStream(null, bufferSize))
                : new ZipInputOf<>(this, wrappedInputStream(null, bufferSize), charset);
    }
    
    public BufferedInputOf<T> bufferedInputStream(int bufferSize) throws IOException {
        return new BufferedInputOf<>(this, wrappedInputStream(null, bufferSize));
    }
    
    public InputStreamOf<T> inputStream() throws IOException {
        return new InputStreamOf<>(this, wrappedInputStream(null, Buffering.UNBUFFERED));
    }
    
    public DataInputOf<T> dataInputStream(int bufferSize) throws IOException {
        return new DataInputOf<>(this, wrappedInputStream(null, bufferSize));
    }
    
    public ObjectInputOf<T> objectInputStream(int bufferSize) throws IOException {
        return new ObjectInputOf<>(this, wrappedInputStream(null, bufferSize));
    }
    
    public BufferedReaderOf<T> bufferedReader(Charset charset, int bufferSize) throws IOException {
        return new BufferedReaderOf<>(this, wrappedReader(charset, bufferSize));
    }
    
    public ReaderOf<T> reader(Charset charset) throws IOException {
        return new ReaderOf<>(this, wrappedReader(charset, Buffering.UNBUFFERED));
    }
    
    // OUTPUT
    
    public ZipOutputOf<T> zipOutputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipOutputOf<>(this, wrappedOutputStream(null, bufferSize))
                : new ZipOutputOf<>(this, wrappedOutputStream(null, bufferSize), charset);
    }
    
    public BufferedOutputOf<T> bufferedOutputStream(int bufferSize) throws IOException {
        return new BufferedOutputOf<>(this, wrappedOutputStream(null, bufferSize));
    }
    
    public OutputStreamOf<T> outputStream() throws IOException {
        return new OutputStreamOf<>(this, wrappedOutputStream(null, Buffering.UNBUFFERED));
    }
    
    public DataOutputOf<T> dataOutputStream(int bufferSize) throws IOException {
        return new DataOutputOf<>(this, wrappedOutputStream(null, bufferSize));
    }
    
    public ObjectOutputOf<T> objectOutputStream(int bufferSize) throws IOException {
        return new ObjectOutputOf<>(this, wrappedOutputStream(null, bufferSize));
    }
    
    public PrintWriterOf<T> printWriter(Charset charset, int bufferSize) throws IOException {
        return new PrintWriterOf<>(this, wrappedWriter(charset, bufferSize));
    }
    
    public PrintWriterOf<T> printWriter(Charset charset, int bufferSize, boolean autoflush) throws IOException {
        return new PrintWriterOf<>(this, wrappedWriter(charset, bufferSize), autoflush);
    }
    
    public BufferedWriterOf<T> bufferedWriter(Charset charset, int bufferSize) throws IOException {
        return new BufferedWriterOf<>(this, wrappedWriter(charset, bufferSize));
    }
    
    public WriterOf<T> writer(Charset charset) throws IOException {
        return new WriterOf<>(this, wrappedWriter(charset, Buffering.UNBUFFERED));
    }
    
    // STRAIGHT
    
    public ReaderOf<T> reader() throws IOException {
        return reader(Encoding.DEFAULT_CHARSET);
    }
    
    public BufferedReaderOf<T> bufferedReader(int bufferSize) throws IOException {
        return bufferedReader(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    public WriterOf<T> writer() throws IOException {
        return writer(Encoding.DEFAULT_CHARSET);
    }
    
    public BufferedWriterOf<T> bufferedWriter(int bufferSize) throws IOException {
        return bufferedWriter(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    public PrintWriterOf<T> printWriter(int bufferSize) throws IOException {
        return printWriter(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    public PrintWriterOf<T> printWriter(int bufferSize, boolean autoflush) throws IOException {
        return printWriter(Encoding.DEFAULT_CHARSET, bufferSize, autoflush);
    }
    
}
