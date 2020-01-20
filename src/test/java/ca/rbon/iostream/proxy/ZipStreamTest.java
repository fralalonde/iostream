package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IO;
import ca.rbon.iostream.wrap.ZipOutputOf;

public class ZipStreamTest {

    @Test
    public void zipAndUnzipBytes() throws IOException {

        ZipOutputOf<byte[]> zos = IO.bytes().zipOutputStream();
        try (zos) {
            zos.putNextEntry(new ZipEntry("a"));
            zos.write("aaaaaaa".getBytes());
        }

        try (ZipInputStream zis = IO.bytes(zos.getInner()).zipInputStream()) {
            ZipEntry ze = zis.getNextEntry();
            assertThat(ze).isNotNull();
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        }
    }

    @Test
    public void zipAndUnzipBufferedBytes() throws IOException {

        ZipOutputOf<byte[]> zos = IO.bytes().zipOutputStream(3);
        try (zos) {
            ZipEntry ze = new ZipEntry("a");
            zos.putNextEntry(ze);
            zos.write("aaaaaaa".getBytes());
            zos.finish();
        }

        try (ZipInputStream zis = IO.bytes(zos.getInner()).zipInputStream()) {
            ZipEntry ze = zis.getNextEntry();
            assertThat(ze).isNotNull();
            assertThat(ze.getName()).isEqualTo("a");
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        }
    }

    @Test
    public void zipAndUnzipEncodedBytes() throws IOException {

        ZipOutputOf<byte[]> zos = IO.bytes().zipOutputStream("UTF-16");
        try (zos) {
            zos.putNextEntry(new ZipEntry("ééé"));
            zos.write("aaaaaaa".getBytes());
        }

        try (ZipInputStream zis = IO.bytes(zos.getInner()).zipInputStream("UTF-16")) {
            ZipEntry ze = zis.getNextEntry();
            assertThat(ze.getName()).isEqualTo("ééé");
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void zipAndUnzipWrongEncodedBytes() throws IOException {

        ZipOutputOf<byte[]> zos = IO.bytes().zipOutputStream("UTF-16");
        try (zos) {
            zos.putNextEntry(new ZipEntry("ééé"));
            zos.write("aaaaaaa".getBytes());
        }

        try (ZipInputStream zis = IO.bytes(zos.getInner()).zipInputStream("UTF-8")) {
            ZipEntry ze = zis.getNextEntry();
            assertThat(ze.getName()).isEqualTo("ééé");
        }
    }

}
