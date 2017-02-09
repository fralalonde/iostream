package ca.rbon.iostream.fluent;

public interface AppendPick<T> extends EncodingPick<T> {
    
    OutEncodingPick<T> append(boolean append);
    
    default OutEncodingPick<T> append() {
        return append(true);
    }
    
}
