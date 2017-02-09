package ca.rbon.iostream.resource.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.natural.NaturalInputStream;
import ca.rbon.iostream.natural.NaturalReader;
import ca.rbon.iostream.proxy.InStreamBuilder;
import ca.rbon.iostream.proxy.ReaderBuilder;

public class FileBuilder extends OutFileBuilder
        implements InStreamBuilder<File>, ReaderBuilder<File>, NaturalInputStream<FileInputStream>, NaturalReader<FileReader> {
    
    public FileBuilder(FileResource fileTarget) {
        super(fileTarget);
    }
    
    @Override
    public FileInputStream inputStream() throws FileNotFoundException {
        return fileTarget.getInputStream(Chain.NO_PROXY);
    }
    
    @Override
    public FileReader reader() throws IOException {
        return fileTarget.getReader(Chain.NO_PROXY);
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
