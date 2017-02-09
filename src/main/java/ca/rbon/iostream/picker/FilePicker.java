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
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FilePicker extends BasePicker<File> implements Resource<File> {
    
    final File file;
    
    boolean append;
        
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
    
}
