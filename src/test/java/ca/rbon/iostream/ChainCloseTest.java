package ca.rbon.iostream;

import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class ChainCloseTest {
    
    Closeable closeable1 = Mockito.mock(Closeable.class);
    Closeable closeable2 = Mockito.mock(Closeable.class);
    
    @Test
    public void test() throws IOException {
        ChainClose chain = new ChainClose();
        chain.addLink(closeable2);
        chain.addLink(closeable1);
        chain.close();
        InOrder inOrder = Mockito.inOrder(closeable2, closeable1);
        inOrder.verify(closeable1).close();
        inOrder.verify(closeable2).close();
    }
        
    
}
