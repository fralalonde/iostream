package ca.rbon.iostream.writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;
import lombok.SneakyThrows;

/**
 * DO NOT USE PrintWriter OutputStream-wrapping constructor, since they create their own BufferingOutputStream, which would resultin double-buffering.
 * 
 * @author fralalonde
 * @param <T>
 */
public class PrintWriterOf<T> extends PrintWriter implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public PrintWriterOf(ClosingResource<T> cl, Writer w) {
        super(w);
        closer = cl;
    }
    
    public PrintWriterOf(ClosingResource<T> cl, Writer w, boolean autoFlush) {
        super(w, autoFlush);
        closer = cl;
    }
    
    @SneakyThrows(IOException.class)
    public void close() {
        super.close();
        closer.close();
    }
    
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
