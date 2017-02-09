package ca.rbon.iostream.picker;

import java.io.File;
import java.nio.charset.Charset;

import ca.rbon.iostream.fluent.OutBufPick;
import ca.rbon.iostream.fluent.OutBufferPick;
import ca.rbon.iostream.fluent.OutEncodingPick;

public class TmpFilePicker extends FilePicker implements OutEncodingPick<File> {
    
    public TmpFilePicker(FilePicker picker) {
        super(picker);
    }
    
    @Override
    public OutBufferPick<File> encoding(Charset encoding) {
        this.encoding = encoding;
        return this;
    }
    
    @Override
    public OutBufPick<File> bufferSize(int size) {
        this.bufferSize = size;
        return this;
    }
    
}
