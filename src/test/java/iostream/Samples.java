package iostream;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

import iostream.proxy.stream.DataOutputProxy;
import iostream.proxy.writer.PrintWriterProxy;

public class Samples {

    public void smallSample() throws IOException {
	try (DataOutputProxy<File> dataFile = IoStreams.tempFile().dataOutputStream()) {
	    dataFile.write(42);
	    try (BufferedWriter bufFile = IoStreams.file(dataFile.getResource()).bufferedWriter()) {
		bufFile.append("is the answer");
	    }
	    ;
	}
    }

    public void closing() throws IOException {
	// IOSTREAMS
	Writer writer = IoStreams.file("a.txt").printWriter();
	// ...
	writer.close();

	// JDK
	Writer writer1 = new FileWriter("a.txt");
	Writer writer2 = new BufferedWriter(writer1);
	// ...
	writer2.close();
	writer1.close();
    }

    public void iostreamResult() throws IOException {
	// IOSTREAMS
	PrintWriterProxy<byte[]> writer = IoStreams.bytes().printWriter();
	// ...
	writer.close();
	byte[] myPrecious = writer.getResource();
    }

    public void jdkResult() throws IOException {
	// JDK
	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	Writer writer = new PrintWriter(byteArrayOutputStream);
	// ...
	writer.close();
	byteArrayOutputStream.close();
	byte[] myPrecious = byteArrayOutputStream.toByteArray();
    }

    public void files() throws IOException {
	try (PrintWriter w = IoStreams.file("mouha.txt").printWriter()) {
	    w.append("haha");
	}
	

	IoStreams.file("doum.zip").zipInputStream();
	IoStreams.file("dam.txt", true).bufferedWriter();

	DataOutputProxy<File> tmpout = IoStreams.tempFile().dataOutputStream();
	tmpout.write(42);
	String tmpFilename = tmpout.getResource().getAbsolutePath();
    }
    
    public void strings() throws IOException {
	IoStreams.string("agaga gogo").bufferedReader();
	IoStreams.string("agaga gogo").reader();
	
	String str = IoStreams.string().bufferedWriter().getResource();
    }
    
    public void byteArrays() throws IOException {
	
	IoStreams.bytes().outputStream();
	byte[] bytes = IoStreams.bytes().dataOutputStream().getResource();
	
	IoStreams.bytes(new byte[] { 0, 1, 2 }).objectInputStream();
    }
    
    public void sockets() throws IOException {
	IoStreams.socket("gloogloo.com", 80).bufferedOutputStream();
	InputStream smtpHoneypot = IoStreams.socket("localhost", 25).inputStream();	
    }

    public byte[] fluent() throws IOException {
	// create and combine both objects
	PrintWriterProxy<byte[]> writer = IoStreams.bytes().printWriter();

	// write the string
	writer.write("doodoo");

	// close the writer and the inner stream at once
	writer.close();

	// extract result
	return writer.getResource();
    }

    public byte[] classic() throws IOException {
	// explicitly create inner stream to extract result
	ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

	// create outer writer
	PrintWriter writer = new PrintWriter(byteOutputStream);

	// write a string
	writer.write("doodoo");

	// close the writer
	writer.close();

	// dont forget to close inner stream too
	byteOutputStream.close();

	// extract result from inner stream
	return byteOutputStream.toByteArray();
    }

    public byte[] fluent2() throws IOException {
	PrintWriterProxy<byte[]> writer = IoStreams.bytes().printWriter();
	writer.write("doodoo");
	writer.close();
	return writer.getResource();
    }

    public byte[] classic2() throws IOException {
	ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
	PrintWriter writer = new PrintWriter(byteOutputStream);
	writer.write("doodoo");
	writer.close();
	byteOutputStream.close();
	return byteOutputStream.toByteArray();
    }

    public byte[] fluent3() throws IOException {
	try (PrintWriterProxy<byte[]> writer = IoStreams.bytes().printWriter()) {
	    writer.write("doodoo");
	    return writer.getResource();
	}
    }

    public byte[] classic3() throws IOException {
	try (ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(byteOutputStream)) {
	    writer.write("doodoo");
	    return byteOutputStream.toByteArray();
	}
    }

}
