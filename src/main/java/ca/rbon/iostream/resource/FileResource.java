package ca.rbon.iostream.resource;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>
 * FilePicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class FileResource extends Resource<File> {


    final File file;

    final boolean append;

    public FileResource( File file, boolean append) {
        this.file = file;
        this.append = append;
    }

    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(file, append);
    }

    /** {@inheritDoc} */
    @Override
    public File getResource() {
        return file;
    }

}
