package ca.rbon.iostream.picker;

import java.io.File;
import java.io.IOException;

import ca.rbon.iostream.fluent.CharBufSizePick;
import ca.rbon.iostream.fluent.CharUnbufPick;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;

public class CharFilePicker extends FilePicker implements CharBufSizePick<File> {
    
    public CharFilePicker(FilePicker picker) {
        super(picker);
    }
    
    @Override
    public CharUnbufPick<File> bufferSize(int size) {
        bufferSize = size;
        return this;
    }
    
    @Override
    public BufferedReaderProxy<File> bufferedReader() throws IOException {
        return super.bufferedReader();
    }
    
    // OUTPUT
    
    @Override
    public PrintWriterProxy<File> printWriter() throws IOException {
        return super.printWriter();
    }
    
    @Override
    public PrintWriterProxy<File> printWriter(boolean autoflush) throws IOException {
        return super.printWriter(autoflush);
    }
    
    @Override
    public BufferedWriterProxy<File> bufferedWriter() throws IOException {
        return super.bufferedWriter();
    }
    
}
