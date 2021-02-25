package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import ca.rbon.iostream.resource.Resource;

/**
 * DO NOT USE PrintWriter OutputStream-wrapping constructor: they create their
 * own BufferingOutputStream, which would result in double-buffered Resource.
 *
 * @param <T> Resource type
 */
public class PrintWriterOf<T> extends PrintWriter implements WrapperOf<T> {

    final Resource<T> closer;

    /**
     * <p>
     * Constructor for PrintWriterOf.
     * </p>
     *
     * @param cl a {@link ca.rbon.iostream.resource.Resource} object.
     * @param w  a {@link java.io.Writer} object.
     */
    public PrintWriterOf(Resource<T> cl, Writer w) {
        super(w);
        closer = cl;
    }

    /**
     * <p>
     * Constructor for PrintWriterOf.
     * </p>
     *
     * @param cl        a {@link ca.rbon.iostream.resource.Resource} object.
     * @param w         a {@link java.io.Writer} object.
     * @param autoFlush a boolean.
     */
    public PrintWriterOf(Resource<T> cl, Writer w, boolean autoFlush) {
        super(w, autoFlush);
        closer = cl;
    }

    /** {@inheritDoc} */
    @Override
    public T getInner() throws IOException {
        return closer.getResource();
    }

    /**
     * Use the PrintWriter inline and return the inner resource.
     * @param operation The operation to apply.
     * @return The inner resource.
     * @throws IOException if the passed in closure throws
     */
    public T with(BiConsumer<PrintWriterOf<T>, T> operation) throws IOException {
        operation.accept(this, getInner());
        return getInner();
    }

}
