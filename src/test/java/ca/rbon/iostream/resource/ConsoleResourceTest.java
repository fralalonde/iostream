package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ca.rbon.iostream.IoStream;
import wrap.BufferedInputOf;
import wrap.BufferedOutputOf;

public class ConsoleResourceTest {
    
    @Test
    public void consoleIn() throws IOException {
        try (BufferedInputOf<InputStream> stream = IoStream.stdin().bufferedInputStream()) {
            Assertions.assertThat(stream.getResource()).isSameAs(System.in);
        }
    }
    
    @Test
    public void consoleOut() throws IOException {
        try (BufferedOutputOf<OutputStream> stream = IoStream.stdout().bufferedOutputStream()) {
            Assertions.assertThat(stream.getResource()).isSameAs(System.out);
        }
    }
    
}
