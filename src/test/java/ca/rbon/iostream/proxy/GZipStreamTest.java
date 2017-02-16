package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IoStream;

public class GZipStreamTest {
    
    @Test
    public void gzipAndUngzipBytes() throws IOException {
        
        GZipOutputOf<byte[]> zos = IoStream.bytes().gzipOutputStream();
        try {
            zos.write("aaaaaaa".getBytes());
        } finally {
            zos.close();
        }
        
        try (GZIPInputStream zis = IoStream.bytes(zos.getResource()).gzipInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        }
    }
    
    @Test
    public void gzipAndUngzipBufferedBytes() throws IOException {
        
        GZipOutputOf<byte[]> zos = IoStream.bytes().gzipOutputStream(3);
        try {
            zos.write("aaaaaaa".getBytes());
            zos.finish();
        } finally {
            zos.close();
        }
        
        try (GZIPInputStream zis = IoStream.bytes(zos.getResource()).gzipInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        }
    }
    
}
