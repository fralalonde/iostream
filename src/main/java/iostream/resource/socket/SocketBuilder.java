package iostream.resource.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import iostream.CloseChain;
import iostream.flow.ByteSink;
import iostream.flow.ByteSource;
import iostream.natural.NaturalInputStreamBuilder;
import iostream.natural.NaturalOutputStreamBuilder;
import iostream.proxy.InStreamBuilder;
import iostream.proxy.OutStreamBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SocketBuilder implements OutStreamBuilder<Socket>, InStreamBuilder<Socket>,
	NaturalOutputStreamBuilder<OutputStream>, NaturalInputStreamBuilder<InputStream> {

    final SocketResource resource;

    @Override
    public OutputStream outputStream() throws IOException {
	return resource.getOutputStream(CloseChain.SELF_CLOSE);
    }

    @Override
    public ByteSink<Socket> getByteSink() throws IOException {
	return resource;
    }

    @Override
    public InputStream inputStream() throws IOException {
	return resource.getInputStream(CloseChain.SELF_CLOSE);
    }

    @Override
    public ByteSource<Socket> getByteSource() {
	return resource;
    }

}
