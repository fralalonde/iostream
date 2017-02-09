package ca.rbon.iostream;

import java.io.File;
import java.io.IOException;

import ca.rbon.iostream.fluent.AppendPick;
import ca.rbon.iostream.fluent.BufferSizePick;
import ca.rbon.iostream.fluent.CharUnbufPick;
import ca.rbon.iostream.fluent.OutBufferPick;
import ca.rbon.iostream.fluent.WriterPick;
import ca.rbon.iostream.fluent.OutEncodingPick;
import ca.rbon.iostream.picker.InOutBytesPicker;
import ca.rbon.iostream.picker.InOutFilePicker;
import ca.rbon.iostream.picker.InOutStringPicker;
import ca.rbon.iostream.picker.OutBytesPicker;
import ca.rbon.iostream.picker.OutStringPicker;
import ca.rbon.iostream.picker.TmpFilePicker;

public class IoStream2 {
    
    public static AppendPick<File> file(String name) {
        return file(new File(name));
    }
        
    public static AppendPick<File> file(File file) {
        return new InOutFilePicker(file);
    }

    public static AppendPick<File> file(String name, boolean append) {
        return file(new File(name));
    }
        
    public static AppendPick<File> file(File file, boolean append) {
        return new InOutFilePicker(file);
    }    
    
    public static OutEncodingPick<File> tempFile() throws IOException {
        return new TmpFilePicker(File.createTempFile(IoStream2.class.getSimpleName(), "tmp"));
    }
    
    public static CharUnbufPick<String> string(String str) {
        return new InOutStringPicker(str);
    }
    
    public static WriterPick<String> string() {
        return new OutStringPicker();
    }
    
    public static WriterPick<String> string(int intialCapacity) {
        return new OutStringPicker(intialCapacity);
    }    

    public static WriterPick<String> string(String str, int intialCapacity) {
        return new OutStringPicker(str, intialCapacity);
    }    
    
    static OutBufferPick<byte[]> bytes() {
        return new OutBytesPicker();
    }
    
    static BufferSizePick<byte[]> bytes(byte[] array) {
        return new InOutBytesPicker(array);        
    }

//    static EncodingPick<Socket> socket(String host, int port) throws IOException {
//    }
//    
    
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
