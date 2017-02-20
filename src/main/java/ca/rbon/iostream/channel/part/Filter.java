package ca.rbon.iostream.channel.part;

import java.util.Base64;

import ca.rbon.iostream.channel.filter.FilterFactory;

public interface Filter<SAME> {
    
    /**
     * Apply generic filter to resource.
     * 
     * @param filter the filter to apply
     * @return the continuation of the IoStream channel builder.
     */
    SAME filter(FilterFactory filter);
    
    /**
     * Apply GZip filter to resource.
     * 
     * @return the continuation of the IoStream channel builder.
     */
    SAME gzip();
    
    /**
     * Apply GZip filter to resource.
     * 
     * @param bufferSize size of the gzip buffer
     * @return the continuation of the IoStream channel builder.
     */
    SAME gzip(int bufferSize);
    
    /**
     * Apply {@link Base64} filter to resource.
     * 
     * @return the continuation of the IoStream channel builder.
     */
    SAME base64();
    
}
