package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.function.BiConsumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import ca.rbon.iostream.resource.Resource;
import ca.rbon.iostream.utils.IOUtils;

/**
 * <p>
 * ZipOutputOf class.
 * </p>
 *
 * @param <T> Resource type
 */
public class ZipOutputOf<T> extends ZipOutputStream implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for ZipOutputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.OutputStream} object.
     */
    public ZipOutputOf(Resource<T> cl, OutputStream os) {
        super(os);
        closer = cl;
    }

    /**
     * <p>
     * Constructor for ZipOutputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param os a {@link java.io.OutputStream} object.
     * @param cs a {@link java.nio.charset.Charset} object.
     */
    public ZipOutputOf(Resource<T> cl, OutputStream os, Charset cs) {
        super(os, cs);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

    public ZipOutputOf<T> addZipEntry(ZipEntry entry, InputStream data) throws IOException {
        this.putNextEntry(entry);
        IOUtils.copy(data, this);
        this.closeEntry();
        return this;
    }

    /**
     * Use the ZipOutputStream inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<ZipOutputOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }
}
