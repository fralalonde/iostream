package ca.rbon.iostream.channel.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A {@link Cipher} filter factory.
 *
 * @author fralalonde
 */
@RequiredArgsConstructor
public class CipherFilter implements FilterFactory {

    @NonNull
    final Cipher cipher;

    @Override
    public InputStream filterInput(InputStream input) {
        return new CipherInputStream(input, cipher);
    }

    @Override
    public OutputStream filterOutput(OutputStream output) {
        return new CipherOutputStream(output, cipher);
    }

}
