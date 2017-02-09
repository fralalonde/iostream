package ca.rbon.iostream.fluent;

public interface OutCharBufferPick<T> extends OutCharPick<T>{
    
    OutCharPick<T> bufferSize(int size);        
    
}
