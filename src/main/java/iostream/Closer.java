package iostream;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Closer implements CloseChain {

    final List<Closeable> toClose = new ArrayList<>();

    final OnCloseIOExceptionHandler onex;

    /* (non-Javadoc)
     * @see chunkingpow.CloseChain#register(T)
     */
    @Override
    public <T extends Closeable> T register(T closeable) {
	toClose.add(closeable);
	return closeable;
    }

    public void closeAll() {
	for (Closeable c : toClose) {
	    try {
		c.close();
	    } catch (IOException e) {
		onex.accept(e);
	    }
	}
    }

}