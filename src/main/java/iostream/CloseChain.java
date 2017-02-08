package iostream;

import java.io.Closeable;

public interface CloseChain {
    
    class SelfClosing implements CloseChain {
        
        @Override
        public <T extends Closeable> T add(T closeable) {
            return closeable;
        }
    }
    
    CloseChain NO_PROXY = new SelfClosing();
    
    <T extends Closeable> T add(T closeable);
    
}
