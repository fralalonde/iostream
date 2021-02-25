package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.function.BiConsumer;
import java.util.zip.ZipInputStream;

import ca.rbon.iostream.resource.Resource;

/**
 * <p>
 * ZipInputOf class.
 * </p>
 *
 * @param <T> Resource type
 */
public class ZipInputOf<T> extends ZipInputStream implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for ZipInputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param is a {@link java.io.InputStream} object.
     */
    public ZipInputOf(Resource<T> cl, InputStream is) {
        super(is);
        closer = cl;
    }

    /**
     * <p>
     * Constructor for ZipInputOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param is a {@link java.io.InputStream} object.
     * @param cs a {@link java.nio.charset.Charset} object.
     */
    public ZipInputOf(Resource<T> cl, InputStream is, Charset cs) {
        super(is, cs);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

    /**
     * Use the ZipInputStream inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<ZipInputOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }

}
