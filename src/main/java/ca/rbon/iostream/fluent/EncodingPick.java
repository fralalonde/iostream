package ca.rbon.iostream.fluent;

import java.nio.charset.Charset;

public interface EncodingPick<T> extends BufferPick<T> {
    
    CharBufferPick<T> encoding(Charset encoding);
    
    default CharBufferPick<T> encoding(String charsetName) {
        return encoding(Charset.forName(charsetName));
    }
    
}
