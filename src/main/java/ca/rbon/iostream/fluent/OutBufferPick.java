package ca.rbon.iostream.fluent;

public interface OutBufferPick<T> extends OutPick<T> {
    
    OutPick<T> bufferSize(int size);
    
}
