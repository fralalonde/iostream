package ca.rbon.iostream.resource;

import static ca.rbon.iostream.IO.bytes;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.CodeFlowError;
import ca.rbon.iostream.IO;
import ca.rbon.iostream.wrap.BufferedInputOf;
import ca.rbon.iostream.wrap.BufferedOutputOf;
import ca.rbon.iostream.wrap.BufferedWriterOf;
import ca.rbon.iostream.wrap.InputStreamOf;
import ca.rbon.iostream.wrap.OutputStreamOf;
import ca.rbon.iostream.wrap.WriterOf;

public class ByteArrayTest {

    @Test(expected = CodeFlowError.class)
    public void nullBytes() throws IOException {
        ByteArrayResource r = new ByteArrayResource(null, 0);
        r.getInputStream();
    }

    @Test(expected = CodeFlowError.class)
    public void nullBuffer() {
        ByteArrayResource r = new ByteArrayResource(null, 0);
        r.getResource();
    }

    @Test
    public void emptyBuffer() throws IOException {
        try (BufferedWriterOf<byte[]> w = IO.bytes().bufferedWriter(4)) {
            w.append("eee");
            assertThat(bytes(w.getInner()).reader().read()).isEqualTo(-1);
            w.append("eee");
            assertThat(bytes(w.getInner()).reader().read()).isEqualTo(-1);
            w.flush();
            assertThat(bytes(w.getInner()).reader().read()).isEqualTo(101);
        }
    }

    @Test
    public void emptyBufferCapacity() throws IOException {
        WriterOf<byte[]> w = IO.bytes(5).writer();
        try (w) {
            w.append("eee");
        }
        assertThat(bytes(w.getInner()).reader().read()).isEqualTo(101);
    }

    @Test
    public void appendBytes() throws IOException {
        try (OutputStreamOf<byte[]> bos = IO.bytes(new byte[] { 4, 5, 6 }).outputStream()) {
            bos.write(new byte[] { 7, 8, 9 });
            assertThat(bos.getInner()).isEqualTo(new byte[] { 4, 5, 6, 7, 8, 9 });
        }
    }

    @Test
    public void appendBytesCapacity() throws IOException {
        try (OutputStreamOf<byte[]> bos = IO.bytes(new byte[] { 4, 5, 6 }, 4).outputStream()) {
            bos.write(new byte[] { 7, 8, 9 });
            assertThat(bos.getInner()).isEqualTo(new byte[] { 4, 5, 6, 7, 8, 9 });
        }
    }

    @Test
    public void readBytes() throws IOException {
        InputStreamOf<byte[]> bis = IO.bytes(new byte[] { 4, 5, 6 }).inputStream();
        try (bis) {
            assertThat(IOUtils.readFully(bis, 3)).isEqualTo(new byte[]{4, 5, 6});
        }
        assertThat(bis.getInner()).isEqualTo(new byte[] { 4, 5, 6 });
    }

    @Test
    public void appendBufferedBytes() throws IOException {
        try (BufferedOutputOf<byte[]> bos = IO.bytes(new byte[]{4, 5, 6}).bufferedOutputStream(4)) {
            bos.write(new byte[]{7, 8, 9});
            assertThat(bos.getInner()).isEqualTo(new byte[]{4, 5, 6});
            bos.flush();
            assertThat(bos.getInner()).isEqualTo(new byte[]{4, 5, 6, 7, 8, 9});
        }
    }

    @Test
    public void readBufferedBytes() throws IOException {
        try (BufferedInputOf<byte[]> bis = IO.bytes(new byte[]{4, 5, 6}).bufferedInputStream(4)) {
            assertThat(IOUtils.readFully(bis, 3)).isEqualTo(new byte[]{4, 5, 6});
        }
    }

}
