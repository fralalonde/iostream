package ca.rbon.iostream;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Random;

import ca.rbon.iostream.wrap.DataOutputOf;
import ca.rbon.iostream.wrap.InputStreamOf;
import ca.rbon.iostream.wrap.OutputStreamOf;
import ca.rbon.iostream.wrap.PrintWriterOf;

@SuppressWarnings("unused")
public class Samples {
    
    public void smallSample() throws IOException {
        try (PrintWriterOf<File> myFile = IoStream.file("myfile.txt").printWriter()) {
            File myFileTxt = myFile.get();
            myFile.write("Hello from file " + myFileTxt.getName());
        }
    }
    
    public void extremeSample() throws IOException {
        try (PrintWriterOf<File> myFile = IoStream.file("myfile.txt").base64().gzip(55).printWriter("UTF-16", 256, true)) {
            File myFileTxt = myFile.get();
            myFile.write("Hello from file " + myFileTxt.getName());
        }
    }
    
    public void intStreamSum() throws IOException {
        try (InputStreamOf<File> pw = IoStream.file("number.bin").inputStream()) {
            pw.intStream().sum();
        }
    }
    
    public void randomIntStreamSum() throws IOException {
        try (InputStreamOf<Random> pw = IoStream.random().inputStream()) {
            // add 5 random numbers
            pw.intStream().limit(5).sum();
        }
    }
    
    public void smallSample2() throws IOException {
        try (DataOutputOf<File> dataFile = IoStream.tempFile().dataOutputStream()) {
            dataFile.write(42);
            try (BufferedWriter bufFile = IoStream.file(dataFile.get()).bufferedWriter()) {
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
        PrintWriterOf<byte[]> bytes = IoStream.bytes().printWriter();
        // ...
        bytes.close();
        byte[] myPrecious = bytes.get();
    }
    
    public void jdkResult() throws IOException {
        // JDK
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Writer writer = new PrintWriter(byteArrayOutputStream);
        // ...
        writer.close();
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
        String tmpFilename = tmpout.get().getAbsolutePath();
    }
    
    public void strings() throws IOException {
        IoStream.string("agaga gogo").bufferedReader();
        IoStream.string("agaga gogo").reader();
        String str = IoStream.string().bufferedWriter().get();
    }
    
    public void byteArrays() throws IOException {
        IoStream.bytes().outputStream();
        byte[] bytes = IoStream.bytes().dataOutputStream().get();
        IoStream.bytes(new byte[]{0, 1, 2}).objectInputStream();
    }
    
    public void sockets() throws IOException {
        IoStream.socket("gloogloo.com", 80).bufferedOutputStream();
        InputStream smtpHoneypot = IoStream.socket("localhost", 25).inputStream();
    }
    
    public void streams() throws IOException {
        InputStream providedInput = new ByteArrayInputStream(new byte[7]);
        IoStream.stream(providedInput).gzip().inputStream();
        
        OutputStream providedOutput = new ByteArrayOutputStream();
        IoStream.stream(providedOutput).printWriter(256);
    }
    
    public byte[] fluent() throws IOException {
        // create and combine both objects
        PrintWriterOf<byte[]> bytes = IoStream.bytes().printWriter();
        
        // write the string
        bytes.write("doodoo");
        
        // close the writer and the inner stream at once
        bytes.close();
        
        // extract result
        return bytes.get();
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
        PrintWriterOf<byte[]> bytes = IoStream.bytes().printWriter();
        bytes.write("doodoo");
        bytes.close();
        return bytes.get();
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
        try (PrintWriterOf<byte[]> bytes = IoStream.bytes().printWriter()) {
            bytes.write("doodoo");
            return bytes.get();
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
