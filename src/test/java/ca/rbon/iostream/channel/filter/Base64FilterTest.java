package ca.rbon.iostream.channel.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.wrap.InputStreamOf;
import ca.rbon.iostream.wrap.OutputStreamOf;

public class Base64FilterTest {
    
    @Test
    public void base64Bytes() throws IOException {
        
        OutputStreamOf<byte[]> zos = IoStream.bytes().base64().outputStream();
        try {
            zos.write("aaaaaaa".getBytes());
        } finally {
            zos.close();
        }
        
        assertThat(new String(zos.getInner())).isEqualTo("YWFhYWFhYQ==");
        
        try (InputStreamOf<byte[]> zis = IoStream.bytes(zos.getInner()).base64().inputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 7);
            assertThat(new String(bytes)).isEqualTo("aaaaaaa");
        }
    }
    
}
