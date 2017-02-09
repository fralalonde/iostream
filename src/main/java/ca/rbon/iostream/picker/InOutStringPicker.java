package ca.rbon.iostream.picker;

import java.io.IOException;

import ca.rbon.iostream.fluent.CharUnbufPick;
import ca.rbon.iostream.proxy.writer.BufferedReaderProxy;

public class InOutStringPicker extends StringPicker implements CharUnbufPick<String> {
 
    public InOutStringPicker(String str) {
        super();
        this.str = str;
    }

    public CharUnbufPick<String> bufferSize(int size) {
        bufferSize = size;
        return this;
    }
    
    @Override
    public BufferedReaderProxy<String> bufferedReader() throws IOException {
        return super.bufferedReader();
    }
        
}
