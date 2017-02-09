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
import ca.rbon.iostream.proxy.stream.ZipInputProxy;
import ca.rbon.iostream.proxy.stream.ZipOutputProxy;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;
import lombok.Getter;

public abstract class BasePicker<T> {
    
    final ChainClose chain = new ChainClose();
    
    @Getter
    Charset encoding;
    
    int bufferSize;

    protected abstract Resource<T> getSupplier();       
    
    // SOURCE
    
    protected abstract InputStream getInputStream(Chain chain) throws IOException;

    protected abstract Reader getReader(Chain chain) throws IOException;
    
    // SINK
    
    protected abstract OutputStream getOutputStream(Chain chain) throws IOException;

    protected abstract Writer getWriter(Chain chain) throws IOException;        
    
    // INPUT    
    
    ZipInputProxy<T> zipInputStream() throws IOException {
        return new ZipInputProxy<>(getSupplier(), chain, getInputStream(chain));
    }
    
    BufferedInputProxy<T> bufferedInputStream() throws IOException {
        return new BufferedInputProxy<>(getSupplier(), chain, getInputStream(chain));
    }
    
    DataInputProxy<T> dataInputStream() throws IOException {
        return new DataInputProxy<>(getSupplier(), chain, getInputStream(chain));
    }
    
    ObjectInputProxy<T> objectInputStream() throws IOException {
        return new ObjectInputProxy<>(getSupplier(), chain, getInputStream(chain));
    }
    
    BufferedReaderProxy<T> bufferedReader() throws IOException {
        return new BufferedReaderProxy<>(getSupplier(), chain, getReader(chain));
    }
    
    // OUTPUT
    
    ZipOutputProxy<T> zipOutputStream() throws IOException {
        return new ZipOutputProxy<>(getSupplier(), chain, getOutputStream(chain));
    }
    
    BufferedOutputProxy<T> bufferedOutputStream() throws IOException {
        return new BufferedOutputProxy<>(getSupplier(), chain, getOutputStream(chain));
    }
    
    DataOutputProxy<T> dataOutputStream() throws IOException {
        return new DataOutputProxy<>(getSupplier(), chain, getOutputStream(chain));
    }
    
    ObjectOutputProxy<T> objectOutputStream() throws IOException {
        return new ObjectOutputProxy<>(getSupplier(), chain, getOutputStream(chain));
    }
    
    PrintWriterProxy<T> printWriter() throws IOException {
        return new PrintWriterProxy<>(getSupplier(), chain, getWriter(chain));
    }
    
    PrintWriterProxy<T> printWriter(boolean autoflush) throws IOException {
        return new PrintWriterProxy<>(getSupplier(), chain, getWriter(chain), autoflush);
    }
    
    BufferedWriterProxy<T> bufferedWriter() throws IOException {
        return new BufferedWriterProxy<>(getSupplier(), chain, getWriter(chain));
    }
    
}
