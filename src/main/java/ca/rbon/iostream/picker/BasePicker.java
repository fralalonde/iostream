package ca.rbon.iostream.picker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;
import ca.rbon.iostream.proxy.stream.BufferedInputProxy;
import ca.rbon.iostream.proxy.stream.BufferedOutputProxy;
import ca.rbon.iostream.proxy.stream.DataInputProxy;
import ca.rbon.iostream.proxy.stream.DataOutputProxy;
import ca.rbon.iostream.proxy.stream.ObjectInputProxy;
import ca.rbon.iostream.proxy.stream.ObjectOutputProxy;
import ca.rbon.iostream.proxy.stream.UnbufferedInputProxy;
import ca.rbon.iostream.proxy.stream.UnbufferedOutputProxy;
import ca.rbon.iostream.proxy.stream.ZipInputProxy;
import ca.rbon.iostream.proxy.stream.ZipOutputProxy;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;
import ca.rbon.iostream.proxy.writer.UnbufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.UnbufferedWriterProxy;

public abstract class BasePicker<T> {
    
    private final ChainClose chain = new ChainClose();
    
    protected abstract Resource<T> getSupplier();
    
    protected abstract Reader getReader(Chain ch) throws IOException;
    
    protected abstract Writer getWriter(Chain ch) throws IOException;
    
    protected abstract OutputStream getOutputStream() throws IOException;
    
    protected abstract InputStream getInputStream() throws IOException;
    
    // SOURCE
    
    private InputStream wrappedInputStream(Charset charset, int bufferSize) throws IOException {
        return Buffering.buffer(getInputStream(), bufferSize, chain);
    }
    
    private Reader wrappedReader(Charset charset, int bufferSize) throws IOException {
        Reader natural = getReader(chain);
        Reader encoded = natural != null ? natural : Encoding.encode(chain.add(getInputStream()), charset);
        return chain.add(Buffering.buffer(encoded, bufferSize, chain));
    }
    
    // SINK
    
    private OutputStream wrappedOutputStream(Charset charset, int bufferSize) throws IOException {
        return chain.add(Buffering.buffer(getOutputStream(), bufferSize, chain));
    }
    
    private Writer wrappedWriter(Charset charset, int bufferSize) throws IOException {
        Writer natural = getWriter(chain);
        Writer encoded = natural != null ? natural : Encoding.encode(chain.add(getOutputStream()), charset);
        return chain.add(Buffering.buffer(encoded, bufferSize, chain));
    }
    
    // INPUT
    
    public ZipInputProxy<T> zipInputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipInputProxy<>(getSupplier(), chain, wrappedInputStream(null, bufferSize))
                : new ZipInputProxy<>(getSupplier(), chain, wrappedInputStream(null, bufferSize), charset);
    }
    
    public BufferedInputProxy<T> bufferedInputStream(int bufferSize) throws IOException {
        return new BufferedInputProxy<>(getSupplier(), chain, wrappedInputStream(null, bufferSize));
    }
    
    public UnbufferedInputProxy<T> inputStream() throws IOException {
        return new UnbufferedInputProxy<>(getSupplier(), chain, wrappedInputStream(null, Buffering.UNBUFFERED));
    }
    
    public DataInputProxy<T> dataInputStream(int bufferSize) throws IOException {
        return new DataInputProxy<>(getSupplier(), chain, wrappedInputStream(null, bufferSize));
    }
    
    public ObjectInputProxy<T> objectInputStream(int bufferSize) throws IOException {
        return new ObjectInputProxy<>(getSupplier(), chain, wrappedInputStream(null, bufferSize));
    }
    
    public BufferedReaderProxy<T> bufferedReader(Charset charset, int bufferSize) throws IOException {
        return new BufferedReaderProxy<>(getSupplier(), chain, wrappedReader(charset, bufferSize));
    }
    
    public UnbufferedReaderProxy<T> reader(Charset charset) throws IOException {
        return new UnbufferedReaderProxy<>(getSupplier(), chain, wrappedReader(charset, Buffering.UNBUFFERED));
    }
    
    // OUTPUT
    
    public ZipOutputProxy<T> zipOutputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipOutputProxy<>(getSupplier(), chain, wrappedOutputStream(null, bufferSize))
                : new ZipOutputProxy<>(getSupplier(), chain, wrappedOutputStream(null, bufferSize), charset);
    }
    
    public BufferedOutputProxy<T> bufferedOutputStream(int bufferSize) throws IOException {
        return new BufferedOutputProxy<>(getSupplier(), chain, wrappedOutputStream(null, bufferSize));
    }
    
    public UnbufferedOutputProxy<T> outputStream() throws IOException {
        return new UnbufferedOutputProxy<>(getSupplier(), chain, wrappedOutputStream(null, Buffering.UNBUFFERED));
    }
    
    public DataOutputProxy<T> dataOutputStream(int bufferSize) throws IOException {
        return new DataOutputProxy<>(getSupplier(), chain, wrappedOutputStream(null, bufferSize));
    }
    
    public ObjectOutputProxy<T> objectOutputStream(int bufferSize) throws IOException {
        return new ObjectOutputProxy<>(getSupplier(), chain, wrappedOutputStream(null, bufferSize));
    }
    
    public PrintWriterProxy<T> printWriter(Charset charset, int bufferSize) throws IOException {
        return new PrintWriterProxy<>(getSupplier(), chain, wrappedWriter(charset, bufferSize));
    }
    
    public PrintWriterProxy<T> printWriter(Charset charset, int bufferSize, boolean autoflush) throws IOException {
        return new PrintWriterProxy<>(getSupplier(), chain, wrappedWriter(charset, bufferSize), autoflush);
    }
    
    public BufferedWriterProxy<T> bufferedWriter(Charset charset, int bufferSize) throws IOException {
        return new BufferedWriterProxy<>(getSupplier(), chain, wrappedWriter(charset, bufferSize));
    }
    
    public UnbufferedWriterProxy<T> writer(Charset charset) throws IOException {
        return new UnbufferedWriterProxy<>(getSupplier(), chain, wrappedWriter(charset, Buffering.UNBUFFERED));
    }
    
}
