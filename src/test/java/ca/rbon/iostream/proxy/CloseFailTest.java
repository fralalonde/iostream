package ca.rbon.iostream.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ca.rbon.iostream.IoStream;
import wrap.BufferedInputOf;
import wrap.BufferedOutputOf;
import wrap.BufferedReaderOf;
import wrap.DataInputOf;
import wrap.DataOutputOf;
import wrap.ObjectInputOf;
import wrap.ObjectOutputOf;
import wrap.PrintWriterOf;

public class CloseFailTest {
    
    final InputStream input = Mockito.mock(InputStream.class);
    
    final OutputStream output = Mockito.mock(OutputStream.class);
    
    @Before
    public void init() throws IOException {
        Mockito.doThrow(IOException.class).when(input).close();
        Mockito.doThrow(IOException.class).when(output).close();
    }
    
    @Test(expected = IOException.class)
    public void inputStream() throws IOException {
        BufferedInputOf<InputStream> pis = IoStream.stream(input).bufferedInputStream();
        pis.close();
    }
    
    @Test(expected = IOException.class)
    public void outputStream() throws IOException {
        BufferedOutputOf<OutputStream> pis = IoStream.stream(output).bufferedOutputStream();
        pis.close();
    }
    
    @Test(expected = IOException.class)
    public void dataInput() throws IOException {
        DataInputOf<InputStream> pis = IoStream.stream(input).dataInputStream();
        pis.close();
    }
    
    @Test(expected = IOException.class)
    public void dataOutput() throws IOException {
        DataOutputOf<OutputStream> pis = IoStream.stream(output).dataOutputStream();
        pis.close();
    }
    
    /**
     * OBJECT INPUT does NOT close the input stream
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    // @Test(expected = IOException.class)
    public void objectInput() throws IOException, ClassNotFoundException {
        // input object streams are picky, preparing real object data for test
        ObjectOutputOf<byte[]> o = IoStream.bytes().objectOutputStream();
        o.writeObject(new Integer(4));
        o.close();
        InputStream objectInput = IoStream.bytes(o.getResource()).inputStream();
        
        ObjectInputOf<InputStream> pis = IoStream.stream(objectInput).objectInputStream();
        pis.close();
        assertThat(pis.readObject()).isEqualTo(new Integer(4));
    }
    
    @Test(expected = IOException.class)
    public void objectOutput() throws IOException {
        ObjectOutputOf<OutputStream> pis = IoStream.stream(output).objectOutputStream();
        pis.close();
    }
    
    @Test(expected = IOException.class)
    public void bufferedInput() throws IOException {
        BufferedInputOf<InputStream> pis = IoStream.stream(input).bufferedInputStream();
        pis.close();
        assertThat(pis.read()).isEqualTo(-1);
        Mockito.verify(input).read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());
    }
    
    @Test(expected = IOException.class)
    public void bufferedOutput() throws IOException {
        BufferedOutputOf<OutputStream> pis = IoStream.stream(output).bufferedOutputStream();
        pis.close();
    }
    
    @Test(expected = IOException.class)
    public void bufferedReaderInput() throws IOException {
        BufferedReaderOf<InputStream> pis = IoStream.stream(input).bufferedReader("UTF-16");
        pis.close();
    }
    
    /**
     * STREAM ENCODER does NOT close the output stream.
     * 
     * @throws IOException
     */
    // @Test(expected = IOException.class)
    public void printWriterOutput() throws IOException {
        PrintWriterOf<OutputStream> pis = IoStream.stream(output).printWriter("UTF-16");
        pis.close();
    }
    
}