package ca.rbon.iostream.resource;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ca.rbon.iostream.IO;

import static org.junit.Assert.assertEquals;

public class ConsoleResourceTest {

    @Test
    public void stdin() throws IOException {
        try (var stream = IO.stdin().bufferedInputStream()) {
            Assertions.assertThat(stream.getInner()).isSameAs(System.in);
        }
    }

    @Test
    public void stdout() throws IOException {
        try (var stream = IO.stdout().bufferedOutputStream()) {
            Assertions.assertThat(stream.getInner()).isSameAs(System.out);
        }
    }

    @Test
    public void classpath() throws IOException {
        try (var stream = IO.classpath("some/package/Resource.txt").bufferedReader("UTF-8")) {
            assertEquals(stream.readLine(), "IOSTREAM");
        }
    }

}
