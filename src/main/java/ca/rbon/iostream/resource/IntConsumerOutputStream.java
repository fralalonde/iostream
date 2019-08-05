package ca.rbon.iostream.resource;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

@RequiredArgsConstructor
public final class IntConsumerOutputStream extends OutputStream {

    final IntConsumer consumer;

    @Override
    public void write(int b) throws IOException {
        consumer.accept(b);
    }
}
