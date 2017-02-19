package ca.rbon.iostream.channel.part;

import java.io.OutputStream;
import java.util.function.UnaryOperator;

public interface FilterOut<SAME> {
    
    SAME outputFilter(UnaryOperator<OutputStream> filter);
    
}
