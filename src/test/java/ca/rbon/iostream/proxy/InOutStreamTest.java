package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;
import org.mockito.Mockito;

import ca.rbon.iostream.IoStream;

public class InOutStreamTest {
    
    final InputStream input = Mockito.mock(InputStream.class);
    
    final OutputStream output = Mockito.mock(OutputStream.class);
    
    @Test
    public void inputStream() throws IOException {        
        try (BufferedInputOf<InputStream> pis = IoStream.stream(input).bufferedInputStream()) {
            assertThat(pis.closer.getLinks().size()).isEqualTo(0);
            assertThat(pis.read()).isEqualTo(-1);
            Mockito.verify(input).read(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(8192));
        }
        Mockito.verify(input).close();
    }

    @Test
    public void dataInput() throws IOException {        
        try (DataInputOf<InputStream> pis = IoStream.stream(input).dataInputStream()) {
            assertThat(pis.closer.getLinks().size()).isEqualTo(0);
            assertThat(pis.read()).isEqualTo(0);
            Mockito.verify(input).read();
        }
        Mockito.verify(input).close();
    }
    
    @Test
    public void outputStream() throws IOException {        
        try (BufferedOutputOf<OutputStream> pis = IoStream.stream(output).bufferedOutputStream()) {
            assertThat(pis.closer.getLinks().size()).isEqualTo(0);
            pis.write(new byte[] {1});
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(1));
        }
        Mockito.verify(output).close();
    }
    
    
    @Test
    public void dataOutput() throws IOException {        
        try (DataOutputOf<OutputStream> pis = IoStream.stream(output).dataOutputStream()) {
            assertThat(pis.closer.getLinks().size()).isEqualTo(0);
            pis.write(new byte[] {1});
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(1));
        }
        Mockito.verify(output).close();
    }
    
}
