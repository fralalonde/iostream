package ca.rbon.iostream.resource.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.flow.Sink;
import ca.rbon.iostream.flow.Source;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Wither;

@RequiredArgsConstructor
public class FileResource implements Sink<File>, Source<File> {
    
    @Getter
    @NonNull
    final File target;
    
    @Wither
    final boolean append;

    @Wither
    @Getter
    final Charset encoding;    

    public FileResource(File file, boolean b) {
        target = file;
        append = b;
        encoding = null;
    }
        
    @Override
    public FileInputStream getInputStream(Chain toClose) throws FileNotFoundException {
        return toClose.add(new FileInputStream(target));
    }
    
    @Override
    public FileWriter getWriter(Chain onClose) throws IOException {
        return onClose.add(new FileWriter(target, append));
    }
    
    @Override
    public FileReader getReader(Chain onClose) throws IOException {
        return onClose.add(new FileReader(target));
    }
    
    @Override
    public FileOutputStream getOutputStream(Chain onClose) throws FileNotFoundException {
        return onClose.add(new FileOutputStream(target, append));
    }
    
    @Override
    public File getResource() {
        return target;
    }

}
