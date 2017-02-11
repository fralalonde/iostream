package ca.rbon.iostream.stream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.rbon.iostream.ClosingResource;
import ca.rbon.iostream.Resource;

public class DataOutputOf<T> extends DataOutputStream implements Resource<T> {
    
    final ClosingResource<T> closer;
    
    public DataOutputOf(ClosingResource<T> cl, OutputStream os) throws IOException {
        super(os);
        closer = cl;
    }
    
    @Override
    public void close() throws IOException {
        super.close();
        closer.close();
    }
    
    @Override
    public T getResource() throws IOException {
        return closer.getResource();
    }
    
}
