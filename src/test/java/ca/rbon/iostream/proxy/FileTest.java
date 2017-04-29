package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import ca.rbon.iostream.wrap.*;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IoStream;

public class FileTest {
    
    private static final String A_TXT = "target/A.txt";
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    @Test(expected = NullPointerException.class)
    public void nullFile() throws IOException {
        IoStream.file((File) null);
    }
    
    @Test
    public void recreate() throws IOException {
        try (BufferedWriterOf<File> w = IoStream.file(A_TXT).bufferedWriter("ISO-8859-1")) {
            w.append('é');
        }
        
        try (BufferedWriterOf<File> w = IoStream.file(A_TXT).bufferedWriter("ISO-8859-1")) {
            w.append('é');
        }
        
        try (BufferedReaderOf<File> r = IoStream.file(A_TXT).bufferedReader("ISO-8859-1")) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('é');
        }
    }
    
    @Test
    public void append() throws IOException {
        try (BufferedWriterOf<File> w = IoStream.file(A_TXT).bufferedWriter("UTF-8")) {
            w.append('é');
        }
        
        try (BufferedWriterOf<File> w = IoStream.file(A_TXT, true).bufferedWriter("UTF-8")) {
            w.append('à');
        }
        
        try (BufferedReaderOf<File> r = IoStream.file(A_TXT).bufferedReader("UTF-8")) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(2);
            assertThat(cbuf[0]).isEqualTo('é');
            assertThat(cbuf[1]).isEqualTo('à');
        }
    }
    
    @Test
    public void defaultCharsetFileWriteRead() throws IOException {
        try (BufferedWriterOf<File> w = IoStream.file(A_TXT).bufferedWriter("UTF-8")) {
            w.append('é');
            assertThat(w.get().getName()).isEqualTo("A.txt");
        }
        
        try (BufferedReaderOf<File> r = IoStream.file(A_TXT).bufferedReader(UTF_8)) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('é');
            assertThat(r.get().getName()).isEqualTo("A.txt");
        }
    }
    
    @Test
    public void tmpFileWriteRead() throws IOException {
        PrintWriterOf<File> tmpFileOut = IoStream.tempFile().printWriter("UTF-8");
        try {
            tmpFileOut.append("tmpé");
        } finally {
            tmpFileOut.close();
        }
        
        try (BufferedReaderOf<File> tmpFileIn = IoStream.file(tmpFileOut.get()).bufferedReader(UTF_8)) {
            CharBuffer sb = CharBuffer.allocate(5);
            assertThat(tmpFileIn.read(sb)).isEqualTo(4);
            sb.flip();
            assertThat(sb.toString()).isEqualTo("tmpé");
        }
    }
    
    @Test
    public void specificCharsetFileWriteRead() throws IOException {
        try (WriterOf<File> w = IoStream.file(A_TXT).writer("UTF-16")) {
            w.append('à');
            assertThat(w.get().getName()).isEqualTo("A.txt");
        }
        
        try (ReaderOf<File> r = IoStream.file(A_TXT).reader("UTF-16")) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('à');
            assertThat(r.get().getName()).isEqualTo("A.txt");
        }
    }

    protected void showIoUtilsCopy() throws IOException {
        try (Reader in = IoStream.file("A").reader(); Writer out = IoStream.file("B").writer()) {
            IOUtils.copy(in, out);
        }
    }

}
