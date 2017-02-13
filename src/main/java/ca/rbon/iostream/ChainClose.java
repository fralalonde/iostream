package ca.rbon.iostream;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>ChainClose class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class ChainClose implements Chain, Closeable {
    
    @Getter
    final List<Closeable> links = new ArrayList<>();
    
    /** {@inheritDoc} */
    @Override
    public <C extends Closeable> C chainAdd(C closeable) {
        links.add(closeable);
        return closeable;
    }
    
    /** {@inheritDoc} */
    @Override
    public void close() throws IOException {
        ListIterator<Closeable> reverse = links.listIterator(links.size());
        while (reverse.hasPrevious()) {
            reverse.previous().close();
        }
    }
    
}
