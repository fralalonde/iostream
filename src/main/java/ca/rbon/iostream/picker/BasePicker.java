package ca.rbon.iostream.picker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.proxy.stream.BufferedInputProxy;
import ca.rbon.iostream.proxy.stream.BufferedOutputProxy;
import ca.rbon.iostream.proxy.stream.DataInputProxy;
import ca.rbon.iostream.proxy.stream.DataOutputProxy;
import ca.rbon.iostream.proxy.stream.InputStreamProxy;
import ca.rbon.iostream.proxy.stream.ObjectInputProxy;
import ca.rbon.iostream.proxy.stream.ObjectOutputProxy;
import ca.rbon.iostream.proxy.stream.OutputStreamProxy;
import ca.rbon.iostream.proxy.stream.ZipInputProxy;
import ca.rbon.iostream.proxy.stream.ZipOutputProxy;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;
import ca.rbon.iostream.proxy.writer.ReaderProxy;
import ca.rbon.iostream.proxy.writer.WriterProxy;

public abstract class BasePicker<T> extends ChainClose<T> {
        
    protected abstract Reader getReader(Chain ch) throws IOException;
    
    protected abstract Writer getWriter(Chain ch) throws IOException;
    
    protected abstract OutputStream getOutputStream() throws IOException;
    
    protected abstract InputStream getInputStream() throws IOException;
            
    // SOURCE
    
    private InputStream wrappedInputStream(Charset charset, int bufferSize) throws IOException {
        return Buffering.buffer(getInputStream(), bufferSize, this);
    }
    
    private Reader wrappedReader(Charset charset, int bufferSize) throws IOException {
        Reader natural = getReader(this);
        Reader encoded = natural != null ? natural : Encoding.encode(this.add(getInputStream()), charset);
        return this.add(Buffering.buffer(encoded, bufferSize, this));
    }
    
    // SINK
    
    private OutputStream wrappedOutputStream(Charset charset, int bufferSize) throws IOException {
        return this.add(Buffering.buffer(getOutputStream(), bufferSize, this));
    }
    
    private Writer wrappedWriter(Charset charset, int bufferSize) throws IOException {
        Writer natural = getWriter(this);
        Writer encoded = natural != null ? natural : Encoding.encode(this.add(getOutputStream()), charset);
        return this.add(Buffering.buffer(encoded, bufferSize, this));
    }
    
    // INPUT
    
    public ZipInputProxy<T> zipInputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipInputProxy<>(this, wrappedInputStream(null, bufferSize))
                : new ZipInputProxy<>(this, wrappedInputStream(null, bufferSize), charset);
    }
    
    public BufferedInputProxy<T> bufferedInputStream(int bufferSize) throws IOException {
        return new BufferedInputProxy<>(this, wrappedInputStream(null, bufferSize));
    }
    
    public InputStreamProxy<T> inputStream() throws IOException {
        return new InputStreamProxy<>(this, wrappedInputStream(null, Buffering.UNBUFFERED));
    }
    
    public DataInputProxy<T> dataInputStream(int bufferSize) throws IOException {
        return new DataInputProxy<>(this, wrappedInputStream(null, bufferSize));
    }
    
    public ObjectInputProxy<T> objectInputStream(int bufferSize) throws IOException {
        return new ObjectInputProxy<>(this, wrappedInputStream(null, bufferSize));
    }
    
    public BufferedReaderProxy<T> bufferedReader(Charset charset, int bufferSize) throws IOException {
        return new BufferedReaderProxy<>(this, wrappedReader(charset, bufferSize));
    }
    
    public ReaderProxy<T> reader(Charset charset) throws IOException {
        return new ReaderProxy<>(this, wrappedReader(charset, Buffering.UNBUFFERED));
    }
    
    // OUTPUT
    
    public ZipOutputProxy<T> zipOutputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipOutputProxy<>(this, wrappedOutputStream(null, bufferSize))
                : new ZipOutputProxy<>(this, wrappedOutputStream(null, bufferSize), charset);
    }
    
    public BufferedOutputProxy<T> bufferedOutputStream(int bufferSize) throws IOException {
        return new BufferedOutputProxy<>(this, wrappedOutputStream(null, bufferSize));
    }
    
    public OutputStreamProxy<T> outputStream() throws IOException {
        return new OutputStreamProxy<>(this, wrappedOutputStream(null, Buffering.UNBUFFERED));
    }
    
    public DataOutputProxy<T> dataOutputStream(int bufferSize) throws IOException {
        return new DataOutputProxy<>(this, wrappedOutputStream(null, bufferSize));
    }
    
    public ObjectOutputProxy<T> objectOutputStream(int bufferSize) throws IOException {
        return new ObjectOutputProxy<>(this, wrappedOutputStream(null, bufferSize));
    }
    
    public PrintWriterProxy<T> printWriter(Charset charset, int bufferSize) throws IOException {
        return new PrintWriterProxy<>(this, wrappedWriter(charset, bufferSize));
    }
    
    public PrintWriterProxy<T> printWriter(Charset charset, int bufferSize, boolean autoflush) throws IOException {
        return new PrintWriterProxy<>(this, wrappedWriter(charset, bufferSize), autoflush);
    }
    
    public BufferedWriterProxy<T> bufferedWriter(Charset charset, int bufferSize) throws IOException {
        return new BufferedWriterProxy<>(this, wrappedWriter(charset, bufferSize));
    }
    
    public WriterProxy<T> writer(Charset charset) throws IOException {
        return new WriterProxy<>(this, wrappedWriter(charset, Buffering.UNBUFFERED));
    }
    
    // STRAIGHT
    
    public ReaderProxy<T> reader() throws IOException {
        return reader(Encoding.DEFAULT_CHARSET);
    }
    
    public BufferedReaderProxy<T> bufferedReader(int bufferSize) throws IOException {
        return bufferedReader(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    public WriterProxy<T> writer() throws IOException {
        return writer(Encoding.DEFAULT_CHARSET);
    }
    
    public BufferedWriterProxy<T> bufferedWriter(int bufferSize) throws IOException {
        return bufferedWriter(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    public PrintWriterProxy<T> printWriter(int bufferSize) throws IOException {
        return printWriter(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    public PrintWriterProxy<T> printWriter(int bufferSize, boolean autoflush) throws IOException {
        return printWriter(Encoding.DEFAULT_CHARSET, bufferSize, autoflush);
    }
    
}
