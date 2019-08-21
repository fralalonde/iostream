package ca.rbon.iostream.channel.filter;



import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A {@link Cipher} filter factory.
 *
 * @author fralalonde
 */
public class CipherFilter implements FilterFactory {


    final Cipher cipher;

    public CipherFilter( Cipher cipher) {
        this.cipher = cipher;
    }

    @Override
    public InputStream filterInput(InputStream input) throws IOException {
        return new CipherInputStream(input, cipher);
    }

    @Override
    public OutputStream filterOutput(OutputStream output) throws IOException {
        return new CipherOutputStream(output, cipher);
    }

}
