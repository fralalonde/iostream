package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import ca.rbon.iostream.IO;
import ca.rbon.iostream.wrap.InputStreamOf;

public class RandomInputStreamTest {

    @Test
    public void random() throws IOException {
        try (InputStreamOf<Random> pw = IO.random(1000).inputStream()) {
            byte[] bytes = new byte[4];
            pw.read(bytes, 0, 4);
            Assertions.assertThat(bytes).isEqualTo(new byte[] { -75, 63, -109, 117 });
        }
    }

    @Test
    public void intStream() throws IOException {
        try (InputStreamOf<byte[]> pw = IO.bytes(new byte[] { 1, 2, 3 }).inputStream()) {
            Assertions.assertThat(pw.intStream().sum()).isEqualTo(6);
        }
    }

    @Test(expected = IOException.class)
    public void intStreamThrow() throws IOException {
        InputStream mock = Mockito.mock(InputStream.class);
        Mockito.doThrow(IOException.class).when(mock).read();
        try (InputStreamOf<?> pw = IO.stream(mock).inputStream()) {
            Assertions.assertThat(pw.intStream().sum()).isEqualTo(6);
        }
    }

}
