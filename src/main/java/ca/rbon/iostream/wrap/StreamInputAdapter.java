package ca.rbon.iostream.wrap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import lombok.SneakyThrows;

/**
 * @author fralalonde
 */
public class StreamInputAdapter {

    static final IntPredicate END_OF_STREAM = x -> x != -1;

    /**
     * Adapted from StackOverflow {@linkplain so
     * http://stackoverflow.com/questions/20746429/limit-a-stream-by-a-predicate}
     *
     * @param splitr    the original Spliterator
     * @param predicate the predicate
     * @return a Spliterator.OfInt
     */
    private static Spliterator.OfInt takeIntWhile(Spliterator.OfInt splitr, IntPredicate predicate) {
        return new Spliterators.AbstractIntSpliterator(splitr.estimateSize(), 0) {
            boolean stillGoing = true;

            @Override
            public boolean tryAdvance(IntConsumer consumer) {
                if (stillGoing) {
                    boolean hadNext = splitr.tryAdvance((int elem) -> {
                        if (predicate.test(elem)) {
                            consumer.accept(elem);
                        } else {
                            stillGoing = false;
                        }
                    });
                    return hadNext && stillGoing;
                }
                return false;
            }
        };
    }

    private static IntStream takeIntWhile(IntStream stream, IntPredicate predicate) {
        return StreamSupport.intStream(takeIntWhile(stream.spliterator(), predicate), false);
    }

    /**
     * @param input the input stream
     * @return an {@link IntStream} iterating over the bytes of the
     *         {@link InputStream}
     */
    public static IntStream toIntStream(InputStream input) {
        return takeIntWhile(IntStream.generate(() -> apparentlySafeRead(input)), END_OF_STREAM);
    }

    @SneakyThrows(IOException.class)
    private static int apparentlySafeRead(InputStream input) {
        return input.read();
    }

}
