package ca.rbon.iostream;

import java.io.Closeable;

public interface Chain {
    
    class EmptyChain implements Chain {
        
        @Override
        public <T extends Closeable> T add(T closeable) {
            return closeable;
        }
    }
    
    Chain NO_PROXY = new EmptyChain();
    
    <T extends Closeable> T add(T closeable);
    
}
