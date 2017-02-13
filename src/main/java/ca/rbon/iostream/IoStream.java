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
     * <p>Stream to or from a file.</p>
     *
     * @param name Name of the file to use
     * @return An input or output picker
     */
    public static InOutChannel<File> file(String name) {
        return file(new File(name));
    }
    
    /**
     * <p>Stream to or from a file.</p>
     *
     * @param file File to use
     * @return An input or output picker
     */
    public static InOutChannel<File> file(File file) {
        return new FileResource(file, false);
    }
    
    /**
     * <p>Write to a file.</p>
     *
     * @param name Name of file to write to.
     * @param append True to append to an existing file. False to overwrite an existing file. If file doesn't exist it is just created.
     * @return An output picker
     */
    public static OutputChannel<File> file(String name, boolean append) {
        return file(new File(name), append);
    }
    
    /**
     * <p>Write to a file.</p>
     *
     * @param file File to write to.
     * @param append True to append to an existing file. False to overwrite an existing file. If file doesn't exist it is just created.
     * @return An output picker
     */
    public static OutputChannel<File> file(File file, boolean append) {
        return new FileResource(file, append);
    }
    
    /**
     * <p>Write to a temporary file.</p>
     *
     * @return An output picker
     * @throws java.io.IOException If the file could not be created
     */
    public static OutputChannel<File> tempFile() throws IOException {
        return file(File.createTempFile(IoStream.class.getSimpleName(), "tmp"));
    }
    
    /**
     * <p>Read from or append to an existing string.</p>
     *
     * @param str The string to read or append to
     * @return An input or output char picker
     */
    public static InOutCharChannel<String> string(String str) {
        return new StringResource(str, StringResource.DEFAULT_CAPACITY);
    }
    
    /**
     * <p>Append to a new string.</p>
     *
     * @return An output char picker
     */
    public static CharWriterChannel<String> string() {
        return string(null, StringResource.DEFAULT_CAPACITY);
    }
    
    /**
     * <p>Append to a new string.</p>
     *
     * @param intialCapacity the initial size of the buffer
     * @return An output char picker
     */
    public static CharWriterChannel<String> string(int intialCapacity) {
        return string(null, intialCapacity);
    }
    
    /**
     * <p>Append to an existing string.</p>
     *
     * @param str The string to append to
     * @param intialCapacity The initial size of the buffer
     * @return An output char picker
     */
    public static CharWriterChannel<String> string(String str, int intialCapacity) {
        return new StringResource(str, intialCapacity);
    }
    
    /**
     * <p>Write to a new byte array with default initial capacity.</p>
     *
     * @return An output picker
     */
    public static OutputChannel<byte[]> bytes() {
        return bytes(ByteArrayResource.DEFAULT_CAPACITY);
    }
    
    /**
     * <p>Write to a new byte array with specified initial capacity.</p>
     *
     * @param intialCapacity The initial capacity of the buffer
     * @return An output picker
     */
    public static OutputChannel<byte[]> bytes(int intialCapacity) {
        return new ByteArrayResource(null, intialCapacity);
    }
    
    /**
     * <p>Read from an existing array or append to it.</p>
     *
     * @param array The array to read from or append to
     * @return An input or output picker
     */
    public static InOutChannel<byte[]> bytes(byte[] array) {
        return new ByteArrayResource(array, ByteArrayResource.DEFAULT_CAPACITY);
    }
    
    /**
     * <p>Append to an existing array with specfied additional capacity.</p>
     *
     * @param array The array to read from or append to
     * @return An input or output picker
     * @param additionalCapacity a int.
     */
    public static OutputChannel<byte[]> bytes(byte[] array, int additionalCapacity) {
        return new ByteArrayResource(array, additionalCapacity);
    }
    
    /**
     * <p>Read or write from a socket.</p>
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
     * <p>Read or write from a socket.</p>
     *
     * @param socket The socket to use
     * @return An input or output picker
     * @throws java.io.IOException If the socket could not be opened
     */
    public static InOutChannel<Socket> socket(Socket socket) throws IOException {
        return new SocketResource(socket);
    }
    
    /**
     * <p>Read or write from the console.</p>
     *
     * @return a {@link ca.rbon.iostream.channel.InOutChannel} object.
     */
    public static InOutChannel<Console> console() {
        return new ConsoleResource();
    }
    
    /**
     * <p>Read from a PipeOutputStream to be built.</p>
     *
     * @return a {@link ca.rbon.iostream.channel.InputChannel} object.
     */
    public static InputChannel<PipedInputStream> pipeInput() {
        return new PipeInResource(null);
    }
    
    /**
     * <p>Read from an existing PipeOutputStream.</p>
     *
     * @param connect a {@link java.io.PipedOutputStream} object.
     * @return a {@link ca.rbon.iostream.channel.InputChannel} object.
     */
    public static InputChannel<PipedInputStream> pipeInput(PipedOutputStream connect) {
        return new PipeInResource(connect);
    }
    
    /**
     * <p>Write to a PipeInputStream to be built.</p>
     *
     * @return a {@link ca.rbon.iostream.channel.OutputChannel} object.
     */
    public static OutputChannel<PipedOutputStream> pipeOutput() {
        return new PipeOutResource(null);
    }
    
    /**
     * <p>Write to an existing PipeInputStream to be built.</p>
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
