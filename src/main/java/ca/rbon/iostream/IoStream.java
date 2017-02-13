package ca.rbon.iostream;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Socket;

import ca.rbon.iostream.channel.CharWriterChannel;
import ca.rbon.iostream.channel.InOutCharChannel;
import ca.rbon.iostream.channel.InOutChannel;
import ca.rbon.iostream.channel.InputChannel;
import ca.rbon.iostream.channel.OutputChannel;
import ca.rbon.iostream.channel.OutputStreamChannel;
import ca.rbon.iostream.resource.ByteArrayResource;
import ca.rbon.iostream.resource.ConsoleResource;
import ca.rbon.iostream.resource.FileResource;
import ca.rbon.iostream.resource.PipeInResource;
import ca.rbon.iostream.resource.PipeOutResource;
import ca.rbon.iostream.resource.SocketResource;
import ca.rbon.iostream.resource.StringResource;

/**
 * <p>
 * IoStream is the root object for IO streams builder methods.
 * Creating an IO stream involves selecting a resource such as {@link #file(String)} and a channel such as {@link OutputStreamChannel#dataOutputStream()}.  
 * </p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class IoStream {
    
    /**
     * Stream to or from a file.
     *
     * @param name Name of the file to use
     * @return An input or output picker
     */
    public static InOutChannel<File> file(String name) {
        return file(new File(name));
    }
    
    /**
     * Stream to or from a file.
     *
     * @param file File to use
     * @return An input or output picker
     */
    public static InOutChannel<File> file(File file) {
        return new FileResource(file, false);
    }
    
    /**
     * Write to a file.
     *
     * @param name Name of file to write to.
     * @param append True to append to an existing file. False to overwrite an existing file. If file doesn't exist it is just created.
     * @return An output picker
     */
    public static OutputChannel<File> file(String name, boolean append) {
        return file(new File(name), append);
    }
    
    /**
     * Write to a file.
     *
     * @param file File to write to.
     * @param append True to append to an existing file. False to overwrite an existing file. If file doesn't exist it is just created.
     * @return An output picker
     */
    public static OutputChannel<File> file(File file, boolean append) {
        return new FileResource(file, append);
    }
    
    /**
     * Write to a temporary file.
     *
     * @return An output picker
     * @throws java.io.IOException If the file could not be created
     */
    public static OutputChannel<File> tempFile() throws IOException {
        return file(File.createTempFile(IoStream.class.getSimpleName(), "tmp"));
    }
    
    /**
     * Read from or append to an existing string.
     *
     * @param str The string to read or append to
     * @return An input or output char picker
     */
    public static InOutCharChannel<String> string(String str) {
        return new StringResource(str, StringResource.DEFAULT_CAPACITY);
    }
    
    /**
     * Append to a new string.
     *
     * @return An output char picker
     */
    public static CharWriterChannel<String> string() {
        return string(null, StringResource.DEFAULT_CAPACITY);
    }
    
    /**
     * Append to a new string.
     *
     * @param intialCapacity the initial size of the buffer
     * @return An output char picker
     */
    public static CharWriterChannel<String> string(int intialCapacity) {
        return string(null, intialCapacity);
    }
    
    /**
     * Append to an existing string.
     *
     * @param str The string to append to
     * @param intialCapacity The initial size of the buffer
     * @return An output char picker
     */
    public static CharWriterChannel<String> string(String str, int intialCapacity) {
        return new StringResource(str, intialCapacity);
    }
    
    /**
     * Write to a new byte array with default initial capacity.
     *
     * @return An output picker
     */
    public static OutputChannel<byte[]> bytes() {
        return bytes(ByteArrayResource.DEFAULT_CAPACITY);
    }
    
    /**
     * Write to a new byte array with specified initial capacity.
     *
     * @param intialCapacity The initial capacity of the buffer
     * @return An output picker
     */
    public static OutputChannel<byte[]> bytes(int intialCapacity) {
        return new ByteArrayResource(null, intialCapacity);
    }
    
    /**
     * Read from an existing array or append to it.
     *
     * @param array The array to read from or append to
     * @return An input or output picker
     */
    public static InOutChannel<byte[]> bytes(byte[] array) {
        return new ByteArrayResource(array, ByteArrayResource.DEFAULT_CAPACITY);
    }
    
    /**
     * Append to an existing array with specfied additional capacity.
     *
     * @param array The array to read from or append to
     * @return An input or output picker
     * @param additionalCapacity a int.
     */
    public static OutputChannel<byte[]> bytes(byte[] array, int additionalCapacity) {
        return new ByteArrayResource(array, additionalCapacity);
    }
    
    /**
     * Read or write from a socket.
     *
     * @param host The host name to connect to
     * @param port The port to connect to
     * @return An input or output picker
     * @throws java.io.IOException If the socket could not be opened
     */
    public static InOutChannel<Socket> socket(String host, int port) throws IOException {
        return socket(new Socket(host, port));
    }
    
    /**
     * Read or write from a socket.
     *
     * @param socket The socket to use
     * @return An input or output picker
     * @throws java.io.IOException If the socket could not be opened
     */
    public static InOutChannel<Socket> socket(Socket socket) throws IOException {
        return new SocketResource(socket);
    }
    
    /**
     * <p>console.</p>
     *
     * @return a {@link ca.rbon.iostream.channel.InOutChannel} object.
     */
    public static InOutChannel<Console> console() {
        return new ConsoleResource();
    }
    
    /**
     * <p>pipeInput.</p>
     *
     * @return a {@link ca.rbon.iostream.channel.InputChannel} object.
     */
    public static InputChannel<PipedInputStream> pipeInput() {
        return new PipeInResource(null);
    }
    
    /**
     * <p>pipeInput.</p>
     *
     * @param connect a {@link java.io.PipedOutputStream} object.
     * @return a {@link ca.rbon.iostream.channel.InputChannel} object.
     */
    public static InputChannel<PipedInputStream> pipeInput(PipedOutputStream connect) {
        return new PipeInResource(connect);
    }
    
    /**
     * <p>pipeOutput.</p>
     *
     * @return a {@link ca.rbon.iostream.channel.OutputChannel} object.
     */
    public static OutputChannel<PipedOutputStream> pipeOutput() {
        return new PipeOutResource(null);
    }
    
    /**
     * <p>pipeOutput.</p>
     *
     * @param connect a {@link java.io.PipedInputStream} object.
     * @return a {@link ca.rbon.iostream.channel.OutputChannel} object.
     */
    public static OutputChannel<PipedOutputStream> pipeOutput(PipedInputStream connect) {
        return new PipeOutResource(connect);
    }
    
    // static InOutPick nil() {
    // return null;
    // }
    
    // static InBytesPick random() {
    // return null;
    // }
    
    // static OutPick stream(OutputStream os) {
    // return null;
    // }
    
    // static InPick stream(InputStream is) {
    // return null;
    // }
    
}
