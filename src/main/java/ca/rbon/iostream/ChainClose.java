package ca.rbon.iostream;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChainClose implements Chain, Closeable {
    
    final List<Closeable> links = new ArrayList<>();
    
    @Override
    public <T extends Closeable> T add(T closeable) {
        links.add(closeable);
        return closeable;
    }
    
    @Override
    public void close() throws IOException {
        ListIterator<Closeable> reverse = links.listIterator(links.size());
        while (reverse.hasPrevious()) {
            reverse.previous().close();
        }
    }
    
}
