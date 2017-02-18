package ca.rbon.iostream.channel.part;

public interface Filter<GZIPPED> {
    
    GZIPPED gzip(int bufferSize);
    
    GZIPPED gzip();
    
}
