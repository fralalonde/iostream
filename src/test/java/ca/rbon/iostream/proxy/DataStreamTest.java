package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IoStream;

public class DataStreamTest {
    
    @Test
    public void dataStreams() throws IOException {
        
        DataOutputOf<byte[]> zos = IoStream.bytes().dataOutputStream();
        try {
            zos.writeBytes("AA");
        } finally {
            zos.close();
        }
        
        byte[] dataBytes = zos.getResource();
        try (DataInputStream zis = IoStream.bytes(dataBytes).dataInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 2);
            assertThat(new String(bytes)).isEqualTo("AA");
        }
    }
    
    @Test
    public void dataAndUndataBufferedBytes() throws IOException {
        DataOutputOf<byte[]> zos = IoStream.bytes().dataOutputStream(3);
        try {
            zos.writeBytes("AA");
        } finally {
            zos.close();
        }
        
        try (DataInputStream zis = IoStream.bytes(zos.getResource()).dataInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 2);
            assertThat(new String(bytes)).isEqualTo("AA");
        }
    }
    
}
