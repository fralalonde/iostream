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
import ca.rbon.iostream.fluent.InOutPick;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>FilePicker class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class FilePicker extends BasePicker<File> implements InOutPick<File> {
    
    @NonNull
    final File file;
    
    final boolean append;
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        return null;
    }
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(file, append);
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
