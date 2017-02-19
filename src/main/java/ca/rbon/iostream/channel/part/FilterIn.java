package ca.rbon.iostream.channel.part;

import java.io.InputStream;
import java.util.function.UnaryOperator;

public interface FilterIn<SAME> {
    
    SAME inputFilter(UnaryOperator<InputStream> filter);
    
}
