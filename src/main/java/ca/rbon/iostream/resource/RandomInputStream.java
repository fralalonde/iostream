package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RandomInputStream extends InputStream {
    
    final Random random;
    
    @Override
    public int read() throws IOException {
        return random.nextInt();
    }
    
}
