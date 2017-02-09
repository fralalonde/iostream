package ca.rbon.iostream.proxy;

import java.io.IOException;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.flow.CharSource;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;

public interface ReaderBuilder<T> {
    
    CharSource<T> getCharSource() throws IOException;
    
    default BufferedReaderProxy<T> bufferedReader() throws IOException {
        ChainClose toClose = new ChainClose();
        return new BufferedReaderProxy<>(getCharSource(), toClose, getCharSource().getReader(toClose));
    }
    
}
