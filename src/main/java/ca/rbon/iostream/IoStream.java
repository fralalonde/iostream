package ca.rbon.iostream;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Socket;

import ca.rbon.iostream.fluent.InOutPick;
import ca.rbon.iostream.fluent.InOutCharPick;
import ca.rbon.iostream.fluent.InPick;
import ca.rbon.iostream.fluent.OutPick;
import ca.rbon.iostream.fluent.CharWriterPick;
import ca.rbon.iostream.picker.BytesPicker;
import ca.rbon.iostream.picker.ConsolePicker;
import ca.rbon.iostream.picker.FilePicker;
import ca.rbon.iostream.picker.PipeInPicker;
import ca.rbon.iostream.picker.PipeOutPicker;
import ca.rbon.iostream.picker.SocketPicker;
import ca.rbon.iostream.picker.StringPicker;

public class IoStream {
    
    /**
     * Stream to or from a file.
     * 
     * @param name Name of the file to use
     * @return An input or output picker
     */
    public static InOutPick<File> file(String name) {
        return file(new File(name));
    }
    
    /**
     * Stream to or from a file.
     * 
     * @param file File to use
     * @return An input or output picker
     */
    public static InOutPick<File> file(File file) {
        return new FilePicker(file, false);
    }
    
    /**
     * Write to a file.
     * 
     * @param name Name of file to write to.
     * @param append True to append to an existing file. False to overwrite an existing file. If file doesn't exist it is just created.
     * @return An output picker
     */
    public static OutPick<File> file(String name, boolean append) {
        return file(new File(name), append);
    }
    
    /**
     * Write to a file.
     * 
     * @param file File to write to.
     * @param append True to append to an existing file. False to overwrite an existing file. If file doesn't exist it is just created.
     * @return An output picker
     */
    public static OutPick<File> file(File file, boolean append) {
        return new FilePicker(file, append);
    }
    
    /**
     * Write to a temporary file.
     * 
     * @return An output picker
     * @throws IOException If the file could not be created
     */
    public static OutPick<File> tempFile() throws IOException {
        return file(File.createTempFile(IoStream.class.getSimpleName(), "tmp"));
    }
    
    /**
     * Read from or append to an existing string.
     * 
     * @param str The string to read or append to
     * @return An input or output char picker
     */
    public static InOutCharPick<String> string(String str) {
        return new StringPicker(str, StringPicker.DEFAULT_CAPACITY);
    }
    
    /**
     * Append to a new string.
     * 
     * @return An output char picker
     */
    public static CharWriterPick<String> string() {
        return string(null, StringPicker.DEFAULT_CAPACITY);
    }
    
    /**
     * Append to a new string.
     * 
     * @param intialCapacity the initial size of the buffer
     * @return An output char picker
     */
    public static CharWriterPick<String> string(int intialCapacity) {
        return string(null, intialCapacity);
    }
    
    /**
     * Append to an existing string.
     * 
     * @param str The string to append to
     * @param intialCapacity The initial size of the buffer
     * @return An output char picker
     */
    public static CharWriterPick<String> string(String str, int intialCapacity) {
        return new StringPicker(str, intialCapacity);
    }
    
    /**
     * Write to a new byte array with default initial capacity.
     * 
     * @return An output picker
     */
    public static OutPick<byte[]> bytes() {
        return bytes(BytesPicker.DEFAULT_CAPACITY);
    }
    
    /**
     * Write to a new byte array with specified initial capacity.
     * 
     * @param intialCapacity The initial capacity of the buffer
     * @return An output picker
     */
    public static OutPick<byte[]> bytes(int intialCapacity) {
        return new BytesPicker(null, intialCapacity);
    }
    
    /**
     * Read from an existing array or append to it.
     * 
     * @param array The array to read from or append to
     * @return An input or output picker
     */
    public static InOutPick<byte[]> bytes(byte[] array) {
        return new BytesPicker(array, BytesPicker.DEFAULT_CAPACITY);
    }
    
    /**
     * Append to an existing array with specfied additional capacity.
     * 
     * @param array The array to read from or append to
     * @return An input or output picker
     */
    public static OutPick<byte[]> bytes(byte[] array, int additionalCapacity) {
        return new BytesPicker(array, additionalCapacity);
    }
    
    /**
     * Read or write from a socket.
     * 
     * @param host The host name to connect to
     * @param port The port to connect to
     * @return An input or output picker
     * @throws IOException If the socket could not be opened
     */
    public static InOutPick<Socket> socket(String host, int port) throws IOException {
        return socket(new Socket(host, port));
    }
    
    /**
     * Read or write from a socket.
     * 
     * @param socket The socket to use
     * @return An input or output picker
     * @throws IOException If the socket could not be opened
     */
    public static InOutPick<Socket> socket(Socket socket) throws IOException {
        return new SocketPicker(socket);
    }
    
    public static InOutPick<Console> console() {
        return new ConsolePicker();
    }
    
    public static InPick<PipedInputStream> pipeInput() {
        return new PipeInPicker(null);
    }
    
    public static InPick<PipedInputStream> pipeInput(PipedOutputStream connect) {
        return new PipeInPicker(connect);
    }
    
    public static OutPick<PipedOutputStream> pipeOutput() {
        return new PipeOutPicker(null);
    }
    
    public static OutPick<PipedOutputStream> pipeOutput(PipedInputStream connect) {
        return new PipeOutPicker(connect);
    }
    
    // static TargetFlow nil() {
    // return null;
    // }
    
    // static TargetFlow random() {
    // return null;
    // }
    
    // static OutStreamFilter stream(OutputStream os) {
    // return null;
    // }
    
    // static InStreamFilter stream(InputStream is) {
    // return null;
    // }
    
}
