package iostream.resource.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import iostream.CloseChain;
import iostream.natural.NaturalInputStreamBuilder;
import iostream.natural.NaturalReaderBuilder;
import iostream.proxy.InStreamBuilder;
import iostream.proxy.ReaderBuilder;

public class FileBuilder extends OutFileBuilder
        implements InStreamBuilder<File>, ReaderBuilder<File>, NaturalInputStreamBuilder<FileInputStream>, NaturalReaderBuilder<FileReader> {
    
    public FileBuilder(FileResource fileTarget) {
        super(fileTarget);
    }
    
    @Override
    public FileInputStream inputStream() throws FileNotFoundException {
        return fileTarget.getInputStream(CloseChain.NO_PROXY);
    }
    
    @Override
    public FileReader reader() throws IOException {
        return fileTarget.getReader(CloseChain.NO_PROXY);
    }
    
    @Override
    public FileResource getCharSource() throws IOException {
        return fileTarget;
    }
    
    @Override
    public FileResource getByteSource() {
        return fileTarget;
    }
    
}
