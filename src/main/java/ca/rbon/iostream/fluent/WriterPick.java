package ca.rbon.iostream.fluent;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.picker.Encoding;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;
import ca.rbon.iostream.proxy.writer.UnbufferedWriterProxy;

public interface WriterPick<T> {
    
    UnbufferedWriterProxy<T> writer(Charset charset) throws IOException;
    
    default UnbufferedWriterProxy<T> writer(String charsetName) throws IOException {
        return writer(Charset.forName(charsetName));
    }
    
    default UnbufferedWriterProxy<T> writer() throws IOException {
        return writer(Encoding.DEFAULT_CHARSET);
    }
    
    // BUFFERED
    
    BufferedWriterProxy<T> bufferedWriter(Charset charset, int bufferSize) throws IOException;
    
    default BufferedWriterProxy<T> bufferedWriter(Charset charset) throws IOException {
        return bufferedWriter(Encoding.DEFAULT_CHARSET, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    default BufferedWriterProxy<T> bufferedWriter(String charsetName) throws IOException {
        return bufferedWriter(Charset.forName(charsetName), Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    default BufferedWriterProxy<T> bufferedWriter(int bufferSize) throws IOException {
        return bufferedWriter(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    default BufferedWriterProxy<T> bufferedWriter() throws IOException {
        return bufferedWriter(Encoding.DEFAULT_CHARSET, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    // PRINT
    
    PrintWriterProxy<T> printWriter(Charset charset, int bufferSize) throws IOException;
    
    default PrintWriterProxy<T> printWriter(Charset charset) throws IOException {
        return printWriter(charset, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    default PrintWriterProxy<T> printWriter(String charsetName) throws IOException {
        return printWriter(Charset.forName(charsetName), Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    default PrintWriterProxy<T> printWriter(int bufferSize) throws IOException {
        return printWriter(Encoding.DEFAULT_CHARSET, bufferSize);
    }
    
    default PrintWriterProxy<T> printWriter() throws IOException {
        return printWriter(Encoding.DEFAULT_CHARSET, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    // PRINT AUTOFLUSH
    
    PrintWriterProxy<T> printWriter(Charset charset, int bufferSize, boolean autoflush) throws IOException;
    
    default PrintWriterProxy<T> printWriter(String charsetName, int bufferSize, boolean autoflush) throws IOException {
        return printWriter(Charset.forName(charsetName), bufferSize, autoflush);
    }
    
    default PrintWriterProxy<T> printWriter(Charset charset, boolean autoflush) throws IOException {
        return printWriter(charset, Buffering.DEFAULT_BUFFER_SIZE, autoflush);
    }
    
    default PrintWriterProxy<T> printWriter(String charsetName, boolean autoflush) throws IOException {
        return printWriter(charsetName, Buffering.DEFAULT_BUFFER_SIZE, autoflush);
    }
    
    default PrintWriterProxy<T> printWriter(int bufferSize, boolean autoflush) throws IOException {
        return printWriter(Encoding.DEFAULT_CHARSET, bufferSize, autoflush);
    }
    
    default PrintWriterProxy<T> printWriter(boolean autoflush) throws IOException {
        return printWriter(Encoding.DEFAULT_CHARSET, Buffering.DEFAULT_BUFFER_SIZE, autoflush);
    }
    
}
