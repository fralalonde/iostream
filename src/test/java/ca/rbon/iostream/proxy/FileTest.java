package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import ca.rbon.iostream.wrap.*;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IO;

public class FileTest {

    private static final String A_TXT = "target/A.txt";
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    @Test(expected = NullPointerException.class)
    public void nullFile() {
        IO.file((File) null);
    }

    @Test
    public void recreate() throws IOException {
        try (BufferedWriterOf<File> w = IO.file(A_TXT).bufferedWriter("ISO-8859-1")) {
            w.append('é');
        }

        try (BufferedWriterOf<File> w = IO.file(A_TXT).bufferedWriter("ISO-8859-1")) {
            w.append('é');
        }

        try (BufferedReaderOf<File> r = IO.file(A_TXT).bufferedReader("ISO-8859-1")) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('é');
        }
    }

    @Test
    public void append() throws IOException {
        try (BufferedWriterOf<File> w = IO.file(A_TXT).bufferedWriter("UTF-8")) {
            w.append('é');
        }

        try (BufferedWriterOf<File> w = IO.file(A_TXT, true).bufferedWriter("UTF-8")) {
            w.append('à');
        }

        try (BufferedReaderOf<File> r = IO.file(A_TXT).bufferedReader("UTF-8")) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(2);
            assertThat(cbuf[0]).isEqualTo('é');
            assertThat(cbuf[1]).isEqualTo('à');
        }
    }

    @Test
    public void defaultCharsetFileWriteRead() throws IOException {
        try (BufferedWriterOf<File> w = IO.file(A_TXT).bufferedWriter("UTF-8")) {
            w.append('é');
            assertThat(w.getInner().getName()).isEqualTo("A.txt");
        }

        try (BufferedReaderOf<File> r = IO.file(A_TXT).bufferedReader(UTF_8)) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('é');
            assertThat(r.getInner().getName()).isEqualTo("A.txt");
        }
    }

    @Test
    public void tmpFileWriteRead() throws IOException {
        PrintWriterOf<File> tmpFileOut = IO.tempFile().printWriter("UTF-8");
        try (tmpFileOut) {
            tmpFileOut.append("tmpé");
        }

        try (BufferedReaderOf<File> tmpFileIn = IO.file(tmpFileOut.getInner()).bufferedReader(UTF_8)) {
            CharBuffer sb = CharBuffer.allocate(5);
            assertThat(tmpFileIn.read(sb)).isEqualTo(4);
            sb.flip();
            assertThat(sb.toString()).isEqualTo("tmpé");
        }
    }

    @Test
    public void specificCharsetFileWriteRead() throws IOException {
        try (WriterOf<File> w = IO.file(A_TXT).writer("UTF-16")) {
            w.append('à');
            assertThat(w.getInner().getName()).isEqualTo("A.txt");
        }

        try (ReaderOf<File> r = IO.file(A_TXT).reader("UTF-16")) {
            char[] cbuf = new char[4];
            assertThat(r.read(cbuf)).isEqualTo(1);
            assertThat(cbuf[0]).isEqualTo('à');
            assertThat(r.getInner().getName()).isEqualTo("A.txt");
        }
    }

    protected void showIoUtilsCopy() throws IOException {
        try (Reader in = IO.file("A").reader(); Writer out = IO.file("B").writer()) {
            IOUtils.copy(in, out);
        }
    }

}
