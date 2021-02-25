# IoStream

[![Build Status](https://travis-ci.org/fralalonde/IO.svg?branch=master)](https://travis-ci.org/fralalonde/iostream)
[![Codecov](https://img.shields.io/codecov/c/github/fralalonde/IO.svg)](https://codecov.io/gh/fralalonde/iostream)
[![Maven Central](https://img.shields.io/maven-central/v/ca.rbon/IO.svg)](http://search.maven.org/#search%7Cga%7C1%7Crbon)
[![License: MPL 2.0](https://img.shields.io/badge/License-MPL%202.0-brightgreen.svg)](https://opensource.org/licenses/MPL-2.0)
[![Javadocs](http://www.javadoc.io/badge/ca.rbon/IO.svg)](http://www.javadoc.io/doc/ca.rbon/iostream)
[![Dependencies](https://www.versioneye.com/user/projects/58b8255d01b5b7003d6201bc/badge.svg)](https://www.versioneye.com/user/projects/58b8255d01b5b7003d6201bc?child=summary)

Better code handling of Java IO streams.

* Fluent builder for Java standard InputStream, OutputStream, Reader and Writer and Filter classes
* Provides File, ByteArray, String, Socket, Pipe, Buffered, Zip, Console and generic stream wrapper resources
* Supports Base64, GZip and Cipher filters
* Retains default Java behavior and parameters  
* Composed objects are unambiguously closed as one
* Composed objects expose underlying resource for retrieval of results / follow up operations

```java
try (PrintWriterOf<File> fileWriter = IO.file("myfile.gz").gzip().printWriter()) {
    File theFile = fileWriter.get();
    fileWriter.write("Hello from file " + theFile.getName());
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
    <version>0.9.1</version>
</dependency>
```

Sadly I do no have gradle at the ready, but I'm sure you smart foxes will know where to 
insert what I believe to be `ca.rbon:iostream:0.9.1`.

Because it uses default interface methods, this library requires Java 1.8 (and nothing but).
      
## How does it work?

First, `IoStream` wraps the streams and their charset aware sibblings in a more palatable 
fluent-builder (or _Factory Method_, for you pattern freaks) so you don't have to `new` anything 
when you need to do some sweet blocking I/O. 
```java
import ca.rbon.iostream.IO;
``` 
Then start by typing `IO.` and autocomplete-away!

### Building
Because streams and readers/writers are often paired together, 
the fluent builder guides you from the selection of the underlying resource (`File`, `byte[]`, `Socket`...) 
to the way of accessing it (`BufferedStream`, `ZipStream`, `Writer`...). 
```java
PrintWriter writer = IO.file("yesss.txt").printWriter();
```
You will notice that `printWriter()` above actually returns something called `PrintWriterOf<File>`. 
This is a subclass of the standard `java.io.PrintWriter` that  can be safely use as such. 
All `IoStream` final methods return `*Of<File>` classes extending normal `java.io` classes. 
Their added functionality is explained below (hint: checkout their `getInner()` method.).

If a resource only implements streams (like `socket()`), and you require `Writer` or `Reader` 
char-oriented access, the builder will transparently insert an `OutputStreamWriter` or `InputStreamReader` 
adapter for you.
```java
BufferedWriter writer = IO.socket("spamaway.net", 25).bufferedWriter();
``` 

### try-with syntax
Obviously, `IoStream` goes hand in hand with the try-with syntax introduced in JDK 7.
```java
try (Writer w = IO.file("mouha.txt").printWriter()) {
    w.append("haha");
}
```

### Retrieving inner results 
It is often required to access the resource after all streams are closed in order to obtain the 
final operation result. This is a pattern especially common with auto-instantiated resources such as byte 
arrays out streams, string writers or temp files.

`IoStream` makes the job easier by allowing access to the resulting resource straight from the outmost object. 
This is done magically with IoStream's `*Of<T>` classes, which subclass regular `java.io` classes.
Because we are modern, sane people, we can ignore this detail by using the `var` keyword from JDK 10+.
 Then all you have to do is call the `getInner()` method to retrieve the inner resource you just built.
```java
var writer = IO.bytes().printWriter();
// ...
writer.close();
byte[] myPrecious = writer.getInner();
```
By comparison, classic JDK code forces you to juggle with the inner and outer streams: 
```java
ByteArrayOutputStream innerStream = new ByteArrayOutputStream();
Writer outerStream = new PrintWriter(innerStream);
// ...
outerStream.close();
byte[] myPrecious = innerStream.toByteArray();
```

## Companion libraries

`IoStream` is an excellent companion to Apache Commons IO's 
[IOUtils class](https://commons.apache.org/proper/commons-io/javadocs/api-2.4/index.html?org/apache/commons/io/IOUtils.html). 
There is little overlap between the libraries. 
`IoStream` helps to build the streams while `IOUtils` takes care of the operations, such as `copy()` :

```java
try (var in = IO.file("A").reader(); var out = IO.file("B").writer()) {
    IOUtils.copy(in, out);
}
```

## Things you can do with it

### Convert to IntStream

```java
try (var pw = IO.file("numbers.bin").inputStream()) {
    pw.intStream().sum();
}
```

### Byte Arrays, Random & IOUtils 
```java
IO.bytes().outputStream();
byte[] bytes = IO.bytes().dataOutputStream().getInner();

IO.bytes(new byte[] { 0, 1, 2 }).objectInputStream();
```

### Files
```java
FileOutputStream out = IO.file("noooes.txt").outputStream();

try (var myFile = IO.file("mouha.txt").printWriter()) {
    myFile.write("haha");
}
   

IO.file("doum.zip").zipInputStream("UTF-8");
IO.file("dam.txt", true).bufferedWriter();

var tmpout = IO.tempFile().dataOutputStream();
tmpout.write(42);
String tmpFilename = tmpout.getInner().getAbsolutePath();
```

### Strings
```java
IO.string("agaga gogo").bufferedReader();
IO.string("agaga gogo").reader();

String str = IO.string().bufferedWriter().getInner();
```

### Sockets
```java
IO.socket("gloogloo.com", 80).bufferedOutputStream();
InputStream smtpHoneypot = IO.socket("localhost", 25).inputStream(); 
```

### Pipe
```java
IO.socket("gloogloo.com", 80).bufferedOutputStream();
InputStream smtpHoneypot = IO.socket("localhost", 25).inputStream(); 
```

### Random 
```java
try (var pw = IO.random().inputStream()) {
	// add 5 random numbers
    pw.intStream().limit(5).sum();
}
```

### Existing input and output streams integration
```java
InputStream providedInput = new ByteArrayInputStream(new byte[7]);
IO.stream(providedInput).gzipInputStream();

OutputStream providedOutput = new ByteArrayOutputStream();
IO.stream(providedOutput).printWriter(256);
```

### Writing text to a File, GZipped, Base64, UTF-16 encoded. 256-byte buffer, autoflush.
```java
// this example is pretty extreme
try (var pw = IO.file("myfile.txt").base64().gzip(55).printWriter("UTF-16", 256, true)) {
    File myFileTxt = pw.getInner();
    pw.write("Hello from file " + myFileTxt.getName());
}
```

## FAQ
### Why not guava?
You mean [this](https://github.com/google/guava/wiki/IOExplained)?
Sure, but it's bigger and comes with its own classes and superseded the original Java classes.
Plus, I could not wrap my head around it in 10 seconds, which is all it should take for such a thing. 
So I spent 10 hours writing this lib instead.

### But my eyes are _still_ bleeding!
Hey, Java can be a pain. We could push it further with compiler @Annotations or dynamically generated bytecode, 
but then we'd have a different set of problems. 
[Why](https://www.rust-lang.org/) 
[don't](https://clojure.org/) 
[you](https://www.ceylon-lang.org/) 
[try](https://nim-lang.org/) 
[a](http://fsharp.org/) 
[different](http://www.dangermouse.net/esoteric/piet.html) 
[language](http://lolcode.org/)?     

### Why doesn't `IoStream` support _$feature_
The goal is to support just Java stream classes (Sockets, Pipes) with their original semantics. 
No additional transitive deps. No fixing of the original sins. 
Some parts are still missing, but once it's done, it's done. You'll know because it'll be version 1.0.   

## Samples

### Standard comparison

IoStream enabled-code 
```java
public byte[] usingIoStream() throws IOException {
    var writer = IO.bytes().printWriter();
    writer.write("doodoo");
    writer.close();
    return writer.getInner();
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
    var writer = IO.bytes().printWriter();

    // write the string
    writer.write("doodoo");

    // close the writer and the inner stream at once
    writer.close();

    // extract result
    return writer.getInner();
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
try (var writer = IO.bytes().printWriter()) {
    writer.write("doodoo");
    return writer.getInner();
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
