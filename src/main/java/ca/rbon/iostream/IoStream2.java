package ca.rbon.iostream;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import ca.rbon.iostream.fluent.AppendPick;
import ca.rbon.iostream.fluent.EncodingPick;
import ca.rbon.iostream.fluent.InOutPick;
import ca.rbon.iostream.fluent.OutEncodingPick;
import ca.rbon.iostream.fluent.OutPick;
import ca.rbon.iostream.picker.InOutFilePicker;
import ca.rbon.iostream.picker.TmpFilePicker;

public class IoStream2 {
    
    public static AppendPick<File> file(String name) {
        return file(new File(name));
    }
        
    public static AppendPick<File> file(File file) {
        return new InOutFilePicker(file);
    }
    
    public static OutEncodingPick<File> tempFile() throws IOException {
        return new TmpFilePicker(File.createTempFile(IoStream.class.getSimpleName(), "tmp"));
    }
    
    public static InOutPick<String> string(String str) {
    }
    
    public static OutPick<String> string() {
    }
    
    public static OutPick<String> string(int intialCapacity) {
    }    

    public static OutPick<String> string(String str, int intialCapacity) {
    }    
    
    static EncodingPick<Socket> socket(String host, int port) throws IOException {
    }
    
    static OutEncodingPick<byte[]> bytes() {
    }
    
    static EncodingPick<byte[]> bytes(byte[] array) {
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
