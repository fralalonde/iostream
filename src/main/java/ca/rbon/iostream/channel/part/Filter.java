package ca.rbon.iostream.channel.part;

public interface Filter<SAME> {
    
    SAME gzip(int bufferSize);
    
    SAME gzip();
    
}
