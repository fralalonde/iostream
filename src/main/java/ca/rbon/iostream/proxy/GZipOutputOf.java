package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>
 * ZipOutputOf class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class GZipOutputOf<T> extends GZIPOutputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    /**
     * <p>
     * Constructor for ZipOutputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @throws IOException if the stream can not be built
     */
    public GZipOutputOf(ClosingResource<T> cl, OutputStream os) throws IOException {
        super(os);
        closer = cl;
    }
    
    /**
     * <p>
     * Constructor for ZipOutputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @param size is an int.
     * @throws IOException if the stream can not be built
     */
    public GZipOutputOf(ClosingResource<T> cl, OutputStream os, int size) throws IOException {
        super(os, size);
        closer = cl;
    }
    
    /** {@inheritDoc} */
    @Override
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
