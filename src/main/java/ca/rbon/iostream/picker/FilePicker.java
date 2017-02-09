package ca.rbon.iostream.picker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.Resource;
import ca.rbon.iostream.proxy.writer.BufferedWriterProxy;
import ca.rbon.iostream.proxy.writer.PrintWriterProxy;

public class FilePicker extends BasePicker<File> implements Resource<File> {
    
    File file;
    
    boolean append;
    
    public FilePicker(FilePicker picker) {
        super(picker);
        file = picker.file;
        append = picker.append;
    }
    
    protected Resource<File> getSupplier() {
        return this;
    }
    
    @Override
    protected InputStream getInputStream(Chain chain) throws IOException {
        return chain.add(new FileInputStream(file));
    }
    
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        return null;
    }
    
    @Override
    protected OutputStream getOutputStream(Chain chain) throws IOException {
        return chain.add(new FileOutputStream(file));
    }
    
    @Override
    protected Writer getWriter(Chain chain) throws IOException {
        return null;
    }
    
    @Override
    public File getResource() {
        return file;
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
