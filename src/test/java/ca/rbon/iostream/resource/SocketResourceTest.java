package ca.rbon.iostream.resource;

import java.io.IOException;
import java.net.Socket;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import ca.rbon.iostream.IO;

public class SocketResourceTest {

    Socket socket = Mockito.mock(Socket.class);

    @Test(expected = NullPointerException.class)
    public void nullSocket() {
        IO.socket(null);
    }

    @Test
    public void input() throws IOException {
        IO.socket(socket).inputStream();
        Mockito.verify(socket).getInputStream();
    }

    @Test
    public void output() throws IOException {
        IO.socket(socket).outputStream();
        Mockito.verify(socket).getOutputStream();
    }

    @Test
    public void resource() throws IOException {
        Assertions.assertThat(IO.socket(socket).outputStream().getInner()).isSameAs(socket);
    }

}
