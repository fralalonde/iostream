package iostream;

import java.io.File;
import java.io.IOException;

import iostream.proxy.OutBuilder;
import iostream.target.bytearray.ByteArrayBuilder;
import iostream.target.bytearray.OutArrayBuilder;
import iostream.target.file.FileBuilder;
import iostream.target.file.FileTarget;
import iostream.target.string.InStringTarget;
import iostream.target.string.OutStringTarget;
import iostream.target.string.StringInBuilder;
import iostream.target.string.StringOutBuilder;

public class IoStreams {

    public static FileBuilder file(String name) {
	return new FileBuilder(new FileTarget(new File(name), false));
    }
    
    public static OutBuilder<File> file(String name, boolean append) {
	return new OutBuilder<>(new FileTarget(new File(name), append));
    }
    

    public static FileBuilder file(File file) {
	return new FileBuilder(new FileTarget(file, false));
    }

    public static OutBuilder<File> file(File file, boolean append) {
	return new OutBuilder<>(new FileTarget(file, append));
    }
    
    public static OutBuilder<File> tempFile() throws IOException {
	return new OutBuilder<>(new FileTarget(File.createTempFile(IoStreams.class.getSimpleName(), "tmp"), false));
    }

    public static StringInBuilder string(String str) {
	return new StringInBuilder(new InStringTarget(str));
    }

    public static StringOutBuilder string() {
	return new StringOutBuilder(new OutStringTarget());
    }

    // static FileInputOrOutput console(String name) {
    // return null;
    // }
    //
    // static SocketInputOrOutput socket(String addr, int port) {
    // return null;
    // }
    
    static OutArrayBuilder bytes() {
	return new OutArrayBuilder();
    }

    static ByteArrayBuilder bytes(byte[] array) {
	return new ByteArrayBuilder(array);
    }

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
