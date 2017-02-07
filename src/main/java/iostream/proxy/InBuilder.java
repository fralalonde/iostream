package iostream.proxy;

import iostream.flow.ByteSource;
import iostream.flow.CharSource;
import iostream.flow.Source;
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
