package iostream;

import java.io.File;
import java.io.IOException;

import iostream.proxy.builder.InBuilder;
import iostream.proxy.builder.OutBuilder;
import iostream.proxy.builder.ReaderBuilder;
import iostream.proxy.builder.WriterBuilder;
import iostream.target.bytearray.ByteArrayBuilder;
import iostream.target.bytearray.OutBytesTarget;
import iostream.target.file.FileBuilder;
import iostream.target.file.FileTarget;
import iostream.target.string.InStringTarget;
import iostream.target.string.OutStringTarget;

public class IoStream {

    public static FileBuilder file(String name) {
	return new FileBuilder(new FileTarget(new File(name)));
    }

    public static FileBuilder file(File file) {
	return new FileBuilder(new FileTarget(file));
    }

    public static OutBuilder<File> tempFile() throws IOException {
	return new OutBuilder<>(new FileTarget(File.createTempFile(IoStream.class.getSimpleName(), "tmp")));
    }

    public static ReaderBuilder string(String str) {
	InStringTarget instr = new InStringTarget(str);
	return () -> instr;
    }

    public static WriterBuilder<String> string() {
	OutStringTarget outstr = new OutStringTarget();
	return () -> outstr;
    }

    //
    // static FileInputOrOutput console(String name) {
    // return null;
    // }
    //
    // static SocketInputOrOutput socket(String addr, int port) {
    // return null;
    // }
    //
    //
    static OutBuilder<byte[]> bytes() {
	return new OutBuilder<>(new OutBytesTarget());
    }

    static ByteArrayBuilder bytes(byte[] array) {
	return null;
    }

    //
    // static FileInputOrOutput file(int size) {
    // return null;
    // }
    //
    // static TargetFlow byteArray(byte[] bytes) {
    // return null;
    // }
    //
    // static PipeInputOrOutput pipe() {
    // return null;
    // }
    //
    // static TargetFlow nil() {
    // return null;
    // }
    //
    // static TargetFlow random() {
    // return null;
    // }
    //
    // static OutStreamFilter stream(OutputStream os) {
    // return null;
    // }
    //
    // static InStreamFilter stream(InputStream is) {
    // return null;
    // }

}
