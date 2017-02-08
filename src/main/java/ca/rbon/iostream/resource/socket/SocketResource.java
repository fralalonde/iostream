package ca.rbon.iostream.resource.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import ca.rbon.iostream.CloseChain;
import ca.rbon.iostream.flow.ByteSink;
import ca.rbon.iostream.flow.ByteSource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SocketResource implements ByteSink<Socket>, ByteSource<Socket> {
    
    final Socket socket;

    public SocketResource(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }
        
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
    
}
