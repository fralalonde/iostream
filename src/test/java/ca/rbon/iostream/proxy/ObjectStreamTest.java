package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.ObjectInputStream;

import ca.rbon.iostream.utils.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IO;
import ca.rbon.iostream.wrap.ObjectOutputOf;

public class ObjectStreamTest {

    @Test
    public void objectStreams() throws IOException {

        ObjectOutputOf<byte[]> zos = IO.bytes().objectOutputStream();
        try (zos) {
            zos.writeBytes("AA");
        }

        byte[] objectBytes = zos.getInner();
        try (ObjectInputStream zis = IO.bytes(objectBytes).objectInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 2);
            assertThat(new String(bytes)).isEqualTo("AA");
        }
    }

    @Test
    public void objectAndUnobjectBufferedBytes() throws IOException {
        ObjectOutputOf<byte[]> zos = IO.bytes().objectOutputStream(3);
        try (zos) {
            zos.writeBytes("AA");
        }

        try (ObjectInputStream zis = IO.bytes(zos.getInner()).objectInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 2);
            assertThat(new String(bytes)).isEqualTo("AA");
        }
    }

}
