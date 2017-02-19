package wrap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import lombok.SneakyThrows;

public class StreamInputAdapter {
    
    public static Spliterator.OfInt takeIntWhile(Spliterator.OfInt splitr, IntPredicate predicate) {
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
    
    static final IntPredicate END_OF_STREAM = x -> x == -1;
    
    public static IntStream takeIntWhile(IntStream stream, IntPredicate predicate) {
        return StreamSupport.intStream(takeIntWhile(stream.spliterator(), predicate), false);
    }
    
    @SneakyThrows(IOException.class)
    private static int apparentlySafeRead(InputStream input) {
        return input.read();
    }
    
    public static IntStream toIntStream(InputStream input) {
        return takeIntWhile(IntStream.generate(() -> apparentlySafeRead(input)), END_OF_STREAM);
    }
    
}
