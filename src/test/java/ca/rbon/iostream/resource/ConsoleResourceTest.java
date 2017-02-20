package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.wrap.BufferedInputOf;
import ca.rbon.iostream.wrap.BufferedOutputOf;

public class ConsoleResourceTest {
    
    @Test
    public void stdin() throws IOException {
        try (BufferedInputOf<InputStream> stream = IoStream.stdin().bufferedInputStream()) {
            Assertions.assertThat(stream.get()).isSameAs(System.in);
        }
    }
    
    @Test
    public void stdout() throws IOException {
        try (BufferedOutputOf<OutputStream> stream = IoStream.stdout().bufferedOutputStream()) {
            Assertions.assertThat(stream.get()).isSameAs(System.out);
        }
    }
    
    @Test(expected = IOException.class)
    public void consoleOut() throws IOException {
        IoStream.console().printWriter();
    }
    
}
