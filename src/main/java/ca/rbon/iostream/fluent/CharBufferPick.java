package ca.rbon.iostream.fluent;

public interface CharBufferPick<T> extends InOutCharPick<T> {
 
    InOutCharPick<T> bufferSize(int size);    
    
}
