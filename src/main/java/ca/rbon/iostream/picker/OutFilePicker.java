package ca.rbon.iostream.picker;

import java.io.File;

import ca.rbon.iostream.fluent.OutEncodingPick;

public class OutFilePicker extends TmpFilePicker implements OutEncodingPick<File> {
    
    public OutFilePicker(File file, boolean append) {
        super(file);
        this.append = append;
    }
    
}
