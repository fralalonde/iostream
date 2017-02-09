package ca.rbon.iostream.resource.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import ca.rbon.iostream.Chain;
import ca.rbon.iostream.flow.ByteSink;
import ca.rbon.iostream.flow.ByteSource;
import ca.rbon.iostream.natural.NaturalInputStream;
import ca.rbon.iostream.natural.NaturalOutputStream;
import ca.rbon.iostream.proxy.InStreamBuilder;
import ca.rbon.iostream.proxy.OutStreamBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SocketBuilder implements OutStreamBuilder<Socket>, InStreamBuilder<Socket>,
        NaturalOutputStream<OutputStream>, NaturalInputStream<InputStream> {
    
    final SocketResource resource;
    
    @Override
    public OutputStream outputStream() throws IOException {
        return resource.getOutputStream(Chain.NO_PROXY);
    }
    
    @Override
    public ByteSink<Socket> getByteSink() throws IOException {
        return resource;
    }
    
    @Override
    public InputStream inputStream() throws IOException {
        return resource.getInputStream(Chain.NO_PROXY);
    }
    
    @Override
    public ByteSource<Socket> getByteSource() {
        return resource;
    }
    
}
