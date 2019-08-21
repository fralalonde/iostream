package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class RandomInputStream extends InputStream {

    final Random random;

    public RandomInputStream(Random random) {
        this.random = random;
    }

    @Override
    public int read() throws IOException {
        return random.nextInt(256);
    }

}
