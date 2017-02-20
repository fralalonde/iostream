package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;
import org.mockito.Mockito;

import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.resource.InputStreamResource;
import ca.rbon.iostream.resource.OutputStreamResource;
import ca.rbon.iostream.wrap.BufferedInputOf;
import ca.rbon.iostream.wrap.BufferedOutputOf;
import ca.rbon.iostream.wrap.BufferedReaderOf;
import ca.rbon.iostream.wrap.DataInputOf;
import ca.rbon.iostream.wrap.DataOutputOf;
import ca.rbon.iostream.wrap.ObjectInputOf;
import ca.rbon.iostream.wrap.ObjectOutputOf;
import ca.rbon.iostream.wrap.PrintWriterOf;

public class InOutStreamTest {
    
    final InputStream input = Mockito.mock(InputStream.class);
    
    final OutputStream output = Mockito.mock(OutputStream.class);
    
    @Test
    public void inputStream() throws IOException {
        try (BufferedInputOf<InputStream> pis = IoStream.stream(input).bufferedInputStream()) {
            assertThat(pis.read()).isEqualTo(-1);
            Mockito.verify(input).read(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(8192));
        }
        Mockito.verify(input).close();
    }
    
    @Test(expected = CodeFlowError.class)
    public void badInputStream() throws IOException {
        new InputStreamResource(Mockito.mock(InputStream.class)).writer();
    }
    
    @Test(expected = NullPointerException.class)
    public void nullInputStream() throws IOException {
        new InputStreamResource(null).writer();
    }
    
    @Test
    public void outputStream() throws IOException {
        try (BufferedOutputOf<OutputStream> pis = IoStream.stream(output).bufferedOutputStream()) {
            pis.write(new byte[]{1});
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(1));
        }
        Mockito.verify(output).close();
    }
    
    @Test(expected = CodeFlowError.class)
    public void badOutputStream() throws IOException {
        new OutputStreamResource(Mockito.mock(OutputStream.class)).reader();
    }
    
    @Test(expected = NullPointerException.class)
    public void nullOutputStream() throws IOException {
        new OutputStreamResource(null).writer();
    }
    
    @Test
    public void dataInput() throws IOException {
        try (DataInputOf<InputStream> pis = IoStream.stream(input).dataInputStream()) {
            assertThat(pis.read()).isEqualTo(0);
            Mockito.verify(input).read();
        }
        Mockito.verify(input).close();
    }
    
    @Test
    public void dataOutput() throws IOException {
        try (DataOutputOf<OutputStream> pis = IoStream.stream(output).dataOutputStream()) {
            pis.write(new byte[]{1});
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(1));
        }
        Mockito.verify(output).close();
    }
    
    @Test
    public void objectInput() throws IOException, ClassNotFoundException {
        // input object streams are picky, preparing real object data for test
        ObjectOutputOf<byte[]> o = IoStream.bytes().objectOutputStream();
        o.writeObject(new Integer(4));
        o.close();
        
        InputStream i = IoStream.bytes(o.getResource()).inputStream();
        
        try (ObjectInputOf<InputStream> pis = IoStream.stream(i).objectInputStream()) {
            assertThat(pis.readObject()).isEqualTo(new Integer(4));
        }
        Mockito.verify(input, Mockito.never()).close();
    }
    
    @Test
    public void objectOutput() throws IOException {
        try (ObjectOutputOf<OutputStream> pis = IoStream.stream(output).objectOutputStream()) {
            pis.write(new byte[]{1});
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(1));
        }
        Mockito.verify(output).close();
    }
    
    @Test
    public void bufferedInput() throws IOException {
        try (BufferedInputOf<InputStream> pis = IoStream.stream(input).bufferedInputStream()) {
            assertThat(pis.read()).isEqualTo(-1);
            Mockito.verify(input).read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());
        }
        Mockito.verify(input).close();
    }
    
    @Test
    public void bufferedOutput() throws IOException {
        try (BufferedOutputOf<OutputStream> pis = IoStream.stream(output).bufferedOutputStream()) {
            pis.write(new byte[]{1});
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(1));
        }
        Mockito.verify(output).close();
    }
    
    @Test
    public void bufferedReaderInput() throws IOException {
        try (BufferedReaderOf<InputStream> pis = IoStream.stream(input).bufferedReader("UTF-16")) {
            Mockito.when(input.read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt())).thenReturn(2);
            assertThat(pis.read()).isEqualTo(0);
            Mockito.verify(input).read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());
        }
        Mockito.verify(input).close();
    }
    
    @Test
    public void printWriterOutput() throws IOException {
        try (PrintWriterOf<OutputStream> pis = IoStream.stream(output).printWriter("UTF-16")) {
            pis.write("JGJHGJH");
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(16));
        }
        Mockito.verify(output).close();
    }
    
}
