package iostream.resource.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import iostream.CloseChain;
import iostream.natural.NaturalOutputStreamBuilder;
import iostream.natural.NaturalWriterBuilder;
import iostream.proxy.OutStreamBuilder;
import iostream.proxy.WriterBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutFileBuilder
        implements OutStreamBuilder<File>, WriterBuilder<File>, NaturalWriterBuilder<FileWriter>, NaturalOutputStreamBuilder<FileOutputStream> {
    
    @Getter
    final FileResource fileTarget;
    
    @Override
    public FileOutputStream outputStream() throws IOException {
        return fileTarget.getOutputStream(CloseChain.SELF_CLOSE);
    }
    
    @Override
    public FileWriter writer() throws IOException {
        return fileTarget.getWriter(CloseChain.SELF_CLOSE);
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
