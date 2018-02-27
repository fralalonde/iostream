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
        BytesBiChannel<Pipe> pipe = IoStream.pipe(5);
        
        final OutputStreamOf<Pipe> output = pipe.outputStream();
        final InputStreamOf<Pipe> input = pipe.inputStream();
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
        BytesBiChannel<Pipe> pipe = IoStream.pipe();
        
        final OutputStreamOf<Pipe> output = pipe.outputStream();
        final InputStreamOf<Pipe> input = output.getInner().inputStream();
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
        BytesBiChannel<Pipe> pipe = IoStream.pipe(5);
        final InputStreamOf<Pipe> pipeInput = pipe.inputStream();
        
        BytesOutChannel<Pipe> pipe2 = IoStream.pipe(pipeInput);
        final OutputStreamOf<Pipe> output = pipe2.outputStream();
        
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
        BytesBiChannel<Pipe> pipe = IoStream.pipe(5);
        final InputStreamOf<Pipe> pipeInput = pipe.inputStream();
        
        BytesOutChannel<Pipe> pipe2 = IoStream.pipe(pipeInput.getInner().getInputStream());
        final OutputStreamOf<Pipe> output = pipe2.outputStream();
        
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
