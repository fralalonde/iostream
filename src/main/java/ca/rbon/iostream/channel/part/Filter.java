package ca.rbon.iostream.channel.part;

import ca.rbon.iostream.channel.filter.FilterFactory;

public interface Filter<SAME> {
    
    SAME gzip();
    
    SAME gzip(int bufferSize);
    
    SAME filter(FilterFactory filter);
    
}
