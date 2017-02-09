package ca.rbon.iostream.picker;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.fluent.OutBufferPick;
import ca.rbon.iostream.fluent.OutEncodingPick;
import ca.rbon.iostream.fluent.OutPick;
import ca.rbon.iostream.proxy.stream.BufferedOutputProxy;
import ca.rbon.iostream.proxy.stream.DataOutputProxy;
import ca.rbon.iostream.proxy.stream.ObjectOutputProxy;
import ca.rbon.iostream.proxy.stream.ZipOutputProxy;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;

public class TmpFilePicker extends FilePicker implements OutEncodingPick<File> {
    
    public TmpFilePicker(File file) {
        super(file);
    }

    @Override
    public OutBufferPick<File> encoding(Charset encoding) {
        this.encoding = encoding;
        return this;
    }

    @Override
    public OutPick<File> bufferSize(int size) {
        this.bufferSize = size;
        return this;
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
