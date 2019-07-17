package ca.rbon.iostream.resource;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.channel.BytesBiChannel;
import ca.rbon.iostream.channel.BytesOutChannel;
import ca.rbon.iostream.wrap.InputStreamOf;
import ca.rbon.iostream.wrap.OutputStreamOf;

public class PipeTest {

    @Test
    public void pipeSelf() throws IOException, InterruptedException {
        var pipe = IoStream.pipe(5);

        final var output = pipe.outputStream();
        final var input = pipe.inputStream();
        final AtomicInteger ai = new AtomicInteger();

        Thread t = new Thread(() -> {
            try {
                int i;
                while ((i = input.read()) != -1) {
                    ai.set(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();

        output.write(58);
        t.join(50);

        output.close();

        Assertions.assertThat(ai.get()).isEqualTo(58);
    }

    @Test
    public void pipeResource() throws IOException, InterruptedException {
        var pipe = IoStream.pipe();

        final var output = pipe.outputStream();
        final var input = output.getInner().inputStream();
        final AtomicInteger ai = new AtomicInteger();

        Thread t = new Thread(() -> {
            try {
                int i;
                while ((i = input.read()) != -1) {
                    ai.set(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();

        output.write(58);
        t.join(50);

        output.close();

        Assertions.assertThat(ai.get()).isEqualTo(58);
    }

    @Test
    public void pipeOf() throws IOException, InterruptedException {
        var pipe = IoStream.pipe(5);
        final var pipeInput = pipe.inputStream();

        var pipe2 = IoStream.pipe(pipeInput);
        final var output = pipe2.outputStream();

        final AtomicInteger ai = new AtomicInteger();

        Thread t = new Thread(() -> {
            try {
                int i;
                while ((i = pipeInput.read()) != -1) {
                    ai.set(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();

        output.write(58);
        t.join(50);

        output.close();

        Assertions.assertThat(ai.get()).isEqualTo(58);
    }

    @Test
    public void pipeInput() throws IOException, InterruptedException {
        var pipe = IoStream.pipe(5);
        final var pipeInput = pipe.inputStream();

        var pipe2 = IoStream.pipe(pipeInput.getInner().getInputStream());
        final var output = pipe2.outputStream();

        final AtomicInteger ai = new AtomicInteger();

        Thread t = new Thread(() -> {
            try {
                int i;
                while ((i = pipeInput.read()) != -1) {
                    ai.set(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();

        output.write(58);
        t.join(50);

        output.close();

        Assertions.assertThat(ai.get()).isEqualTo(58);
    }

}
