package ca.rbon.iostream.resource.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.flow.ByteSink;
import ca.rbon.iostream.flow.ByteSource;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Wither;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SocketResource implements ByteSink<Socket>, ByteSource<Socket> {
    
    final Socket socket;
    
    @Wither
    @Getter
    final Charset encoding;    
            
    @Override
    public Socket getResource() {
        return socket;
    }
    
    @Override
    public OutputStream getOutputStream(Chain toClose) throws IOException {
        return toClose.add(socket.getOutputStream());
    }
    
    @Override
    public InputStream getInputStream(Chain toClose) throws IOException {
        return toClose.add(socket.getInputStream());
    }

    public SocketResource(Socket sock) {
        socket = sock;
        encoding = null;
    }
    
}
