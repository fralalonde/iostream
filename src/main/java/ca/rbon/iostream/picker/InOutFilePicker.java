package ca.rbon.iostream.picker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import ca.rbon.iostream.fluent.AppendPick;
import ca.rbon.iostream.fluent.CharBufSizePick;
import ca.rbon.iostream.fluent.UnbufPick;
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

public class InOutFilePicker extends FilePicker implements AppendPick<File> {
    
    public InOutFilePicker(File file) {
        super(file);
    }
    
    @Override
    public OutputStream outputStream() throws FileNotFoundException {
        return super.outputStream();
    }
    
    @Override
    public CharBufSizePick<File> encoding(Charset encoding) {
        this.encoding = encoding;
        return new CharFilePicker(this);
    }
    
    @Override
    public UnbufPick<File> bufferSize(int size) {
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
    public ZipInputProxy<File> zipInputStream(Charset zipEntriesCharset) throws IOException {
        return super.zipInputStream(zipEntriesCharset);
    }
    
    @Override
    public ZipInputProxy<File> zipInputStream(String zipEntriesCharsetName) throws IOException {
        return super.zipInputStream(zipEntriesCharsetName);
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
    public ZipOutputProxy<File> zipOutputStream(Charset zipEntriesCharset) throws IOException {
        return super.zipOutputStream(zipEntriesCharset);
    }
    
    @Override
    public ZipOutputProxy<File> zipOutputStream(String zipEntriesCharsetName) throws IOException {
        return super.zipOutputStream(zipEntriesCharsetName);
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
    
}
