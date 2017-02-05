package iostream;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import iostream.IoStream;

public class IoStreamTest {

    @Test
    public void test() throws IOException {
	IoStream.file("doum.txt").printWriter();
	IoStream.file("doum.txt").zipOutput();
	IoStream.file("doum.txt").zipInput();
	IoStream.file("dam.txt").append(true).bufferedWriter();
	IoStream.file("doum.txt").bufferedInput();
	
	IoStream.tempFile().dataOutput();
	
	IoStream.string("agaga gogo").bufferedReader();
	
	IoStream.string().bufferedWriter();
	
	IoStream.bytes().dataOutput();
	
	fail("Not yet implemented");
    }

}
