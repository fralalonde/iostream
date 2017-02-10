package ca.rbon.iostream;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import ca.rbon.iostream.fluent.BiCharPick;
import ca.rbon.iostream.fluent.BiPick;
import ca.rbon.iostream.fluent.OutPick;
import ca.rbon.iostream.fluent.WriterPick;
import ca.rbon.iostream.picker.BytesPicker;
import ca.rbon.iostream.picker.FilePicker;
import ca.rbon.iostream.picker.SocketPicker;
import ca.rbon.iostream.picker.StringPicker;

public class IoStream2 {
    
    public static BiPick<File> file(String name) {
        return file(new File(name));
    }
    
    public static BiPick<File> file(File file) {
        return new FilePicker(file, false);
    }
    
    public static OutPick<File> file(String name, boolean append) {
        return file(new File(name), append);
    }
    
    public static OutPick<File> file(File file, boolean append) {
        return new FilePicker(file, append);
    }
    
    public static OutPick<File> tempFile() throws IOException {
        return file(File.createTempFile(IoStream2.class.getSimpleName(), "tmp"));
    }
    
    public static BiCharPick<String> string(String str) {
        return new StringPicker(str, StringPicker.DEFAULT_CAPACITY);
    }
    
    public static WriterPick<String> string() {
        return string(null, StringPicker.DEFAULT_CAPACITY);
    }
    
    public static WriterPick<String> string(int intialCapacity) {
        return string(null, intialCapacity);
    }
    
    public static WriterPick<String> string(String str, int intialCapacity) {
        return new StringPicker(str, intialCapacity);
    }
    
    static OutPick<byte[]> bytes() {
        return bytes(BytesPicker.DEFAULT_CAPACITY);
    }
    
    static OutPick<byte[]> bytes(int intialCapacity) {
        return new BytesPicker(null, intialCapacity);
    }
    
    static BiPick<byte[]> bytes(byte[] array) {
        return bytes(array, BytesPicker.DEFAULT_CAPACITY);
    }
    
    static BiPick<byte[]> bytes(byte[] array, int intialCapacity) {
        return new BytesPicker(array, intialCapacity);
    }
    
    static BiPick<Socket> socket(String host, int port) throws IOException {
        return socket(new Socket(host, port));
    }

    static BiPick<Socket> socket(Socket socket) throws IOException {
        return new SocketPicker(socket);
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
