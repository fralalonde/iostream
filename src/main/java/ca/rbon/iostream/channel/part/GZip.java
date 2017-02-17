package ca.rbon.iostream.channel.part;

import ca.rbon.iostream.resource.Resource;

public interface GZip<GZIPPED> {
    
    GZIPPED gzip(int bufferSize);
    
    default GZIPPED gzip() {
        return gzip(Resource.DEFAULT_BUFFER_SIZE);
    }
    
}
