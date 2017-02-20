# IoStream

[![Build Status](https://travis-ci.org/fralalonde/iostream.svg?branch=master)](https://travis-ci.org/fralalonde/iostream)
[![Codecov](https://img.shields.io/codecov/c/github/fralalonde/iostream.svg)](https://codecov.io/gh/fralalonde/iostream)
[![Maven Central](https://img.shields.io/maven-central/v/ca.rbon/iostream.svg)](http://search.maven.org/#search%7Cga%7C1%7Crbon)
[![License: MPL 2.0](https://img.shields.io/badge/License-MPL%202.0-brightgreen.svg)](https://opensource.org/licenses/MPL-2.0)

Better ergonomics for Java IO streams building and disposal.
* Compact, fluent, safe builder for JDK InputStream, OutputStream, Reader and Writer classes
* Handles File, ByteArray, String, Socket, Pipe, Buffered, Zip, Console resources
* Base64 and GZip filters
* Retains default Java behavior and parameters  
* Composed objects are unambiguously closed as one
* Composed objects expose underlying resource for retrieval of results / follow up operations

```java
try (PrintWriterOf<File> pw = IoStream.file("myfile.txt").printWriter()) {
    File myFileTxt = pw.getResource();
    pw.write("Hello from file " + myFileTxt);
}        
```  

_"It's the little things"_

## What is this for?

Part of the original JDK 1.0, Java's original blocking I/O API celebrated it's 21st birthday on January 23, 2017. The _venerable_ Java "streams" classes are now of legal drinking age in most of the places they're allowed to go! And because of Java's insistence on backward compatibility, everything is still as it was back then. Everything but the rest of the world, that is. Ahem. Who feels like programming like it's 1996?

Mind you, there isn't much that is broken with the streams functionality. Sequential I/O is not something that changes much, if ever. It's just that the classes have become somewhat unwieldy when used alongside contemporary Java libraries. Let's give them a little makeover, shall we?

If you are using Maven, start by adding this snippet to your `pom.xml`

```xml
<dependency>
    <groupId>ca.rbon</groupId>
    <artifactId>iostream</artifactId>
    <version>0.8.0</version>
</dependency>
```

Sadly I do no have gradle at the ready, but I'm sure you smart foxes will know where to insert what I believe to be `ca.rbon:iostream:0.7.5`.

This library requires Java 1.8 (and nothing but).
      
## How does it work?

First, `IoStream` wraps the streams and their charset aware sibblings in a more palatable fluent-builder (or _Factory Method_, for you pattern freaks) so you don't have to `new` anything when you need to do some sweet blocking I/O. Start by typing `IoStream.` and autocomplete-away!
```java
import iostream.IoStream;
``` 

### Building
Because streams and readers/writers are often paired together, the fluent builder guides you from the selection of the underlying resource (`File`, `byte[]`, `Socket`...) to the way of accessing it (`BufferedStream`, `ZipStream`, `Writer`...). 
```java
PrintWriter writer = IoStream.file("yesss.txt").printWriter();
```
You will notice that `printWriter()` above actually returns something called `PrintWriterOf<File>`. This is a subclass of the standard `java.io.PrintWriter` that  can be safely use as such. All `IoStream` final methods return `*Of<File>` classes extending normal `java.io` classes. Their added functionality is explained below (hint: checkout their `getResource()` method.).

If a resource only implements streams (like `socket()`), and you require `Writer` or `Reader` char-oriented access, the builder will transparently insert an `OutputStreamWriter` or `InputStreamReader` adapter for you.
```java
BufferedWriter writer = IoStream.socket("spamaway.net", 25).bufferedWriter();
``` 

### try-with syntax
Obviously, `IoStream` goes hand in hand with the try-with syntax introduced in JDK 7.
```java
try (Writer w = IoStream.file("mouha.txt").printWriter()) {
    w.append("haha");
}
```

### Retrieving results using `*Of<>` classes 
It is often required to access the resource after all streams are closed in order to obtain the final operation result. This is a pattern especially common with auto-instantiated resources such as byte arrays out streams, string writers or temp files. `IoStream` makes the job easier by allowing access to the resulting resource from the outmost object. The only catch is that you need to use IoStream's `Of<>` classes, which all subclass regular `java.io` classes. This done to provide you with the `getResource()` method, which return type follows the resource type you built.
```java
PrintWriterOf<byte[]> writer = IoStream.bytes().printWriter();
// ...
writer.close();
byte[] myPrecious = writer.getResource();
```
The classic JDK code forces you to juggle with the inner and outer streams. 
```java
ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
Writer writer = new PrintWriter(byteArrayOutputStream);
// ...
writer.close();
byte[] myPrecious = byteArrayOutputStream.toByteArray();
```

## Companion libraries

`IoStream` is an excellent companion to [Apache Commons IO's](https://commons.apache.org/proper/commons-io/javadocs/api-2.4/index.html?org/apache/commons/io/IOUtils.html) `IOUtils` class. There is little overlap between the libraries. `IoStream` helps to build the streams while `IOUtils` takes care of the operations, such as `copy()`.


## Things you can do with it

### Convert to IntStream

```java
try (InputStreamOf<File> pw = IoStream.file("numbers.bin").inputStream()) {
    pw.intStream().sum();
}
```

### Files
```java
FileOutputStream out = IoStream.file("noooes.txt").outputStream();

try (PrintWriter w = IoStream.file("mouha.txt").printWriter()) {
    w.append("haha");
}   

IoStream.file("doum.zip").zipInputStream("UTF-8");
IoStream.file("dam.txt", true).bufferedWriter();

DataOutputOf<File> tmpout = IoStream.tempFile().dataOutputStream();
tmpout.write(42);
String tmpFilename = tmpout.getResource().getAbsolutePath();
```

### Strings
```java
IoStream.string("agaga gogo").bufferedReader();
IoStream.string("agaga gogo").reader();

String str = IoStream.string().bufferedWriter().getResource();
```

### Byte Arrays
```java
IoStream.bytes().outputStream();
byte[] bytes = IoStream.bytes().dataOutputStream().getResource();

IoStream.bytes(new byte[] { 0, 1, 2 }).objectInputStream();
```

### Sockets
```java
IoStream.socket("gloogloo.com", 80).bufferedOutputStream();
InputStream smtpHoneypot = IoStream.socket("localhost", 25).inputStream(); 
```

### Random and IntStream
```java
try (InputStreamOf<Random> pw = IoStream.random().inputStream()) {
	// add 5 random numbers
    pw.intStream().limit(5).sum();
}
```

### Existing input and output streams integration
```java
InputStream providedInput = new ByteArrayInputStream(new byte[7]);
IoStream.stream(providedInput).gzipInputStream();

OutputStream providedOutput = new ByteArrayOutputStream();
IoStream.stream(providedOutput).printWriter(256);
```

### Writing text to a File, GZipped, Base64, UTF-16 encoded. 256-byte buffer, autoflush.
```java
// this example is pretty extreme
try (PrintWriterOf<File> pw = IoStream.file("myfile.txt").base64().gzip(55).printWriter("UTF-16", 256, true)) {
    File myFileTxt = pw.getResource();
    pw.write("Hello from file " + myFileTxt.getName());
}
```

## FAQ
### Why not guava?
You mean [this](https://github.com/google/guava/wiki/IOExplained)?
Sure, but it's bigger and comes with its own classes and superseded the original Java classes.
Plus, I could not wrap my head around it in 10 seconds, which is all it should take for such a thing. So I spent 10 hours writing this lib instead.

### But my eyes are _still_ bleeding!
Hey, Java can be a pain. We could push it further with compiler @Annotations or dynamically generated bytecode, but then we'd have a different set of problems. [Why](https://www.rust-lang.org/) [don't](https://clojure.org/) [you](https://www.ceylon-lang.org/) [use](https://nim-lang.org/) [a](http://fsharp.org/) [different](http://www.dangermouse.net/esoteric/piet.html) [language](http://lolcode.org/)?     

### Why doesn't `IoStream` support _$feature_
The goal is to support just Java stream classes (Sockets, Pipes) with their original semantics. No additional transitive deps. No fixing of the original sins. Some parts are still missing, but once it's done, it's done. You'll know because it'll be version 1.0.   

## Samples

### Standard comparison

IoStream enabled-code 
```java
public byte[] usingIoStream() throws IOException {
    PrintWriterOf<byte[]> writer = IoStream.bytes().printWriter();
    writer.write("doodoo");
    writer.close();
    return writer.getResource();
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
IoStream enabled-code 
```java
public byte[] fluent() throws IOException {
    // create and combine both objects
    PrintWriterOf<byte[]> writer = IoStream.bytes().printWriter();

    // write the string
    writer.write("doodoo");

    // close the writer and the inner stream at once
    writer.close();

    // extract result
    return writer.getResource();
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
IoStream enabled-code 
```java
try (PrintWriterOf<byte[]> writer = IoStream.bytes().printWriter()) {
    writer.write("doodoo");
    return writer.getResource();
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
