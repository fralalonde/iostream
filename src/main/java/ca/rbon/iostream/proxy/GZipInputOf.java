package ca.rbon.iostream.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

/**
 * <p>ZipInputOf class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class GZipInputOf<T> extends GZIPInputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    /**
     * <p>Constructor for ZipInputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param is a {@link java.io.InputStream} object.
     * @throws IOException if the stream can not be built
     */
    public GZipInputOf(ClosingResource<T> cl, InputStream is) throws IOException {
        super(is);
        closer = cl;
    }
    
    /**
     * <p>Constructor for ZipInputOf.</p>
     *
     * @param cl a {@link ca.rbon.iostream.ClosingResource} object.
     * @param is a {@link java.io.InputStream} object.
     * @param size an int.
     * @throws IOException if the stream can not be built
     */
    public GZipInputOf(ClosingResource<T> cl, InputStream is, int size) throws IOException {
        super(is, size);
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
