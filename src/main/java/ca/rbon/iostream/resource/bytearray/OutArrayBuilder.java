package ca.rbon.iostream.resource.bytearray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import ca.rbon.iostream.flow.ByteSink;
import ca.rbon.iostream.flow.CharSink;
import ca.rbon.iostream.flow.Sink;
import ca.rbon.iostream.natural.NaturalOutputStreamBuilder;
import ca.rbon.iostream.proxy.OutStreamBuilder;
import ca.rbon.iostream.proxy.WriterBuilder;

public class OutArrayBuilder implements WriterBuilder<byte[]>, OutStreamBuilder<byte[]>, NaturalOutputStreamBuilder<ByteArrayOutputStream> {
    
    final Sink<byte[]> sink = new OutBytesResource();
    
    @Override
    public ByteArrayOutputStream outputStream() throws IOException {
        return new ByteArrayOutputStream();
    }
    
    @Override
    public ByteSink<byte[]> getByteSink() throws IOException {
        return sink;
    }
    
    @Override
    public CharSink<byte[]> getCharSink() throws IOException {
        return sink;
    }
    
}
