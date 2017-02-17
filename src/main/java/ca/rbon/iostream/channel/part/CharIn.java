package ca.rbon.iostream.channel.part;

import java.io.IOException;

import ca.rbon.iostream.proxy.BufferedReaderOf;
import ca.rbon.iostream.proxy.ReaderOf;
import ca.rbon.iostream.resource.Resource;

public interface CharIn<T> {
    
    ReaderOf<T> reader() throws IOException;
    
    BufferedReaderOf<T> bufferedReader(int bufferSize) throws IOException;
    
    default BufferedReaderOf<T> bufferedReader() throws IOException {
        return bufferedReader(Resource.DEFAULT_BUFFER_SIZE);
    }
    
}
