package ca.rbon.iostream.picker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.fluent.InOutPick;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/**
 * <p>SocketPicker class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class SocketPicker extends BasePicker<Socket> implements InOutPick<Socket> {
    
    @NonNull
    final Socket socket;
        
    /** {@inheritDoc} */
    @Override
    protected InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }
    
    /** {@inheritDoc} */
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        return null;
    }
    
    /** {@inheritDoc} */
    @Override
    protected OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }
    
    /** {@inheritDoc} */
    @Override
    protected Writer getWriter(Chain chain) throws IOException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Socket getResource() {
        return socket;
    }
    
}
