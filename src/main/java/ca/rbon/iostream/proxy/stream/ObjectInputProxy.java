package ca.rbon.iostream.proxy.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import ca.rbon.iostream.ChainClose;
import ca.rbon.iostream.Resource;

public class ObjectInputProxy<T> extends ObjectInputStream implements Resource<T> {
    
    final ChainClose closer;
    
    final Resource<T> holder;
    
    public ObjectInputProxy(Resource<T> t, ChainClose cl, InputStream is) throws IOException {
        super(is);
        holder = t;
        cl.add(is);
        closer = cl;
    }
    
    @Override
    public void close() throws IOException {
        closer.close();
    }
    
    @Override
    public T getResource() {
        return holder.getResource();
    }
    
    
}
