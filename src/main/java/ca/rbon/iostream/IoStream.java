package ca.rbon.iostream;

import java.io.File;
import java.io.IOException;

import ca.rbon.iostream.proxy.OutBuilder;
import ca.rbon.iostream.resource.bytearray.ByteArrayBuilder;
import ca.rbon.iostream.resource.bytearray.OutArrayBuilder;
import ca.rbon.iostream.resource.file.FileBuilder;
import ca.rbon.iostream.resource.file.FileResource;
import ca.rbon.iostream.resource.socket.SocketBuilder;
import ca.rbon.iostream.resource.socket.SocketResource;
import ca.rbon.iostream.resource.string.InStringResource;
import ca.rbon.iostream.resource.string.OutStringResource;
import ca.rbon.iostream.resource.string.StringInBuilder;
import ca.rbon.iostream.resource.string.StringOutBuilder;

public class IoStream {
    
    public static FileBuilder file(String name) {
        return new FileBuilder(new FileResource(new File(name), false));
    }
    
    public static OutBuilder<File> file(String name, boolean append) {
        return new OutBuilder<>(new FileResource(new File(name), append));
    }
    
    public static FileBuilder file(File file) {
        return new FileBuilder(new FileResource(file, false));
    }
    
    public static OutBuilder<File> file(File file, boolean append) {
        return new OutBuilder<>(new FileResource(file, append));
    }
    
    public static OutBuilder<File> tempFile() throws IOException {
        return new OutBuilder<>(new FileResource(File.createTempFile(IoStream.class.getSimpleName(), "tmp"), false));
    }
    
    public static StringInBuilder string(String str) {
        return new StringInBuilder(new InStringResource(str));
    }
    
    public static StringOutBuilder string() {
        return new StringOutBuilder(new OutStringResource());
    }
    
    static SocketBuilder socket(String host, int port) throws IOException {
        return new SocketBuilder(new SocketResource(host, port));
    }
    
    static OutArrayBuilder bytes() {
        return new OutArrayBuilder();
    }
    
    static ByteArrayBuilder bytes(byte[] array) {
        return new ByteArrayBuilder(array);
    }
    
    // static FileInputOrOutput console(String name) {
    // return null;
    // }
    
    // static PipeInputOrOutput pipe() {
    // return null;
    // }
    
    // static TargetFlow nil() {
    // return null;
    // }
    
    // static TargetFlow random() {
    // return null;
    // }
    
    // static OutStreamFilter stream(OutputStream os) {
    // return null;
    // }
    
    // static InStreamFilter stream(InputStream is) {
    // return null;
    // }
    
}
