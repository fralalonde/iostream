package ca.rbon.iostream.proxy;

import java.io.IOException;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.flow.CharSink;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;

public interface WriterBuilder<T> {
    
    CharSink<T> getCharSink() throws IOException;
    
    default PrintWriterProxy<T> printWriter() throws IOException {
        ChainClose toClose = new ChainClose();
        return new PrintWriterProxy<>(getCharSink(), toClose, getCharSink().getWriter(toClose));
    }
    
    default PrintWriterProxy<T> printWriter(boolean autoflush) throws IOException {
        ChainClose toClose = new ChainClose();
        return new PrintWriterProxy<>(getCharSink(), toClose, getCharSink().getWriter(toClose), autoflush);
    }
    
    default BufferedWriterProxy<T> bufferedWriter() throws IOException {
        ChainClose toClose = new ChainClose();
        return new BufferedWriterProxy<>(getCharSink(), toClose, getCharSink().getWriter(toClose));
    }
    
}
