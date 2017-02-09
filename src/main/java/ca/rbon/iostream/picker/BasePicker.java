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
import lombok.Getter;

public abstract class BasePicker<T> {
        
    private final ChainClose chain = new ChainClose();
    
    protected abstract Resource<T> getSupplier();
    
    // SOURCE
    
    protected abstract InputStream getInputStream(Charset charset, Chain chain) throws IOException;
    
    private InputStream wrappedInputStream(Charset charset, int bufferSize) throws IOException {
        return Buffering.buffer(getInputStream(charset, chain), bufferSize, chain);
    }
    
    protected abstract Reader getReader(Chain chain) throws IOException;
    
    private Reader wrappedReader(Charset charset, int bufferSize) throws IOException {
        Reader natural = getReader(chain);
        Reader encoded = natural != null ? natural : Encoding.encode(getInputStream(charset, chain), charset, chain);
        return Buffering.buffer(encoded, bufferSize, chain);
    }
    
    // SINK
    
    protected abstract OutputStream getOutputStream(Charset charset, Chain chain) throws IOException;
    
    private OutputStream wrappedOutputStream(Charset charset, int bufferSize) throws IOException {
        return Buffering.buffer(getOutputStream(charset, chain), bufferSize, chain);
    }
    
    protected abstract Writer getWriter(Chain chain) throws IOException;
    
    private Writer wrappedWriter(Charset charset, int bufferSize) throws IOException {
        Writer natural = getWriter(chain);
        Writer encoded = natural != null ? natural : Encoding.encode(getOutputStream(charset, chain), charset, chain);
        return Buffering.buffer(encoded, bufferSize, chain);
    }
    
    // INPUT
    
    ZipInputProxy<T> zipInputStream(Charset charset, int bufferSize) throws IOException {
        return charset == null
                ? new ZipInputProxy<>(getSupplier(), chain, wrappedInputStream(null, bufferSize))
                : new ZipInputProxy<>(getSupplier(), chain, wrappedInputStream(null, bufferSize), charset);
    }
    
    BufferedInputProxy<T> bufferedInputStream() throws IOException {
        return new BufferedInputProxy<>(getSupplier(), chain, wrappedInputStream());
    }
    
    UnbufferedInputProxy<T> unbufferedInputStream() throws IOException {
        return new UnbufferedInputProxy<>(getSupplier(), chain, wrappedInputStream());
    }
    
    DataInputProxy<T> dataInputStream() throws IOException {
        return new DataInputProxy<>(getSupplier(), chain, wrappedInputStream());
    }
    
    ObjectInputProxy<T> objectInputStream() throws IOException {
        return new ObjectInputProxy<>(getSupplier(), chain, wrappedInputStream());
    }
    
    BufferedReaderProxy<T> bufferedReader() throws IOException {
        return new BufferedReaderProxy<>(getSupplier(), chain, wrappedReader());
    }
    
    UnbufferedReaderProxy<T> unbufferedReader() throws IOException {
        return new UnbufferedReaderProxy<>(getSupplier(), chain, wrappedReader());
    }
    
    // OUTPUT
    
    ZipOutputProxy<T> zipOutputStream() throws IOException {
        return encoding == null
                ? new ZipOutputProxy<>(getSupplier(), chain, wrappedOutputStream())
                : new ZipOutputProxy<>(getSupplier(), chain, wrappedOutputStream(), encoding);
    }
    
    BufferedOutputProxy<T> bufferedOutputStream() throws IOException {
        return new BufferedOutputProxy<>(getSupplier(), chain, wrappedOutputStream());
    }
    
    UnbufferedOutputProxy<T> unbufferedOutputStream() throws IOException {
        return new UnbufferedOutputProxy<>(getSupplier(), chain, wrappedOutputStream());
    }
    
    DataOutputProxy<T> dataOutputStream() throws IOException {
        return new DataOutputProxy<>(getSupplier(), chain, wrappedOutputStream());
    }
    
    ObjectOutputProxy<T> objectOutputStream() throws IOException {
        return new ObjectOutputProxy<>(getSupplier(), chain, wrappedOutputStream());
    }
    
    PrintWriterProxy<T> printWriter() throws IOException {
        return new PrintWriterProxy<>(getSupplier(), chain, wrappedWriter());
    }
    
    PrintWriterProxy<T> printWriter(boolean autoflush) throws IOException {
        return new PrintWriterProxy<>(getSupplier(), chain, wrappedWriter(), autoflush);
    }
    
    BufferedWriterProxy<T> bufferedWriter() throws IOException {
        return new BufferedWriterProxy<>(getSupplier(), chain, wrappedWriter());
    }
    
    UnbufferedWriterProxy<T> unbufferedWriter() throws IOException {
        return new UnbufferedWriterProxy<>(getSupplier(), chain, wrappedWriter());
    }
    
}
