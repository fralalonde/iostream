package iostream.resource.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import iostream.CloseChain;
import iostream.flow.Sink;
import iostream.flow.Source;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileResource implements Sink<File>, Source<File> {

    @Getter
    final File target;
    
    final boolean append;    

    @Override
    public FileInputStream getInputStream(CloseChain toClose) throws FileNotFoundException {
	return toClose.add(new FileInputStream(target));	
    }

    @Override
    public FileWriter getWriter(CloseChain onClose) throws IOException {
	return onClose.add(new FileWriter(target, append));
    }

    @Override
    public FileReader getReader(CloseChain onClose) throws IOException {
	return onClose.add(new FileReader(target));
    }

    @Override
    public FileOutputStream getOutputStream(CloseChain onClose) throws FileNotFoundException {
	return onClose.add(new FileOutputStream(target, append));
    }

    @Override
    public File getResource() {
	return target;
    }

}
