package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;

import org.junit.Test;

import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.wrap.PrintWriterOf;
import ca.rbon.iostream.wrap.ReaderOf;
import ca.rbon.iostream.wrap.WriterOf;

public class FileTest {
    
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
            assertThat(w.getResource().getName()).isEqualTo("A.txt");
        }
        
        try (ReaderOf<File> r = IoStream.file(A_TXT).reader()) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('é');
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
            assertThat(w.getResource().getName()).isEqualTo("A.txt");
        }
        
        try (ReaderOf<File> r = IoStream.file(A_TXT).reader("UTF-16")) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('à');
            assertThat(r.getResource().getName()).isEqualTo("A.txt");
        }
    }
    
}
