package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;
import org.mockito.Mockito;

import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.IO;
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
        try (BufferedInputOf<InputStream> pis = IO.stream(input).bufferedInputStream()) {
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
        try (BufferedOutputOf<OutputStream> pis = IO.stream(output).bufferedOutputStream()) {
            pis.write(new byte[] { 1 });
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
        try (DataInputOf<InputStream> pis = IO.stream(input).dataInputStream()) {
            assertThat(pis.read()).isEqualTo(0);
            Mockito.verify(input).read();
        }
        Mockito.verify(input).close();
    }

    @Test
    public void dataOutput() throws IOException {
        try (DataOutputOf<OutputStream> pis = IO.stream(output).dataOutputStream()) {
            pis.write(new byte[] { 1 });
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(1));
        }
        Mockito.verify(output).close();
    }

    @Test
    public void objectInput() throws IOException, ClassNotFoundException {
        // input object streams are picky, preparing real object data for test
        ObjectOutputOf<byte[]> o = IO.bytes().objectOutputStream();
        o.writeObject(Integer.valueOf(4));
        o.close();

        InputStream i = IO.bytes(o.getInner()).inputStream();

        try (ObjectInputOf<InputStream> pis = IO.stream(i).objectInputStream()) {
            assertThat(pis.readObject()).isEqualTo(Integer.valueOf(4));
        }
        Mockito.verify(input, Mockito.never()).close();
    }

    @Test
    public void objectOutput() throws IOException {
        try (ObjectOutputOf<OutputStream> pis = IO.stream(output).objectOutputStream()) {
            pis.write(new byte[] { 1 });
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(1));
        }
        Mockito.verify(output).close();
    }

    @Test
    public void bufferedInput() throws IOException {
        try (BufferedInputOf<InputStream> pis = IO.stream(input).bufferedInputStream()) {
            assertThat(pis.read()).isEqualTo(-1);
            Mockito.verify(input).read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());
        }
        Mockito.verify(input).close();
    }

    @Test
    public void bufferedOutput() throws IOException {
        try (BufferedOutputOf<OutputStream> pis = IO.stream(output).bufferedOutputStream()) {
            pis.write(new byte[] { 1 });
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(1));
        }
        Mockito.verify(output).close();
    }

    @Test
    public void bufferedReaderInput() throws IOException {
        try (BufferedReaderOf<InputStream> pis = IO.stream(input).bufferedReader("UTF-16")) {
            Mockito.when(input.read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt())).thenReturn(2);
            assertThat(pis.read()).isEqualTo(0);
            Mockito.verify(input).read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());
        }
        Mockito.verify(input).close();
    }

    @Test
    public void printWriterOutput() throws IOException {
        try (PrintWriterOf<OutputStream> pis = IO.stream(output).printWriter("UTF-16")) {
            pis.write("JGJHGJH");
            Mockito.verify(output, never()).write(Mockito.any(byte[].class));
            pis.flush();
            Mockito.verify(output).write(Mockito.any(byte[].class), Mockito.eq(0), Mockito.eq(16));
        }
        Mockito.verify(output).close();
    }

}
