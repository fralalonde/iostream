package ca.rbon.iostream.flow;

import java.io.IOException;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.Resource;

public interface CharSink<T> extends Resource<T> {
    
    Writer getWriter(Chain toClose) throws IOException;
    
}
