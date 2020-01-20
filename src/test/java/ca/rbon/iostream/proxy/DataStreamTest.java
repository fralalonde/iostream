package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ca.rbon.iostream.IO;
import ca.rbon.iostream.wrap.DataOutputOf;

public class DataStreamTest {

    @Test
    public void dataStreams() throws IOException {

        DataOutputOf<byte[]> zos = IO.bytes().dataOutputStream();
        try (zos) {
            zos.writeBytes("AA");
        }

        byte[] dataBytes = zos.getInner();
        try (DataInputStream zis = IO.bytes(dataBytes).dataInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 2);
            assertThat(new String(bytes)).isEqualTo("AA");
        }
    }

    @Test
    public void dataAndUndataBufferedBytes() throws IOException {
        DataOutputOf<byte[]> zos = IO.bytes().dataOutputStream(3);
        try (zos) {
            zos.writeBytes("AA");
        }

        try (DataInputStream zis = IO.bytes(zos.getInner()).dataInputStream()) {
            byte[] bytes = IOUtils.readFully(zis, 2);
            assertThat(new String(bytes)).isEqualTo("AA");
        }
    }

}
