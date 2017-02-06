# iostreams
Better ergonomics for Java IO stream handling. 
* Single root class fluent builder. 
* Closes the stack of multiple closeable at once. 
* Allow access to the underlying resource through the top level facade.  

_"It's the little things"_

## Things you can do with it

Byte arrays, Files and Strings are currently implememted.  Non-deprecated Java classes semantics are preserved. 

```java
IoStreams.file("doum.txt").printWriter();
IoStreams.file("doum.txt").zipOutput().getSubject();
IoStreams.file("doum.txt").zipInput();
IoStreams.file("dam.txt", true).bufferedWriter();
IoStreams.file("doum.txt").bufferedInput();

IoStreams.tempFile().dataOutput();

IoStreams.string("agaga gogo").bufferedReader();
IoStreams.string("agaga gogo").reader();

IoStreams.string().bufferedWriter().getSubject();

IoStreams.bytes().dataOutput();
IoStreams.bytes().output();

IoStreams.bytes(new byte[] { 0, 1, 2 }).dataOutput();
IoStreams.bytes(new byte[] { 0, 1, 2 }).objectInput();
```

## FAQ
### Why not guava?
You mean [this](https://github.com/google/guava/wiki/IOExplained)?
Sure, but it's bigger and comes with its own classes and superseded the original Java classes.
Plus, I could not wrap my head around it in 10 seconds, which is all it should take for such a thing. So I spent 10 hours writing this lib instead.

### It's still hurts my eyes to read the code
Use another language. Java can be a bitch. 

### Why doesn't it support _$feature_
The goal is to support just Java stream classes (Sockets, Pipes) with their original semantics. No additional transitive deps. No fixing of the original sins. Some parts are still missing, but once it's done, it's done. You'll know because it'll be version 1.0.   

## Samples

### Standard comparison

IoStreams enabled-code 
```java
public byte[] usingIoStreams() throws IOException {
    PrintWriterProxy<byte[]> writer = IoStreams.bytes().printWriter();
    writer.write("doodoo");
    writer.close();
    return writer.getSubject();
}
```
  
Classic java streams 
```java
public byte[] classicStreams() throws IOException {
    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
    PrintWriter writer = new PrintWriter(byteOutputStream);
    writer.write("doodoo");
    writer.close();
    byteOutputStream.close();
    return byteOutputStream.toByteArray();
}
```

### Commented
IoStreams enabled-code 
```java
public byte[] fluent() throws IOException {
    // create and combine both objects
    PrintWriterProxy<byte[]> writer = IoStreams.bytes().printWriter();

    // write the string
    writer.write("doodoo");

    // close the writer and the inner stream at once
    writer.close();

    // extract result
    return writer.getSubject();
}
```
  
Classic java streams 
```java
public byte[] classic() throws IOException {
    // explicitly create inner stream to extract result
    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

    // create outer writer
    PrintWriter writer = new PrintWriter(byteOutputStream);

    // write a string
    writer.write("doodoo");

    // close the writer
    writer.close();

    // dont forget to close inner stream too
    byteOutputStream.close();

    // extract result from inner stream
    return byteOutputStream.toByteArray();
}
```

### Using try-with-resources
IoStreams enabled-code 
```java
try (PrintWriterProxy<byte[]> writer = IoStreams.bytes().printWriter()) {
    writer.write("doodoo");
    return writer.getSubject();
}
```
  
Classic java streams 
```java
try (ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
    PrintWriter writer = new PrintWriter(byteOutputStream)) {
    writer.write("doodoo");
    return byteOutputStream.toByteArray();
}
```
