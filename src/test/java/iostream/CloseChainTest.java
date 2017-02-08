package iostream;

import java.io.Closeable;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

public class CloseChainTest {
    
    Closeable closeable = Mockito.mock(Closeable.class);

    @Test
    public void selfCloseAdd() {
	Assertions.assertThat(CloseChain.NO_PROXY.add(closeable)).isSameAs(closeable);
    }
        

}
