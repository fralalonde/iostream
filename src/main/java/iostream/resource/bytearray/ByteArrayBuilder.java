package iostream.resource.bytearray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import iostream.flow.ByteSink;
import iostream.flow.ByteSource;
import iostream.flow.CharSink;
import iostream.flow.CharSource;
import iostream.natural.NaturalInputStreamBuilder;
import iostream.natural.NaturalOutputStreamBuilder;
import iostream.proxy.InStreamBuilder;
import iostream.proxy.OutStreamBuilder;
import iostream.proxy.ReaderBuilder;
import iostream.proxy.WriterBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ByteArrayBuilder
	implements InStreamBuilder<byte[]>, ReaderBuilder<byte[]>, WriterBuilder<byte[]>, OutStreamBuilder<byte[]>,
	NaturalInputStreamBuilder<ByteArrayInputStream>, NaturalOutputStreamBuilder<ByteArrayOutputStream> {

    final byte[] bytes;

    @Override
    public ByteArrayInputStream inputStream() {
	return new ByteArrayInputStream(bytes);
    }

    @Override
    public ByteArrayOutputStream outputStream() throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
	baos.write(bytes);
	return baos;
    }

    @Override
    public ByteSink<byte[]> getByteSink() throws IOException {
	return new OutBytesResource(bytes);
    }

    @Override
    public CharSink<byte[]> getCharSink() throws IOException {
	return new OutBytesResource(bytes);
    }

    @Override
    public CharSource<byte[]> getCharSource() throws IOException {
	return new InBytesResource(bytes);
    }

    @Override
    public ByteSource<byte[]> getByteSource() {
	return new InBytesResource(bytes);
    }

}
