package ca.rbon.iostream.fluent;

import java.nio.charset.Charset;

public interface OutEncodingPick<T> extends OutBufferPick<T> {
    
    OutBufferPick<T> encoding(Charset encoding);
    
    default OutBufferPick<T> encoding(String charsetName) {
        return encoding(Charset.forName(charsetName));
    }
    
    
}
