package ca.rbon.iostream;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChainClose<T> implements Chain, Closeable, Resource<T> {
    
    final List<Closeable> links = new ArrayList<>();
    
    @Override
    public <C extends Closeable> C add(C closeable) {
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

    @Override
    public T getResource() throws IOException {
        return null;
    }
    
}
