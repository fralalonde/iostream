package ca.rbon.iostream.stream;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IoStream;

public class ObjectStreamsTest {
    
    @Test
    public void objectStreams() throws IOException {
        
        ObjectOutputOf<byte[]> zos = IoStream.bytes().objectOutputStream();
        try {
            zos.writeBytes("AA");
            assertThat(zos.closer.getLinks().get(0)).isInstanceOf(ByteArrayOutputStream.class);
            assertThat(zos.closer.getLinks().size()).isEqualTo(1);
        } finally {
            zos.close();
        }
        
        byte[] objectBytes = zos.getResource();
        try (ObjectInputStream zis = IoStream.bytes(objectBytes).objectInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 2);
            assertThat(new String(bytes)).isEqualTo("AA");
        }
    }
    
    @Test
    public void objectAndUnobjectBufferedBytes() throws IOException {
        ObjectOutputOf<byte[]> zos = IoStream.bytes().objectOutputStream(3);
        try {
            zos.writeBytes("AA");
            assertThat(zos.closer.getLinks().get(0)).isInstanceOf(ByteArrayOutputStream.class);
            assertThat(zos.closer.getLinks().get(1)).isInstanceOf(BufferedOutputStream.class);
            assertThat(zos.closer.getLinks().size()).isEqualTo(2);
        } finally {
            zos.close();
        }
        
        try (ObjectInputStream zis = IoStream.bytes(zos.getResource()).objectInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 2);
            assertThat(new String(bytes)).isEqualTo("AA");
        }
    }
    
    
}
