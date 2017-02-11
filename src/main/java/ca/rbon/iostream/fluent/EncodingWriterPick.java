package ca.rbon.iostream.fluent;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.picker.Buffering;
import ca.rbon.iostream.picker.Encoding;
import ca.rbon.iostream.writer.BufferedWriterOf;
import ca.rbon.iostream.writer.PrintWriterOf;
import ca.rbon.iostream.writer.WriterOf;

public interface EncodingWriterPick<T> extends StraightWriterPick<T> {
    
    WriterOf<T> writer(Charset charset) throws IOException;
    
    default WriterOf<T> writer(String charsetName) throws IOException {
        return writer(Charset.forName(charsetName));
    }
    
    // BUFFERED
    
    BufferedWriterOf<T> bufferedWriter(Charset charset, int bufferSize) throws IOException;
    
    default BufferedWriterOf<T> bufferedWriter(Charset charset) throws IOException {
        return bufferedWriter(Encoding.DEFAULT_CHARSET, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    default BufferedWriterOf<T> bufferedWriter(String charsetName) throws IOException {
        return bufferedWriter(Charset.forName(charsetName), Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    // PRINT
    
    PrintWriterOf<T> printWriter(Charset charset, int bufferSize) throws IOException;
    
    default PrintWriterOf<T> printWriter(Charset charset) throws IOException {
        return printWriter(charset, Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    default PrintWriterOf<T> printWriter(String charsetName) throws IOException {
        return printWriter(Charset.forName(charsetName), Buffering.DEFAULT_BUFFER_SIZE);
    }
    
    // PRINT AUTOFLUSH
    
    PrintWriterOf<T> printWriter(Charset charset, int bufferSize, boolean autoflush) throws IOException;
    
    default PrintWriterOf<T> printWriter(String charsetName, int bufferSize, boolean autoflush) throws IOException {
        return printWriter(Charset.forName(charsetName), bufferSize, autoflush);
    }
    
    default PrintWriterOf<T> printWriter(Charset charset, boolean autoflush) throws IOException {
        return printWriter(charset, Buffering.DEFAULT_BUFFER_SIZE, autoflush);
    }
    
    default PrintWriterOf<T> printWriter(String charsetName, boolean autoflush) throws IOException {
        return printWriter(charsetName, Buffering.DEFAULT_BUFFER_SIZE, autoflush);
    }
    
}
