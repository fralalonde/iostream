package iostream;

import java.io.Closeable;

public interface CloseChain {

    <T extends Closeable> T register(T closeable);
    
    static class Self implements CloseChain {

	@Override
	public <T extends Closeable> T register(T closeable) {
	    return closeable;
	}	
    }
    
    static CloseChain SELF_CLOSE = new Self(); 
    
    
}