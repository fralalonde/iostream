package ca.rbon.iostream.fluent;

import java.io.IOException;

import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;

public interface InCharPick<T> {
 
    BufferedReaderProxy<T> bufferedReader() throws IOException;
}
