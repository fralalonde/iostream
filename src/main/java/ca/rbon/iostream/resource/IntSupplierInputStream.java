package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.IntSupplier;

public final class IntSupplierInputStream extends InputStream {

    final IntSupplier supplier;

    public IntSupplierInputStream(IntSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public int read() throws IOException {
        return supplier.getAsInt();
    }

}
