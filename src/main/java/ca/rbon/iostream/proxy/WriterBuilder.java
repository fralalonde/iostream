package ca.rbon.iostream.proxy;

import java.io.IOException;

import ca.rbon.iostream.Closer;
import ca.rbon.iostream.flow.CharSink;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;

public interface WriterBuilder<T> {
    
    CharSink<T> getCharSink() throws IOException;
    
    default PrintWriterProxy<T> printWriter() throws IOException {
        Closer toClose = new Closer();
        return new PrintWriterProxy<>(getCharSink(), toClose, getCharSink().getWriter(toClose));
    }
    
    default PrintWriterProxy<T> printWriter(boolean autoflush) throws IOException {
        Closer toClose = new Closer();
        return new PrintWriterProxy<>(getCharSink(), toClose, getCharSink().getWriter(toClose), autoflush);
    }
    
    default BufferedWriterProxy<T> bufferedWriter() throws IOException {
        Closer toClose = new Closer();
        return new BufferedWriterProxy<>(getCharSink(), toClose, getCharSink().getWriter(toClose));
    }
    
}
