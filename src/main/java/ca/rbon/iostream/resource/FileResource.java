package ca.rbon.iostream.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.channel.InOutChannel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>FilePicker class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class FileResource extends BaseResource<File> implements InOutChannel<File> {
    
    @NonNull
    final File file;
    
    final boolean append;
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream(Chain chain) throws IOException {
        return chain.addLink(new FileInputStream(file));
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        return null;
    }
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream(Chain chain) throws IOException {
        return chain.addLink(new FileOutputStream(file, append));
    }
    
    /** {@inheritDoc} */
    @Override
    protected Writer getWriter(Chain chain) throws IOException {
        return null;
    }
    
    /** {@inheritDoc} */
    @Override
    public File getResource() {
        return file;
    }
    
}
