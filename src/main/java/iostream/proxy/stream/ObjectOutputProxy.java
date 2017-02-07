package iostream.proxy.stream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import iostream.Closer;
import iostream.ResourceHolder;

public class ObjectOutputProxy<T> extends ObjectOutputStream implements ResourceHolder<T> {
    
    final Closer closer;
    
    final ResourceHolder<T> holder;
    
    public ObjectOutputProxy(ResourceHolder<T> t, Closer cl, OutputStream os) throws IOException {
        super(os);
        holder = t;
        cl.add(os);
        closer = cl;
    }
    
    @Override
    public void close() throws IOException {
        closer.closeAll();
    }
    
    @Override
    public T getResource() {
        return holder.getResource();
    }
    
}
