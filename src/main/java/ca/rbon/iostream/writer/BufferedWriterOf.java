package ca.rbon.iostream.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>BufferedWriterOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class BufferedWriterOf<T> extends BufferedWriter implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    /**
     * <p>Constructor for BufferedWriterOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param wr a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public BufferedWriterOf(ClosingResource<T> cl, Writer wr) throws IOException {
        super(wr);        
        closer = cl;
    }
    
    /**
     * <p>close.</p>
     *
     * @throws java.io.IOException if any.
     */
    public void close() throws IOException {
        super.close();
        closer.close();
    }
    
    /** {@inheritDoc} */
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
