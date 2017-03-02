package ca.rbon.iostream.channel.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

/**
 * A {@link Base64} filter factory.
 *
 * @author fralalonde
 */
public class Base64Filter implements FilterFactory {

    @Override
    public InputStream filterInput(InputStream input) throws IOException {
        return Base64.getDecoder().wrap(input);
    }

    @Override
    public OutputStream filterOutput(OutputStream output) throws IOException {
        return Base64.getEncoder().wrap(output);
    }

}
