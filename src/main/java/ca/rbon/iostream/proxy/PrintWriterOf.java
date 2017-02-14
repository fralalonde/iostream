package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import ca.rbon.iostream.Resource;
import ca.rbon.iostream.resource.BaseResource;
import lombok.SneakyThrows;

/**
 * DO NOT USE PrintWriter OutputStream-wrapping constructor, since they create their own BufferingOutputStream, which would resultin double-buffering.
 *
 * @author fralalonde
 * @param <T> Resource type
 * @version $Id: $Id
 */
public class PrintWriterOf<T> extends PrintWriter implements Resource<T> {
    
    final BaseResource<T> closer;
    
    /**
     * <p>Constructor for PrintWriterOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param w a {@link java.io.Writer} object.
     */
    public PrintWriterOf(BaseResource<T> cl, Writer w) {
        super(w);
        closer = cl;
    }
    
    /**
     * <p>Constructor for PrintWriterOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param w a {@link java.io.Writer} object.
     * @param autoFlush a boolean.
     */
    public PrintWriterOf(BaseResource<T> cl, Writer w, boolean autoFlush) {
        super(w, autoFlush);
        closer = cl;
    }
    
    /**
     * <p>close.</p>
     */
    @SneakyThrows(IOException.class)
    public void close() {
        super.close();
        closer.close();
    }
    
    /** {@inheritDoc} */
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
