package ca.rbon.iostream.picker;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.Resource;
import ca.rbon.iostream.fluent.BiPick;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsolePicker extends BasePicker<Console> implements BiPick<Console> {
        
    protected Resource<Console> getSupplier() {
        return this;
    }
    
    @Override
    protected InputStream getInputStream() throws IOException {
        return System.in;
    }
    
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        return System.console().reader();
    }
    
    @Override
    protected OutputStream getOutputStream() throws IOException {
        return System.err;
    }
    
    @Override
    protected Writer getWriter(Chain chain) throws IOException {
        return System.console().writer();
    }
    
    @Override
    public Console getResource() {
        return System.console();
    }

    
}
