package iostream;

import java.io.Closeable;

public interface CloseChain {

    <T extends Closeable> T add(T closeable);
    
    static class SingleCloseable implements CloseChain {

	@Override
	public <T extends Closeable> T add(T closeable) {
	    return closeable;
	}	
    }
    
    static CloseChain SELF_CLOSE = new SingleCloseable(); 
    
    
}