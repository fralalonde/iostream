package ca.rbon.iostream.fluent;

public interface BufferPick<T> extends InOutPick<T> {
    
    InOutPick<T> bufferSize(int size);    
    
}
