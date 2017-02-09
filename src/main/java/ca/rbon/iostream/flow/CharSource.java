package ca.rbon.iostream.flow;

import java.io.IOException;
import java.io.Reader;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.Resource;

public interface CharSource<T> extends Resource<T> {
    
    Reader getReader(Chain toClose) throws IOException;
    
}
