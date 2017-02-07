package iostream.resource.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import iostream.CloseChain;
import iostream.flow.ByteSink;
import iostream.flow.ByteSource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SocketResource implements ByteSink<Socket>, ByteSource<Socket> {

    Socket socket;

    @Override
    public Socket getResource() {
	return socket;
    }

    @Override
    public OutputStream getOutputStream(CloseChain toClose) throws IOException {
	return toClose.add(socket.getOutputStream());
    }

    @Override
    public InputStream getInputStream(CloseChain toClose) throws IOException {
	return toClose.add(socket.getInputStream());
    }

    public SocketResource(String host, int port) throws IOException {
	socket = new Socket(host, port);
    }

}