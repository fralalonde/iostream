package ca.rbon.iostream;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

import ca.rbon.iostream.proxy.DataOutputOf;
import ca.rbon.iostream.proxy.OutputStreamOf;
import ca.rbon.iostream.proxy.PrintWriterOf;

@SuppressWarnings("unused")
public class Samples {
    
    public void smallSample() throws IOException {
        try (PrintWriterOf<File> pw = IoStream.file("myfile.txt").printWriter("UTF-16")) {
            File myFileTxt = pw.getResource();
            pw.write("Hello from file " + myFileTxt.getName());
        }        
    }
    
    public void smallSample2() throws IOException {
        try (DataOutputOf<File> dataFile = IoStream.tempFile().dataOutputStream()) {
            dataFile.write(42);
            try (BufferedWriter bufFile = IoStream.file(dataFile.getResource()).bufferedWriter()) {
                bufFile.append("is the answer");
            } 
        }
    }
    
    public void closing() throws IOException {
        // IOSTREAMS
        Writer writer = IoStream.file("a.txt").printWriter();
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
        PrintWriterOf<byte[]> writer = IoStream.bytes().printWriter();
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
        OutputStreamOf<File> out = IoStream.file("noooes.txt").outputStream();
        
        try (PrintWriter w = IoStream.file("mouha.txt").printWriter()) {
            w.append("haha");
        }
        
        IoStream.file("doum.zip").zipInputStream("UTF-8");
        IoStream.file("dam.txt", true).bufferedWriter();
        
        DataOutputOf<File> tmpout = IoStream.tempFile().dataOutputStream();
        tmpout.write(42);
        String tmpFilename = tmpout.getResource().getAbsolutePath();
    }
    
    public void strings() throws IOException {
        IoStream.string("agaga gogo").bufferedReader();
        IoStream.string("agaga gogo").reader();
        
        String str = IoStream.string().bufferedWriter().getResource();
    }
    
    public void byteArrays() throws IOException {
        
        IoStream.bytes().outputStream();
        byte[] bytes = IoStream.bytes().dataOutputStream().getResource();
        
        IoStream.bytes(new byte[]{0, 1, 2}).objectInputStream();
    }
    
    public void sockets() throws IOException {
        IoStream.socket("gloogloo.com", 80).bufferedOutputStream();
        InputStream smtpHoneypot = IoStream.socket("localhost", 25).inputStream();
    }
    
    public byte[] fluent() throws IOException {
        // create and combine both objects
        PrintWriterOf<byte[]> writer = IoStream.bytes().printWriter();
        
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
        PrintWriterOf<byte[]> writer = IoStream.bytes().printWriter();
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
        try (PrintWriterOf<byte[]> writer = IoStream.bytes().printWriter()) {
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
