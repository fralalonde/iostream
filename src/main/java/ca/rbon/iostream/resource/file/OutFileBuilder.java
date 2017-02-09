package ca.rbon.iostream.resource.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.natural.NaturalOutputStream;
import ca.rbon.iostream.natural.NaturalWriter;
import ca.rbon.iostream.proxy.OutStreamBuilder;
import ca.rbon.iostream.proxy.WriterBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutFileBuilder
        implements OutStreamBuilder<File>, WriterBuilder<File>, NaturalWriter<FileWriter>, NaturalOutputStream<FileOutputStream> {
    
    @Getter
    final FileResource fileTarget;
    
    @Override
    public FileOutputStream outputStream() throws IOException {
        return fileTarget.getOutputStream(Chain.NO_PROXY);
    }
    
    @Override
    public FileWriter writer() throws IOException {
        return fileTarget.getWriter(Chain.NO_PROXY);
    }
    
    @Override
    public FileResource getCharSink() {
        return fileTarget;
    }
    
    @Override
    public FileResource getByteSink() {
        return fileTarget;
    }
    
}
