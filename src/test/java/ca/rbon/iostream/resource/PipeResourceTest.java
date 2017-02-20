package ca.rbon.iostream.resource;

import java.io.IOException;
import java.nio.CharBuffer;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.channel.BytesBiChannel;
import wrap.ReaderOf;
import wrap.WriterOf;

public class PipeResourceTest {
    
    @Test
    public void pipeOut() throws IOException, InterruptedException {
        BytesBiChannel<PipeResource> pipe = IoStream.pipe(5);
        
        ReaderOf<PipeResource> rea = pipe.reader();
        WriterOf<PipeResource> wri = pipe.writer();
        CharBuffer cb = CharBuffer.allocate(3);
        
        Thread t = new Thread(() -> {
            try {
                rea.read(cb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
        wri.write("AAA");
        t.join(500);
        
        cb.flip();
        Assertions.assertThat(cb.toString()).isEqualTo("AAA");
    }
    
}
