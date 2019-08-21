package ca.rbon.iostream.channel.filter;

import ca.rbon.iostream.resource.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * A gzip filter factory.
 *
 * @author fralalonde
 */
public class GzipFilter implements FilterFactory {

    final int gzipBufferSize;

    public GzipFilter(int gzipBufferSize) {
        this.gzipBufferSize = gzipBufferSize;
    }

    @Override
    public InputStream filterInput(InputStream input) throws IOException {
        switch (gzipBufferSize) {
        case Resource.DEFAULT_BUFFER_SIZE:
            return new GZIPInputStream(input);
        default:
            return new GZIPInputStream(input, gzipBufferSize);
        }
    }

    @Override
    public OutputStream filterOutput(OutputStream output) throws IOException {
        switch (gzipBufferSize) {
        case Resource.DEFAULT_BUFFER_SIZE:
            return new GZIPOutputStream(output);
        default:
            return new GZIPOutputStream(output, gzipBufferSize);
        }
    }

}
