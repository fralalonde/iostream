package ca.rbon.iostream.channel.part;

import ca.rbon.iostream.wrap.BufferedOutputOf;
import ca.rbon.iostream.wrap.BufferedWriterOf;
import ca.rbon.iostream.wrap.DataOutputOf;
import ca.rbon.iostream.wrap.ObjectOutputOf;
import ca.rbon.iostream.wrap.OutputStreamOf;
import ca.rbon.iostream.wrap.PrintWriterOf;
import ca.rbon.iostream.wrap.WriterOf;
import ca.rbon.iostream.wrap.ZipOutputOf;

import java.io.IOException;
import java.nio.charset.Charset;

public interface ByteOut<T> {

    OutputStreamOf<T> outputStream() throws IOException;

    BufferedOutputOf<T> bufferedOutputStream(int bufferSize) throws IOException;

    BufferedOutputOf<T> bufferedOutputStream() throws IOException;

    ZipOutputOf<T> zipOutputStream(Charset charset, int bufferSize) throws IOException;

    ZipOutputOf<T> zipOutputStream(String charsetName, int bufferSize) throws IOException;

    ZipOutputOf<T> zipOutputStream(Charset charset) throws IOException;

    ZipOutputOf<T> zipOutputStream(String charsetName) throws IOException;

    ZipOutputOf<T> zipOutputStream(int bufferSize) throws IOException;

    ZipOutputOf<T> zipOutputStream() throws IOException;

    DataOutputOf<T> dataOutputStream(int bufferSize) throws IOException;

    DataOutputOf<T> dataOutputStream() throws IOException;

    ObjectOutputOf<T> objectOutputStream(int bufferSize) throws IOException;

    ObjectOutputOf<T> objectOutputStream() throws IOException;

    WriterOf<T> writer(Charset charset) throws IOException;

    WriterOf<T> writer(String charsetName) throws IOException;

    // BUFFERED

    BufferedWriterOf<T> bufferedWriter(Charset charset, int bufferSize) throws IOException;

    BufferedWriterOf<T> bufferedWriter(Charset charset) throws IOException;

    BufferedWriterOf<T> bufferedWriter(String charsetName) throws IOException;

    // PRINT

    PrintWriterOf<T> printWriter(Charset charset, int bufferSize) throws IOException;

    PrintWriterOf<T> printWriter(String charsetName, int bufferSize) throws IOException;

    PrintWriterOf<T> printWriter(Charset charset) throws IOException;

    PrintWriterOf<T> printWriter(String charsetName) throws IOException;

    // PRINT AUTOFLUSH

    PrintWriterOf<T> printWriter(Charset charset, int bufferSize, boolean autoflush) throws IOException;

    PrintWriterOf<T> printWriter(String charsetName, int bufferSize, boolean autoflush) throws IOException;

    PrintWriterOf<T> printWriter(Charset charset, boolean autoflush) throws IOException;

    PrintWriterOf<T> printWriter(String charsetName, boolean autoflush) throws IOException;

}
