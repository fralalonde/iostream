package iostream.proxy.builder;

import iostream.flow.ByteSource;
import iostream.flow.CharSource;
import iostream.flow.Source;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InBuilder implements ReaderBuilder, InStreamBuilder {

    final Source source;

    @Override
    public ByteSource getByteSource() {
	return source;
    }

    @Override
    public CharSource getCharSource() {
	return source;
    }

}
