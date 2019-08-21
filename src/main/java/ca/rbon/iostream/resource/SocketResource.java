package ca.rbon.iostream.resource;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * <p>
 * SocketPicker class.
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class SocketResource extends Resource<Socket> {


    final Socket socket;

    public SocketResource( Socket socket) {
        this.socket = socket;
    }

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
