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

import ca.rbon.iostream.wrap.DataOutputOf;

@SuppressWarnings("unused")
public class Samples {

    public void smallSample() throws IOException {
        try (var myFile = IO.file("myfile.txt").printWriter()) {
            File myFileTxt = myFile.getInner();
            myFile.write("Hello from file " + myFileTxt.getName());
        }
    }

    public void extremeSample() throws IOException {
        try (var myFile = IO.file("myfile.txt").base64().gzip(55).printWriter("UTF-16", 256,
                true)) {
            File myFileTxt = myFile.getInner();
            myFile.write("Hello from file " + myFileTxt.getName());
        }
    }

    public void intStreamSum() throws IOException {
        try (var pw = IO.file("number.bin").inputStream()) {
            pw.intStream().sum();
        }
    }

    public void randomIntStreamSum() throws IOException {
        try (var pw = IO.random().inputStream()) {
            // add 5 random numbers
            pw.intStream().limit(5).sum();
        }
    }

    public void smallSample2() throws IOException {
        try (var dataFile = IO.tempFile().dataOutputStream()) {
            dataFile.write(42);
            try (BufferedWriter bufFile = IO.file(dataFile.getInner()).bufferedWriter()) {
                bufFile.append("is the answer");
            }
        }
    }

    public void closing() throws IOException {
        // IOSTREAMS
        Writer writer = IO.file("a.txt").printWriter();
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
        var bytes = IO.bytes().printWriter();
        // ...
        bytes.close();
        byte[] myPrecious = bytes.getInner();
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
        var out = IO.file("noooes.txt").outputStream();

        try (PrintWriter w = IO.file("mouha.txt").printWriter()) {
            w.append("haha");
        }

        IO.file("doum.zip").zipInputStream("UTF-8");
        IO.file("dam.txt", true).bufferedWriter();

        DataOutputOf<File> tmpout = IO.tempFile().dataOutputStream();
        tmpout.write(42);
        String tmpFilename = tmpout.getInner().getAbsolutePath();
    }

    public void strings() throws IOException {
        IO.string("agaga gogo").bufferedReader();
        IO.string("agaga gogo").reader();
        String str = IO.string().bufferedWriter().getInner();
    }

    public void byteArrays() throws IOException {
        IO.bytes().outputStream();
        byte[] bytes = IO.bytes().dataOutputStream().getInner();
        IO.bytes(new byte[] { 0, 1, 2 }).objectInputStream();
    }

    public void sockets() throws IOException {
        IO.socket("gloogloo.com", 80).bufferedOutputStream();
        InputStream smtpHoneypot = IO.socket("localhost", 25).inputStream();
    }

    public void streams() throws IOException {
        InputStream providedInput = new ByteArrayInputStream(new byte[7]);
        IO.stream(providedInput).gzip().inputStream();

        OutputStream providedOutput = new ByteArrayOutputStream();
        IO.stream(providedOutput).printWriter(256);
    }

    public byte[] fluent() throws IOException {
        // create and combine both objects
        var bytes = IO.bytes().printWriter();

        // write the string
        bytes.write("doodoo");

        // close the writer and the inner stream at once
        bytes.close();

        // extract result
        return bytes.getInner();
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
        var bytes = IO.bytes().printWriter();
        bytes.write("doodoo");
        bytes.close();
        return bytes.getInner();
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
        try (var bytes = IO.bytes().printWriter()) {
            bytes.write("doodoo");
            return bytes.getInner();
        }
    }

    public byte[] classic3() throws IOException {
        try (var byteOutputStream = new ByteArrayOutputStream();
                var writer = new PrintWriter(byteOutputStream)) {
            writer.write("doodoo");
            return byteOutputStream.toByteArray();
        }
    }

}
