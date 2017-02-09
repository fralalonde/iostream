package ca.rbon.iostream.picker;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.fluent.AppendPick;
import ca.rbon.iostream.fluent.CharBufferPick;
import ca.rbon.iostream.fluent.InOutPick;
import ca.rbon.iostream.fluent.OutEncodingPick;
import ca.rbon.iostream.proxy.stream.BufferedInputProxy;
import ca.rbon.iostream.proxy.stream.BufferedOutputProxy;
import ca.rbon.iostream.proxy.stream.DataInputProxy;
import ca.rbon.iostream.proxy.stream.DataOutputProxy;
import ca.rbon.iostream.proxy.stream.ObjectInputProxy;
import ca.rbon.iostream.proxy.stream.ObjectOutputProxy;
import ca.rbon.iostream.proxy.stream.ZipInputProxy;
import ca.rbon.iostream.proxy.stream.ZipOutputProxy;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;

public class InOutFilePicker extends FilePicker implements AppendPick<File> {
    
    public InOutFilePicker(File file) {
        super(file);
    }
    
    @Override
    public CharBufferPick<File> encoding(Charset encoding) {
        this.encoding = encoding;
        return new CharFilePicker(this);
    }
    
    @Override
    public InOutPick<File> bufferSize(int size) {
        this.bufferSize = size;
        return this;
    }
    
    @Override
    public OutEncodingPick<File> append(boolean append) {
        return new OutFilePicker(file, append);
    }
    
    @Override
    public ZipInputProxy<File> zipInputStream() throws IOException {
        return super.zipInputStream();
    }
    
    @Override
    public BufferedInputProxy<File> bufferedInputStream() throws IOException {
        return super.bufferedInputStream();
    }
    
    @Override
    public DataInputProxy<File> dataInputStream() throws IOException {
        return super.dataInputStream();
    }
    
    @Override
    public ObjectInputProxy<File> objectInputStream() throws IOException {
        return super.objectInputStream();
    }
    
    @Override
    public BufferedReaderProxy<File> bufferedReader() throws IOException {
        return super.bufferedReader();
    }
    
    // OUTPUT
    
    @Override
    public ZipOutputProxy<File> zipOutputStream() throws IOException {
        return super.zipOutputStream();
    }
    
    @Override
    public BufferedOutputProxy<File> bufferedOutputStream() throws IOException {
        return super.bufferedOutputStream();
    }
    
    @Override
    public DataOutputProxy<File> dataOutputStream() throws IOException {
        return super.dataOutputStream();
    }
    
    @Override
    public ObjectOutputProxy<File> objectOutputStream() throws IOException {
        return super.objectOutputStream();
    }
    
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
