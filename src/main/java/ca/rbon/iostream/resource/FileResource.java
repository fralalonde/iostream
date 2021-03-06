package ca.rbon.iostream.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>
 * FilePicker class.
 * </p>

 */
public class FileResource extends Resource<File> {

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
    protected OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(file, append);
    }

    /** {@inheritDoc} */
    @Override
    public File getResource() {
        return file;
    }

}
