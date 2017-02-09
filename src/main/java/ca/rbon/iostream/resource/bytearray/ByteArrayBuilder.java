package ca.rbon.iostream.resource.bytearray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import ca.rbon.iostream.flow.ByteSink;
import ca.rbon.iostream.flow.ByteSource;
import ca.rbon.iostream.flow.CharSink;
import ca.rbon.iostream.flow.CharSource;
import ca.rbon.iostream.natural.NaturalInputStream;
import ca.rbon.iostream.natural.NaturalOutputStream;
import ca.rbon.iostream.proxy.InStreamBuilder;
import ca.rbon.iostream.proxy.OutStreamBuilder;
import ca.rbon.iostream.proxy.ReaderBuilder;
import ca.rbon.iostream.proxy.WriterBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ByteArrayBuilder
        implements InStreamBuilder<byte[]>, ReaderBuilder<byte[]>, WriterBuilder<byte[]>, OutStreamBuilder<byte[]>,
        NaturalInputStream<ByteArrayInputStream>, NaturalOutputStream<ByteArrayOutputStream> {
    
    final byte[] bytes;
    
    @Override
    public ByteArrayInputStream inputStream() {
        return new ByteArrayInputStream(bytes);
    }
    
    @Override
    public ByteArrayOutputStream outputStream() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
        baos.write(bytes);
        return baos;
    }
    
    @Override
    public ByteSink<byte[]> getByteSink() throws IOException {
        return new OutBytesResource(bytes);
    }
    
    @Override
    public CharSink<byte[]> getCharSink() throws IOException {
        return new OutBytesResource(bytes);
    }
    
    @Override
    public CharSource<byte[]> getCharSource() throws IOException {
        return new InBytesResource(bytes);
    }
    
    @Override
    public ByteSource<byte[]> getByteSource() {
        return new InBytesResource(bytes);
    }
    
}
