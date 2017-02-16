package ca.rbon.iostream.proxy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>BufferedWriterOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class BufferedWriterOf<T> extends BufferedWriter  {
    
    final Resource<T> closer;
    
    /**
     * <p>Constructor for BufferedWriterOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param wr a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public BufferedWriterOf(Resource<T> cl, Writer wr) throws IOException {
        super(wr);        
        closer = cl;
    }
    
    /** {@inheritDoc} */
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
