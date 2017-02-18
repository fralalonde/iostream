package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IoStream;
import wrap.ZipOutputOf;

public class ZipStreamTest {
    
    @Test
    public void zipAndUnzipBytes() throws IOException {
        
        ZipOutputOf<byte[]> zos = IoStream.bytes().zipOutputStream();
        try {
            zos.putNextEntry(new ZipEntry("a"));
            zos.write("aaaaaaa".getBytes());
        } finally {
            zos.close();
        }
        
        ZipInputStream zis = IoStream.bytes(zos.getResource()).zipInputStream();
        try {
            ZipEntry ze = zis.getNextEntry();
            assertThat(ze).isNotNull();
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        } finally {
            zis.close();
        }
    }
    
    @Test
    public void zipAndUnzipBufferedBytes() throws IOException {
        
        ZipOutputOf<byte[]> zos = IoStream.bytes().zipOutputStream(3);
        try {
            ZipEntry ze = new ZipEntry("a");
            zos.putNextEntry(ze);
            zos.write("aaaaaaa".getBytes());
            zos.finish();
        } finally {
            zos.close();
        }
        
        try (ZipInputStream zis = IoStream.bytes(zos.getResource()).zipInputStream()) {
            ZipEntry ze = zis.getNextEntry();
            assertThat(ze).isNotNull();
            assertThat(ze.getName()).isEqualTo("a");
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        }
    }
    
    @Test
    public void zipAndUnzipEncodedBytes() throws IOException {
        
        ZipOutputOf<byte[]> zos = IoStream.bytes().zipOutputStream("UTF-16");
        try {
            zos.putNextEntry(new ZipEntry("ééé"));
            zos.write("aaaaaaa".getBytes());
        } finally {
            zos.close();
        }
        
        ZipInputStream zis = IoStream.bytes(zos.getResource()).zipInputStream("UTF-16");
        try {
            ZipEntry ze = zis.getNextEntry();
            assertThat(ze.getName()).isEqualTo("ééé");
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        } finally {
            zis.close();
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void zipAndUnzipWrongEncodedBytes() throws IOException {
        
        ZipOutputOf<byte[]> zos = IoStream.bytes().zipOutputStream("UTF-16");
        try {
            zos.putNextEntry(new ZipEntry("ééé"));
            zos.write("aaaaaaa".getBytes());
        } finally {
            zos.close();
        }
        
        ZipInputStream zis = IoStream.bytes(zos.getResource()).zipInputStream("UTF-8");
        try {
            ZipEntry ze = zis.getNextEntry();
            assertThat(ze.getName()).isEqualTo("ééé");
        } finally {
            zis.close();
        }
    }
    
}
