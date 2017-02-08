package ca.rbon.iostream.proxy;

import ca.rbon.iostream.flow.ByteSource;
import ca.rbon.iostream.flow.CharSource;
import ca.rbon.iostream.flow.Source;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InBuilder<T> implements ReaderBuilder<T>, InStreamBuilder<T> {
    
    final Source<T> source;
    
    @Override
    public ByteSource<T> getByteSource() {
        return source;
    }
    
    @Override
    public CharSource<T> getCharSource() {
        return source;
    }
    
}
