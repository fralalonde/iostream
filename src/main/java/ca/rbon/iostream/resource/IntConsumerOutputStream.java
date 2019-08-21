package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.IntConsumer;

public final class IntConsumerOutputStream extends OutputStream {

    final IntConsumer consumer;

    public IntConsumerOutputStream(IntConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void write(int b) throws IOException {
        consumer.accept(b);
    }
}
