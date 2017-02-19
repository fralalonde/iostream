package ca.rbon.iostream.channel.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import ca.rbon.iostream.resource.Resource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GzipFilter implements FilterFactory {
    
    final int gzipBufferSize;
    
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
