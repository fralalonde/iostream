package iostream.resource.bytearray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import iostream.flow.ByteSink;
import iostream.flow.CharSink;
import iostream.flow.Sink;
import iostream.natural.NaturalOutputStreamBuilder;
import iostream.proxy.OutStreamBuilder;
import iostream.proxy.WriterBuilder;

public class OutArrayBuilder implements WriterBuilder<byte[]>, OutStreamBuilder<byte[]>, NaturalOutputStreamBuilder<ByteArrayOutputStream> {

    final Sink<byte[]> sink = new OutBytesResource(); 
    
    @Override
    public ByteArrayOutputStream outputStream() throws IOException {
	return new ByteArrayOutputStream();
    }

    @Override
    public ByteSink<byte[]> getByteSink() throws IOException {
	return sink;
    }

    @Override
    public CharSink<byte[]> getCharSink() throws IOException {
	return sink;
    }

}