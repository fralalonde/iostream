package ca.rbon.iostream;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

import ca.rbon.iostream.proxy.stream.DataOutputProxy;
import ca.rbon.iostream.proxy.stream.UnbufferedOutputProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;

@SuppressWarnings("unused")
public class Samples2 {
    
    public void smallSample() throws IOException {
        try (DataOutputProxy<File> dataFile = IoStream2.tempFile().dataOutputStream()) {
            dataFile.write(42);
            try (BufferedWriter bufFile = IoStream2.file(dataFile.getResource()).bufferedWriter()) {
                bufFile.append("is the answer");
            } 
        }
    }
    
    public void closing() throws IOException {
        // IOSTREAMS
        Writer writer = IoStream2.file("a.txt").printWriter();
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
        PrintWriterProxy<byte[]> writer = IoStream2.bytes().printWriter();
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
        UnbufferedOutputProxy<File> out = IoStream2.file("noooes.txt").outputStream();
        
        try (PrintWriter w = IoStream2.file("mouha.txt").printWriter()) {
            w.append("haha");
        }
        
        IoStream2.file("doum.zip").zipInputStream("UTF-8");
        IoStream2.file("dam.txt", true).bufferedWriter();
        
        DataOutputProxy<File> tmpout = IoStream2.tempFile().dataOutputStream();
        tmpout.write(42);
        String tmpFilename = tmpout.getResource().getAbsolutePath();
    }
    
    public void strings() throws IOException {
        IoStream2.string("agaga gogo").bufferedReader();
        IoStream2.string("agaga gogo").reader();
        
        String str = IoStream2.string().bufferedWriter().getResource();
    }
    
    public void byteArrays() throws IOException {
        
        IoStream2.bytes().outputStream();
        byte[] bytes = IoStream2.bytes().dataOutputStream().getResource();
        
        IoStream2.bytes(new byte[]{0, 1, 2}).objectInputStream();
    }
    
    public void sockets() throws IOException {
        IoStream2.socket("gloogloo.com", 80).bufferedOutputStream();
        InputStream smtpHoneypot = IoStream2.socket("localhost", 25).inputStream();
    }
    
    public byte[] fluent() throws IOException {
        // create and combine both objects
        PrintWriterProxy<byte[]> writer = IoStream2.bytes().printWriter();
        
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
        PrintWriterProxy<byte[]> writer = IoStream2.bytes().printWriter();
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
        try (PrintWriterProxy<byte[]> writer = IoStream2.bytes().printWriter()) {
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
