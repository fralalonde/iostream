package iostream.proxy.builder;

import iostream.flow.ByteSink;
import iostream.flow.CharSink;
import iostream.flow.Sink;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutBuilder<T> implements WriterBuilder<T>, OutStreamBuilder<T> {

    final Sink<T> sink;

    @Override
    public ByteSink<T> getByteSink() {
	return sink;
    }

    @Override
    public CharSink<T> getCharSink() {
	return sink;
    }

}
