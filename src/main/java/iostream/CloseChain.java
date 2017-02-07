package iostream;

import java.io.Closeable;

public interface CloseChain {
    
    class SingleCloseable implements CloseChain {
        
        @Override
        public <T extends Closeable> T add(T closeable) {
            return closeable;
        }
    }
    
    CloseChain SELF_CLOSE = new SingleCloseable();
    
    <T extends Closeable> T add(T closeable);
    
}
