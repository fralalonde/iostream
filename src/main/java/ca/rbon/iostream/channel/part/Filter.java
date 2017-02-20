package ca.rbon.iostream.channel.part;

import java.util.Base64;

import javax.crypto.Cipher;

import ca.rbon.iostream.channel.filter.FilterFactory;

public interface Filter<SAME> {
    
    /**
     * Apply generic filter to the stream.
     * 
     * @param filter the filter to apply
     * @return the continuation of the IoStream channel builder.
     */
    SAME filter(FilterFactory filter);
    
    /**
     * Apply GZip filter to the stream.
     * 
     * @return the continuation of the IoStream channel builder.
     */
    SAME gzip();
    
    /**
     * Apply GZip filter to the stream.
     * 
     * @param bufferSize size of the gzip buffer
     * @return the continuation of the IoStream channel builder.
     */
    SAME gzip(int bufferSize);
    
    /**
     * Apply {@link Base64} filter to the stream.
     * 
     * @return the continuation of the IoStream channel builder.
     */
    SAME base64();
    
    /**
     * Apply {@link Cipher} filter to the stream.
     * 
     * @param cipher the cipher to use. cipher must be initialized before the stream is used.
     * @return the continuation of the IoStream channel builder.
     */
    SAME cipher(Cipher cipher);
    
}
