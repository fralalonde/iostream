package ca.rbon.iostream.proxy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.Resource;
import ca.rbon.iostream.resource.BaseResource;

/**
 * <p>BufferedWriterOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class BufferedWriterOf<T> extends BufferedWriter implements Resource<T> {
    
    final BaseResource<T> closer;
    
    /**
     * <p>Constructor for BufferedWriterOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.BaseResource} object.
     * @param wr a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public BufferedWriterOf(BaseResource<T> cl, Writer wr) throws IOException {
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
