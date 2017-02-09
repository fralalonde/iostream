package ca.rbon.iostream.picker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.Resource;

public abstract class BytesPicker extends BasePicker<byte[]> implements Resource<byte[]> {
    
    private static final String PREVENTED =
            " This should error should have been prevented by the fluent IoStream builder constraints." +
                    " Please create report this issue at https://github.com/fralalonde/iostream/issues";
    
    private static final String NO_BYTE_ARRAY_SET = "No byte array was provided for this operation." + PREVENTED;
    
    private static final int DEFAULT_CAPACITY = -1;
    
    byte[] bytes;
    
    int initialCapacity = DEFAULT_CAPACITY;
    
    ByteArrayOutputStream stream;
    
    public BytesPicker(BytesPicker picker) {
        super(picker);
        bytes = picker.bytes;
        initialCapacity = picker.initialCapacity;
    }
    
    public byte[] getResource() {
        if (stream != null) {
            return stream.toByteArray();
        }
        if (bytes == null) {
            throw new IllegalStateException(NO_BYTE_ARRAY_SET);
        }
        return bytes;
    }
    
    @Override
    protected Resource<byte[]> getSupplier() {
        return this;
    }
    
    private ByteArrayInputStream rawInputStream() {
        if (bytes == null) {
            throw new IllegalStateException(NO_BYTE_ARRAY_SET);
        }
        return new ByteArrayInputStream(bytes);
    }
    
    @Override
    protected ByteArrayInputStream getInputStream(Chain chain) throws IOException {
        return chain.add(rawInputStream());
    }
    
    @Override
    protected Reader getReader(Chain chain) throws IOException {
        return null;
    }
    
    private ByteArrayOutputStream rawOutputStream() {
        return initialCapacity > DEFAULT_CAPACITY
                ? new ByteArrayOutputStream(initialCapacity)
                : new ByteArrayOutputStream();
    }
    
    @Override
    protected ByteArrayOutputStream getOutputStream(Chain chain) throws IOException {
        return chain.add(rawOutputStream());
    }
    
    @Override
    protected Writer getWriter(Chain chain) throws IOException {
        return null;
    }
    
//    @Override
//    public ZipInputProxy<byte[]> zipInputStream() throws IOException {
//        return super.zipInputStream();
//    }
//    
//    @Override
//    public DataInputProxy<byte[]> dataInputStream() throws IOException {
//        return super.dataInputStream();
//    }
//    
//    @Override
//    public ObjectInputProxy<byte[]> objectInputStream() throws IOException {
//        return super.objectInputStream();
//    }
//    
//    @Override
//    public ZipOutputProxy<byte[]> zipOutputStream() throws IOException {
//        return super.zipOutputStream();
//    }
//    
//    @Override
//    public DataOutputProxy<byte[]> dataOutputStream() throws IOException {
//        return super.dataOutputStream();
//    }
//    
//    @Override
//    public ObjectOutputProxy<byte[]> objectOutputStream() throws IOException {
//        return super.objectOutputStream();
//    }
//    
//    @Override
//    public PrintWriterProxy<byte[]> printWriter() throws IOException {
//        return super.printWriter();
//    }
//    
//    @Override
//    public PrintWriterProxy<byte[]> printWriter(boolean autoflush) throws IOException {
//        return super.printWriter(autoflush);
//    }
    
}
