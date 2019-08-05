package ca.rbon.iostream.resource;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.IntSupplier;

@RequiredArgsConstructor
public final class IntSupplierInputStream extends InputStream {

    final IntSupplier supplier;

    @Override
    public int read() throws IOException {
        return supplier.getAsInt();
    }

}
