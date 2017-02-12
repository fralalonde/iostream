package ca.rbon.iostream.writer;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;

import org.junit.Test;

import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.writer.PrintWriterOf;
import ca.rbon.iostream.writer.ReaderOf;
import ca.rbon.iostream.writer.WriterOf;

public class FilePickerTest {
    
    private static final String A_TXT = "target/A.txt";
    
    @Test
    public void recreate() throws IOException {
        try (WriterOf<File> w = IoStream.file(A_TXT).writer()) {
            w.append('é');
        }
        
        try (WriterOf<File> w = IoStream.file(A_TXT).writer()) {
            w.append('é');
        }
        
        try (ReaderOf<File> r = IoStream.file(A_TXT).reader()) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('é');
        }
    }
    
    @Test
    public void append() throws IOException {
        try (WriterOf<File> w = IoStream.file(A_TXT).writer()) {
            w.append('é');
        }
        
        try (WriterOf<File> w = IoStream.file(A_TXT, true).writer()) {
            w.append('à');
        }
        
        try (ReaderOf<File> r = IoStream.file(A_TXT).reader()) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(2);
            assertThat(cbuf[0]).isEqualTo('é');
            assertThat(cbuf[1]).isEqualTo('à');
        }
    }
    
    @Test
    public void defaultCharsetFileWriteRead() throws IOException {
        try (WriterOf<File> w = IoStream.file(A_TXT).writer()) {
            w.append('é');
            assertThat(w.closer.getLinks().get(0)).isInstanceOf(FileOutputStream.class);
            assertThat(w.closer.getLinks().get(1)).isInstanceOf(OutputStreamWriter.class);
            assertThat(w.closer.getLinks().size()).isEqualTo(2);
            assertThat(((OutputStreamWriter) w.closer.getLinks().get(1)).getEncoding()).isEqualTo("UTF8");
            assertThat(w.getResource().getName()).isEqualTo("A.txt");
        }
        
        try (ReaderOf<File> r = IoStream.file(A_TXT).reader()) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('é');
            assertThat(r.closer.getLinks().get(0)).isInstanceOf(FileInputStream.class);
            assertThat(r.closer.getLinks().get(1)).isInstanceOf(InputStreamReader.class);
            assertThat(r.closer.getLinks().size()).isEqualTo(2);
            assertThat(((InputStreamReader) r.closer.getLinks().get(1)).getEncoding()).isEqualTo("UTF8");
            assertThat(r.getResource().getName()).isEqualTo("A.txt");
        }
    }
    
    @Test
    public void tmpFileWriteRead() throws IOException {
        PrintWriterOf<File> w = IoStream.tempFile().printWriter();
        try {
            w.append("tmpé");
        } finally {
            w.close();
        }
        
        try (ReaderOf<File> r = IoStream.file(w.getResource()).reader()) {
            CharBuffer sb = CharBuffer.allocate(5);
            assertThat(r.read(sb)).isEqualTo(4);
            sb.flip();
            assertThat(sb.toString()).isEqualTo("tmpé");
        }
    }
    
    @Test
    public void specificCharsetFileWriteRead() throws IOException {
        try (WriterOf<File> w = IoStream.file(A_TXT).writer("UTF-16")) {
            w.append('à');
            assertThat(w.closer.getLinks().get(0)).isInstanceOf(FileOutputStream.class);
            assertThat(w.closer.getLinks().get(1)).isInstanceOf(OutputStreamWriter.class);
            assertThat(w.closer.getLinks().size()).isEqualTo(2);
            assertThat(((OutputStreamWriter) w.closer.getLinks().get(1)).getEncoding()).isEqualTo("UTF-16");
            assertThat(w.getResource().getName()).isEqualTo("A.txt");
        }
        
        try (ReaderOf<File> r = IoStream.file(A_TXT).reader("UTF-16")) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('à');
            assertThat(r.closer.getLinks().get(0)).isInstanceOf(FileInputStream.class);
            assertThat(r.closer.getLinks().get(1)).isInstanceOf(InputStreamReader.class);
            assertThat(r.closer.getLinks().size()).isEqualTo(2);
            assertThat(((InputStreamReader) r.closer.getLinks().get(1)).getEncoding()).isEqualTo("UTF-16");
            assertThat(r.getResource().getName()).isEqualTo("A.txt");
        }
    }
    
}
