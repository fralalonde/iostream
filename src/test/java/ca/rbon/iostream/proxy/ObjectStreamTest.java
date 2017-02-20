package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.wrap.ObjectOutputOf;

public class ObjectStreamTest {
    
    @Test
    public void objectStreams() throws IOException {
        
        ObjectOutputOf<byte[]> zos = IoStream.bytes().objectOutputStream();
        try {
            zos.writeBytes("AA");
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
        } finally {
            zos.close();
        }
        
        try (ObjectInputStream zis = IoStream.bytes(zos.getResource()).objectInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 2);
            assertThat(new String(bytes)).isEqualTo("AA");
        }
    }
    
}
