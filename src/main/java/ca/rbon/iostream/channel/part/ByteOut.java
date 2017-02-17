package ca.rbon.iostream.channel.part;

import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.proxy.BufferedOutputOf;
import ca.rbon.iostream.proxy.BufferedWriterOf;
import ca.rbon.iostream.proxy.DataOutputOf;
import ca.rbon.iostream.proxy.ObjectOutputOf;
import ca.rbon.iostream.proxy.OutputStreamOf;
import ca.rbon.iostream.proxy.PrintWriterOf;
import ca.rbon.iostream.proxy.WriterOf;
import ca.rbon.iostream.proxy.ZipOutputOf;
import ca.rbon.iostream.resource.Resource;

public interface ByteOut<T> {
    
    OutputStreamOf<T> outputStream() throws IOException;
    
    BufferedOutputOf<T> bufferedOutputStream(int bufferSize) throws IOException;
    
    default BufferedOutputOf<T> bufferedOutputStream() throws IOException {
        return bufferedOutputStream(Resource.DEFAULT_BUFFER_SIZE);
    }
    
    ZipOutputOf<T> zipOutputStream(Charset charset, int bufferSize) throws IOException;
    
    default ZipOutputOf<T> zipOutputStream(String charsetName, int bufferSize) throws IOException {
        return zipOutputStream(Charset.forName(charsetName), bufferSize);
    }
    
    default ZipOutputOf<T> zipOutputStream(Charset charset) throws IOException {
        return zipOutputStream(charset, Resource.UNBUFFERED);
    }
    
    default ZipOutputOf<T> zipOutputStream(String charsetName) throws IOException {
        return zipOutputStream(charsetName, Resource.UNBUFFERED);
    }
    
    default ZipOutputOf<T> zipOutputStream(int bufferSize) throws IOException {
        return zipOutputStream(Resource.DEFAULT_CHARSET, bufferSize);
    }
    
    default ZipOutputOf<T> zipOutputStream() throws IOException {
        return zipOutputStream(Resource.DEFAULT_CHARSET, Resource.UNBUFFERED);
    }
    
    DataOutputOf<T> dataOutputStream(int bufferSize) throws IOException;
    
    default DataOutputOf<T> dataOutputStream() throws IOException {
        return dataOutputStream(Resource.UNBUFFERED);
    }
    
    ObjectOutputOf<T> objectOutputStream(int bufferSize) throws IOException;
    
    default ObjectOutputOf<T> objectOutputStream() throws IOException {
        return objectOutputStream(Resource.UNBUFFERED);
        
    }
    
    WriterOf<T> writer(Charset charset) throws IOException;
    
    default WriterOf<T> writer(String charsetName) throws IOException {
        return writer(Charset.forName(charsetName));
    }
    
    // BUFFERED
    
    BufferedWriterOf<T> bufferedWriter(Charset charset, int bufferSize) throws IOException;
    
    default BufferedWriterOf<T> bufferedWriter(Charset charset) throws IOException {
        return bufferedWriter(Resource.DEFAULT_CHARSET, Resource.DEFAULT_BUFFER_SIZE);
    }
    
    default BufferedWriterOf<T> bufferedWriter(String charsetName) throws IOException {
        return bufferedWriter(Charset.forName(charsetName), Resource.DEFAULT_BUFFER_SIZE);
    }
    
    // PRINT
    
    PrintWriterOf<T> printWriter(Charset charset, int bufferSize) throws IOException;
    
    default PrintWriterOf<T> printWriter(String charsetName, int bufferSize) throws IOException {
        return printWriter(Charset.forName(charsetName), bufferSize);
    }
    
    default PrintWriterOf<T> printWriter(Charset charset) throws IOException {
        return printWriter(charset, Resource.DEFAULT_BUFFER_SIZE);
    }
    
    default PrintWriterOf<T> printWriter(String charsetName) throws IOException {
        return printWriter(charsetName, Resource.DEFAULT_BUFFER_SIZE);
    }
    
    // PRINT AUTOFLUSH
    
    PrintWriterOf<T> printWriter(Charset charset, int bufferSize, boolean autoflush) throws IOException;
    
    default PrintWriterOf<T> printWriter(String charsetName, int bufferSize, boolean autoflush) throws IOException {
        return printWriter(Charset.forName(charsetName), bufferSize, autoflush);
    }
    
    default PrintWriterOf<T> printWriter(Charset charset, boolean autoflush) throws IOException {
        return printWriter(charset, Resource.DEFAULT_BUFFER_SIZE, autoflush);
    }
    
    default PrintWriterOf<T> printWriter(String charsetName, boolean autoflush) throws IOException {
        return printWriter(charsetName, Resource.DEFAULT_BUFFER_SIZE, autoflush);
    }
    
}
