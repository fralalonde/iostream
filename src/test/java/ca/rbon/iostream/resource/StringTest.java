package ca.rbon.iostream.resource;

import java.io.IOException;
import java.nio.CharBuffer;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.IO;
import ca.rbon.iostream.wrap.ReaderOf;
import ca.rbon.iostream.wrap.WriterOf;

public class StringTest {

    @Test(expected = CodeFlowError.class)
    public void nullString() throws IOException {
        new StringResource(null, 0).getInputStream();
    }

    @Test(expected = CodeFlowError.class)
    public void nullBuffer() {
        new StringResource(null, 0).getResource();
    }

    @Test
    public void create() throws IOException {
        try (WriterOf<String> w = IO.string().writer()) {
            w.append("eee");
            Assertions.assertThat(w.getInner()).isEqualTo("eee");
        }
    }

    @Test
    public void append() throws IOException {
        try (WriterOf<String> w = IO.string("AA").writer()) {
            w.append("eee");
            Assertions.assertThat(w.getInner()).isEqualTo("AAeee");
        }
    }

    @Test
    public void appendCapacity() throws IOException {
        try (WriterOf<String> w = IO.string("AA", 3).writer()) {
            w.append("eee");
            Assertions.assertThat(w.getInner()).isEqualTo("AAeee");
        }
    }

    @Test
    public void read() throws IOException {
        try (ReaderOf<String> r = IO.string("AA").reader()) {
            CharBuffer cb = CharBuffer.allocate(4);
            Assertions.assertThat(r.read(cb)).isEqualTo(2);
            cb.flip();
            Assertions.assertThat(cb.toString()).isEqualTo("AA");
        }
    }

}
