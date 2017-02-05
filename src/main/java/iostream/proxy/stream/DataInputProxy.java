package iostream.proxy.stream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import iostream.Closer;

public class DataInputProxy extends DataInputStream {

    final Closer closer;

    public DataInputProxy(Closer cl, InputStream is) throws IOException {
        super(is);
        cl.register(is);
        closer = cl;
    }
    

    public void close() {
        closer.closeAll();	    
    }

    
}