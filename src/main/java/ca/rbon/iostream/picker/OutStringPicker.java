package ca.rbon.iostream.picker;

import ca.rbon.iostream.fluent.OutCharBufferPick;
import ca.rbon.iostream.fluent.WriterPick;

public class OutStringPicker extends StringPicker implements OutCharBufferPick<String> {

    public OutStringPicker() {
    }

    public OutStringPicker(int intialCapacity) {
        capacity = intialCapacity;
    }

    public OutStringPicker(String prepend, int intialCapacity) {
        capacity = intialCapacity;
        str = prepend;
    }

    @Override
    public WriterPick<String> bufferSize(int size) {
        bufferSize = size;
        return this;
    }

    
}
