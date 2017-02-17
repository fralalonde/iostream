package ca.rbon.iostream.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>
 * SocketPicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class SocketResource extends Resource<Socket> {
    
    @NonNull
    final Socket socket;
    
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }
    
    /** {@inheritDoc} */
    @Override
    public Socket getResource() {
        return socket;
    }
    
}
