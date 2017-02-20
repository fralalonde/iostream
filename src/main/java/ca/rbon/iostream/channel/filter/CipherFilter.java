package ca.rbon.iostream.channel.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CipherFilter implements FilterFactory {
    
    @NonNull
    final Cipher cipher;
    
    @Override
    public InputStream filterInput(InputStream input) throws IOException {
        return new CipherInputStream(input, cipher);
    }
    
    @Override
    public OutputStream filterOutput(OutputStream output) throws IOException {
        return new CipherOutputStream(output, cipher);
    }
    
}
