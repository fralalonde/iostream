package ca.rbon.iostream;

import java.io.Closeable;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

public class ChainTest {
    
    Closeable closeable = Mockito.mock(Closeable.class);
    
    @Test
    public void selfCloseAdd() {
        Assertions.assertThat(Chain.NO_PROXY.add(closeable)).isSameAs(closeable);
    }
    
}
