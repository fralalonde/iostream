package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.wrap.InputStreamOf;
import ca.rbon.iostream.wrap.OutputStreamOf;

public class GZipStreamTest {

    @Test
    public void gzipAndUnzipBytes() throws IOException {

        OutputStreamOf<byte[]> zos = IoStream.bytes().gzip().outputStream();
        try {
            zos.write("aaaaaaa".getBytes());
        } finally {
            zos.close();
        }

        try (InputStreamOf<byte[]> zis = IoStream.bytes(zos.getInner()).gzip().inputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        }
    }

    @Test
    public void gzipAndUnzipBufferedBytes() throws IOException {
        OutputStreamOf<byte[]> zos = IoStream.bytes().gzip(3).outputStream();
        try {
            zos.write("aaaaaaa".getBytes());
        } finally {
            zos.close();
        }

        try (InputStream zis = IoStream.bytes(zos.getInner()).gzip(3).inputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        }
    }

}
