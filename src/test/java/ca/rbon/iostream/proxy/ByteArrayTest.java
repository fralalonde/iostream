package ca.rbon.iostream.proxy;

import static ca.rbon.iostream.IoStream.bytes;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.wrap.BufferedInputOf;
import ca.rbon.iostream.wrap.BufferedOutputOf;
import ca.rbon.iostream.wrap.BufferedWriterOf;
import ca.rbon.iostream.wrap.InputStreamOf;
import ca.rbon.iostream.wrap.OutputStreamOf;

public class ByteArrayTest {
    
    @Test
    public void emptyBuffer() throws IOException {
        BufferedWriterOf<byte[]> w = IoStream.bytes().bufferedWriter(4);
        try {
            w.append("eee");
            assertThat(bytes(w.getResource()).reader().read()).isEqualTo(-1);
            w.append("eee");
            assertThat(bytes(w.getResource()).reader().read()).isEqualTo(-1);
            w.flush();
            assertThat(bytes(w.getResource()).reader().read()).isEqualTo(101);
        } finally {
            w.close();
        }
    }
    
    @Test
    public void appendBytes() throws IOException {
        try (OutputStreamOf<byte[]> bos = IoStream.bytes(new byte[]{4, 5, 6}).outputStream()) {
            bos.write(new byte[]{7, 8, 9});
            assertThat(bos.getResource()).isEqualTo(new byte[]{4, 5, 6, 7, 8, 9});
        }
    }
    
    @Test
    public void readBytes() throws IOException {
        InputStreamOf<byte[]> bis = IoStream.bytes(new byte[]{4, 5, 6}).inputStream();
        try {
            assertThat(IOUtils.readFully(bis, 3)).isEqualTo(new byte[]{4, 5, 6});
        } finally {
            bis.close();
        }
    }
    
    @Test
    public void appendBufferedBytes() throws IOException {
        BufferedOutputOf<byte[]> bos = IoStream.bytes(new byte[]{4, 5, 6}).bufferedOutputStream(4);
        try {
            bos.write(new byte[]{7, 8, 9});
            assertThat(bos.getResource()).isEqualTo(new byte[]{4, 5, 6});
            bos.flush();
            assertThat(bos.getResource()).isEqualTo(new byte[]{4, 5, 6, 7, 8, 9});
        } finally {
            bos.close();
        }
    }
    
    @Test
    public void readBufferedBytes() throws IOException {
        BufferedInputOf<byte[]> bis = IoStream.bytes(new byte[]{4, 5, 6}).bufferedInputStream(4);
        try {
            assertThat(IOUtils.readFully(bis, 3)).isEqualTo(new byte[]{4, 5, 6});
        } finally {
            bis.close();
        }
    }
    
}
