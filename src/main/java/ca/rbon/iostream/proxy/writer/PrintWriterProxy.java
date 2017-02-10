package ca.rbon.iostream.proxy.writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;
import lombok.SneakyThrows;

/**
 * DO NOT USE PrintWriter OutputStream-wrapping constructor, since they create their own BufferingOutputStream, which would resultin double-buffering.
 * 
 * @author fralalonde
 * @param <T>
 */
public class PrintWriterProxy<T> extends PrintWriter implements Resource<T> {
    
    final ChainClose<T> closer;
    
    public PrintWriterProxy(ChainClose<T> cl, Writer w) {
        super(w);
        cl.add(w);
        closer = cl;
    }
    
    public PrintWriterProxy(ChainClose<T> cl, Writer w, boolean autoFlush) {
        super(w, autoFlush);
        cl.add(w);
        closer = cl;
    }
    
    @SneakyThrows(IOException.class)
    public void close() {
        closer.close();
    }
    
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
