package iostream;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import iostream.IoStream;

public class IoStreamTest {

    @Test
    public void test() throws IOException {
	IoStream.file("doum.txt").printWriter();
	IoStream.file("doum.txt").zipOutput().getSubject();
	IoStream.file("doum.txt").zipInput();
	IoStream.file("doum.txt").zipInput();
	IoStream.file("dam.txt").append(true).bufferedWriter();
	IoStream.file("doum.txt").bufferedInput();
	
	IoStream.tempFile().dataOutput();
	
	IoStream.string("agaga gogo").bufferedReader();	
	IoStream.string("agaga gogo").reader();
	
	IoStream.string().bufferedWriter().getSubject();
	
	IoStream.bytes().dataOutput();
	IoStream.bytes().output();
	
	
	IoStream.bytes(new byte[] {0, 1, 2}).dataOutput();
	
	IoStream.bytes(new byte[] {0, 1, 2}).objectInput();
	
	fail("Not yet implemented");
    }

}
