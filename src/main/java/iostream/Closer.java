package iostream;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Closer implements CloseChain {

    final List<Closeable> closeables = new ArrayList<>();

    @Override
    public <T extends Closeable> T add(T closeable) {
	closeables.add(closeable);
	return closeable;
    }

    public void closeAll() throws IOException {
	for (Closeable c : closeables) {
	    c.close();
	}
    }

}