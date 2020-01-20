package ca.rbon.iostream;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import ca.rbon.iostream.channel.BytesBiChannel;
import ca.rbon.iostream.channel.BytesInChannel;
import ca.rbon.iostream.channel.BytesOutChannel;
import ca.rbon.iostream.channel.CharBiChannel;
import ca.rbon.iostream.channel.CharOutChannel;
import ca.rbon.iostream.channel.part.ByteOut;
import ca.rbon.iostream.resource.*;
import ca.rbon.iostream.wrap.InputStreamOf;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * IoStream is the root object for IO streams builder methods. Creating an IO
 * stream involves selecting a resource such as {@link #file(String)} and a
 * channel such as {@link ByteOut#dataOutputStream()}.
 * </p>

 */
public class IO {

    private static final String DEFAULT_TEMP_FILE_PREFIX = "IoStream";

    /**
     * <p>
     * Stream to or from a file.
     * </p>
     *
     * @param name Name of the file to use
     * @return An input or output picker
     */
    public static BytesBiChannel<File> file(String name) {
        return file(new File(name));
    }

    /**
     * <p>
     * Stream to or from a file.
     * </p>
     *
     * @param file File to use
     * @return An input or output picker
     */
    @SuppressWarnings("unchecked")
    public static BytesBiChannel<File> file(File file) {
        return proxy(new FileResource(file, false), BytesBiChannel.class);
    }

    /**
     * <p>
     * Write to a file.
     * </p>
     *
     * @param name   Name of file to write to.
     * @param append True to append to an existing file. False to overwrite an
     *               existing file. If file doesn't exist it is just created.
     * @return An output picker
     */
    public static BytesOutChannel<File> file(String name, boolean append) {
        return file(new File(name), append);
    }

    /**
     * <p>
     * Write to a file.
     * </p>
     *
     * @param file   File to write to.
     * @param append True to append to an existing file. False to overwrite an
     *               existing file. If file doesn't exist it is just created.
     * @return An output picker
     */
    @SuppressWarnings("unchecked")
    public static BytesOutChannel<File> file(File file, boolean append) {
        return proxy(new FileResource(file, append), BytesOutChannel.class);
    }

    /**
     * <p>
     * Write to a temporary file with default prefix 'IoStream' and suffix 'tmp'.
     * The file will be created in the system's default temp folder.
     * </p>
     *
     * @return An output picker
     * @throws java.io.IOException If the file could not be created
     */
    public static BytesOutChannel<File> tempFile() throws IOException {
        return tempFile(DEFAULT_TEMP_FILE_PREFIX, "tmp", null);
    }

    /**
     * <p>
     * Write to a temporary file with specified prefix and suffix. The file will be
     * created in the system's default temp folder.
     * </p>
     *
     * @param prefix the file prefix
     * @param suffix the file suffix
     * @return An output channel builder
     * @throws java.io.IOException If the file could not be created
     */
    public static BytesOutChannel<File> tempFile(String prefix, String suffix) throws IOException {
        return tempFile(prefix, suffix, null);
    }

    /**
     * <p>
     * Write to a temporary file with specified prefix and suffix.
     * </p>
     *
     * @param prefix the file prefix
     * @param suffix the file suffix
     * @param folder the folder to create the file in
     * @return An output channel builder
     * @throws java.io.IOException If the file could not be created
     */
    @SuppressWarnings("unchecked")
    public static BytesOutChannel<File> tempFile(String prefix, String suffix, File folder) throws IOException {
        return proxy(new FileResource(File.createTempFile(prefix, suffix, folder), false), BytesOutChannel.class);
    }

    /**
     * <p>
     * Read from or append to an existing string.
     * </p>
     *
     * @param str The string to read or append to
     * @return An input or output char picker
     */
    @SuppressWarnings("unchecked")
    public static CharBiChannel<String> string(String str) {
        return proxy(new StringResource(str, StringResource.DEFAULT_CAPACITY), CharBiChannel.class);
    }

    /**
     * <p>
     * Append to a new string.
     * </p>
     *
     * @return An output char picker
     */
    public static CharOutChannel<String> string() {
        return string(null, StringResource.DEFAULT_CAPACITY);
    }

    /**
     * <p>
     * Append to a new string.
     * </p>
     *
     * @param intialCapacity the initial size of the buffer
     * @return An output char picker
     */
    public static CharOutChannel<String> string(int intialCapacity) {
        return string(null, intialCapacity);
    }

    /**
     * <p>
     * Append to an existing string.
     * </p>
     *
     * @param str            The string to append to
     * @param intialCapacity The initial size of the buffer
     * @return An output char picker
     */
    @SuppressWarnings("unchecked")
    public static CharOutChannel<String> string(String str, int intialCapacity) {
        return proxy(new StringResource(str, intialCapacity), CharOutChannel.class);
    }

    /**
     * <p>
     * The {@link System#out} output stream.
     * </p>
     *
     * @return an OutputStream channel
     */
    @SuppressWarnings("unchecked")
    public static BytesOutChannel<OutputStream> stdout() {
        return proxy(new OutputStreamResource(System.out), BytesOutChannel.class);
    }

    /**
     * <p>
     * The {@link System#in} input stream.
     * </p>
     *
     * @return an InputStream channel
     */
    @SuppressWarnings("unchecked")
    public static BytesInChannel<InputStream> stdin() {
        return proxy(new InputStreamResource(System.in), BytesInChannel.class);
    }

    /**
     * <p>
     * Write to a new byte array with default initial capacity.
     * </p>
     *
     * @return An output picker
     */
    public static BytesOutChannel<byte[]> bytes() {
        return bytes(ByteArrayResource.DEFAULT_CAPACITY);
    }

    /**
     * <p>
     * Write to a new byte array with specified initial capacity.
     * </p>
     *
     * @param intialCapacity The initial capacity of the buffer
     * @return An output picker
     */
    @SuppressWarnings("unchecked")
    public static BytesOutChannel<byte[]> bytes(int intialCapacity) {
        return proxy(new ByteArrayResource(null, intialCapacity), BytesOutChannel.class);
    }

    /**
     * <p>
     * Read from an existing array or append to it.
     * </p>
     *
     * @param array The array to read from or append to
     * @return An input or output picker
     */
    @SuppressWarnings("unchecked")
    public static BytesBiChannel<byte[]> bytes(byte[] array) {
        return proxy(new ByteArrayResource(array, ByteArrayResource.DEFAULT_CAPACITY), BytesBiChannel.class);
    }

    /**
     * <p>
     * Append to an existing array with specfied additional capacity.
     * </p>
     *
     * @param array              The array to read from or append to
     * @param additionalCapacity The appending buffer capacity after the first bytes
     *                           have been added
     * @return An input or output picker
     */
    @SuppressWarnings("unchecked")
    public static BytesOutChannel<byte[]> bytes(byte[] array, int additionalCapacity) {
        return proxy(new ByteArrayResource(array, additionalCapacity), BytesOutChannel.class);
    }

    /**
     * <p>
     * Read or write from a socket.
     * </p>
     *
     * @param host The host name to connect to
     * @param port The port to connect to
     * @return a {@link ca.rbon.iostream.channel.BytesBiChannel} object
     * @throws java.io.IOException If the socket could not be opened
     */
    public static BytesBiChannel<Socket> socket(String host, int port) throws IOException {
        return socket(new Socket(host, port));
    }

    /**
     * <p>
     * Read or write from a socket.
     * </p>
     *
     * @param socket The socket to use
     * @return a {@link ca.rbon.iostream.channel.BytesBiChannel} object
     */
    @SuppressWarnings("unchecked")
    public static BytesBiChannel<Socket> socket(Socket socket) {
        return proxy(new SocketResource(socket), BytesBiChannel.class);
    }

    /**
     * <p>
     * Read or write characters from the system console, if it exists.
     * </p>
     *
     * @return a {@link ca.rbon.iostream.channel.BytesBiChannel} object
     * @throws IOException if the {@link Console} cannot be opened
     */
    @SuppressWarnings("unchecked")
    public static CharBiChannel<Console> console() throws IOException {
        return proxy(new ConsoleResource(), CharBiChannel.class);
    }

    /**
     * <p>
     * Create a new Pipe of default size.
     * </p>
     *
     * @return a {@link ca.rbon.iostream.channel.BytesBiChannel} object
     */
    @SuppressWarnings("unchecked")
    public static BytesBiChannel<Pipe> pipe() {
        return proxy(new Pipe(), BytesBiChannel.class);
    }

    /**
     * <p>
     * Create a new Pipe of specified size.
     * </p>
     *
     * @param pipeSize size of the pipe
     * @return a {@link ca.rbon.iostream.channel.BytesBiChannel} object
     */
    @SuppressWarnings("unchecked")
    public static BytesBiChannel<Pipe> pipe(int pipeSize) {
        return proxy(new Pipe(pipeSize), BytesBiChannel.class);
    }

    /**
     * <p>
     * Write to an existing PipeInputStream.
     * </p>
     *
     * @param connect a {@link java.io.PipedInputStream} object.
     * @return a {@link ca.rbon.iostream.channel.BytesOutChannel} object
     */
    @SuppressWarnings("unchecked")
    public static BytesOutChannel<Pipe> pipe(PipedInputStream connect) {
        return proxy(new Pipe(connect), BytesOutChannel.class);
    }

    /**
     * <p>
     * Write to an existing Pipe.
     * </p>
     *
     * @param connect a {@link java.io.PipedInputStream} object.
     * @return a {@link ca.rbon.iostream.channel.BytesOutChannel} object
     * @throws IOException if the {@link Pipe} cannot be created
     */
    @SuppressWarnings("unchecked")
    public static BytesOutChannel<Pipe> pipe(InputStreamOf<Pipe> connect) throws IOException {
        return proxy(new Pipe(connect.getInner().getInputStream()), BytesOutChannel.class);
    }

    /**
     * Build a random byte input streamwith default seed.
     *
     * @return a {@link ca.rbon.iostream.channel.BytesInChannel} object
     */
    public static BytesInChannel<Random> random() {
        return random(new Random());
    }

    /**
     * Build a random byte input stream with specified seed.
     *
     * @param seed the seed
     * @return a {@link ca.rbon.iostream.channel.BytesInChannel} object
     */
    public static BytesInChannel<Random> random(long seed) {
        return random(new Random(seed));
    }

    /**
     * Build a random byte input stream with specified random.
     *
     * @param random the random
     * @return a {@link ca.rbon.iostream.channel.BytesInChannel} object
     */
    @SuppressWarnings("unchecked")
    public static BytesInChannel<Random> random(Random random) {
        return proxy(new InputStreamResource(new RandomInputStream(random)), BytesInChannel.class);
    }

    /**
     * Build a secure random byte input stream.
     *
     * @return a {@link ca.rbon.iostream.channel.BytesInChannel} object
     * @throws NoSuchAlgorithmException if no SecureRandom algorithm is available
     */
    public static BytesInChannel<Random> secureRandom() throws NoSuchAlgorithmException {
        return random(SecureRandom.getInstanceStrong());
    }

    /**
     * Build a secure random byte input stream with specified algorithm.
     *
     * @param algorithm the secure random algorithm
     * @return a {@link ca.rbon.iostream.channel.BytesInChannel} object
     * @throws NoSuchAlgorithmException if the SecureRandom algorithm is available
     */
    public static BytesInChannel<Random> secureRandom(String algorithm) throws NoSuchAlgorithmException {
        return random(SecureRandom.getInstance(algorithm));
    }

    /**
     * <p>
     * Wrap an existing output stream.
     * </p>
     * <p>
     * The existing stream <i>will</i> be closed by this wrapper.
     * </p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @return a {@link ca.rbon.iostream.channel.BytesOutChannel} object.
     */
    @SuppressWarnings("unchecked")
    public static BytesOutChannel<OutputStream> stream(OutputStream output) {
        return proxy(new OutputStreamResource(output), BytesOutChannel.class);
    }

    /**
     * <p>
     * Wrap an existing input stream.
     * </p>
     * <p>
     * The existing stream <i>will</i> be closed by this wrapper.
     * </p>
     *
     * @param input a {@link java.io.InputStream} object.
     * @return a {@link ca.rbon.iostream.channel.BytesInChannel} object.
     */
    @SuppressWarnings("unchecked")
    public static BytesInChannel<InputStream> stream(InputStream input) {
        return proxy(new InputStreamResource(input), BytesInChannel.class);
    }

    /**
     * <p>
     * Wrap an int supplier into an input stream.
     * </p>
     *
     * @param input a {@link IntSupplier} object.
     * @return a {@link ca.rbon.iostream.channel.BytesInChannel} object.
     */
    @SuppressWarnings("unchecked")
    public static BytesInChannel<InputStream> supplier(IntSupplier input) {
        return proxy(new InputStreamResource(new IntSupplierInputStream(input)), BytesInChannel.class);
    }

    /**
     * <p>
     * Wrap an int consumer into an output stream.
     * </p>
     *
     * @param output a {@link IntConsumer} object.
     * @return a {@link BytesOutChannel} object.
     */
    @SuppressWarnings("unchecked")
    public static BytesOutChannel<InputStream> consumer(IntConsumer output) {
        return proxy(new OutputStreamResource(new IntConsumerOutputStream(output)), BytesOutChannel.class);
    }

    /**
     * <p>
     * Read from a resource on the classpath using the default classloader.
     * </p>
     *
     * @param path The path to the resource.
     * @return a {@link ca.rbon.iostream.channel.BytesInChannel} object.
     */
    public static BytesInChannel<InputStream> classpath(String path) {
        var classloader = IO.class.getClassLoader();
        var resource = classloader.getResourceAsStream(path);
        return stream(resource);
    }

    @SuppressWarnings("unchecked")
    private static <T> T proxy(Resource<?> rez, Class<T> iface) {
        InvocationHandler h = new IoStreamBuilder(rez);
        return (T) Proxy.newProxyInstance(IO.class.getClassLoader(), new Class<?>[] { iface }, h);
    }

    @RequiredArgsConstructor
    static class IoStreamBuilder implements InvocationHandler {

        final Resource<?> rez;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object o = method.invoke(rez, args);
            if (o instanceof Resource) {
                // more to build
                return proxy;
            }
            // build complete
            return o;
        }
    }

}
