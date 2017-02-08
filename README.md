# IoStream
Better ergonomics for Java IO stream handling. 
* Single root class fluent builder. 
* Closes the stack of multiple closeable at once. 
* Allow access to the underlying resource through the top level facade.  

_"It's the little things"_

## What is this for?

Part of the original JDK 1.0, Java's original blocking I/O API celebrated it's 21st birthday on January 23, 2017. The _venerable_ Java "streams" classes are now of legal drinking age in most of the places they're allowed to go! And because of Java's insistence on backward compatibility, everything is still as it was back then. Everything but the rest of the world, that is. Ahem. Who feels like programming like it's 1996?

Mind you, there isn't much that is broken with the streams functionality. Sequential I/O is not something that changes much, if ever. It's just that the classes have become somewhat unwieldy when used alongside contemporary Java libraries. Let's give them a little makeover, shall we?

If you are using Maven, start by adding this snippet to your `pom.xml`

```xml
<dependency>
    <groupId>ca.rbon</groupId>
    <artifactId>iostream</artifactId>
    <version>0.2-SNAPSHOT</version>
</dependency>
```

Sadly I do no have gradle at the ready, but I'm sure you smart foxes will know where to insert what I believe to be `ca.rbon:iostream:0.2-SNAPSHOT`.  
      
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
You can also build resource-native classes such as `FileOutputStream` if that's all you need and the resource provides it "natively", i.e. JDK has a class supporting that case.
```java
FileOutputStream out = IoStream.file("noooes.txt").outputStream();
``` 
If a resource only implements streams (like `socket()`), and you require `Writer` or `Reader` char-oriented access, the builder will transparently insert an `OutputStreamWriter` or `InputStreamReader` adapter for you.
```java
BufferedWriter writer = IoStream.socket("spamaway.net", 25).bufferedWriter();
``` 

### Closing
Next, when you're done reading or writing, you need to `close()` streams and readers and writers that were created. This is where `IoStream` shines compared to good ol'Java. Closing the single construct that was returned by IoStream will close all objects that were created in one swoop, from the outmost object down to the inner resource. 
```java
Writer writer = IoStream.file("a.txt").printWriter();
// ... 
writer.close();
``` 
Compare this to the old way, where you have to track and dispose of any intermediate object created, an then close them _in the correct order_.
```java
Writer writer1 = new FileWriter("a.txt");
Writer writer2 = new BufferedWriter(writer1);
// ... 
writer2.close();
writer1.close();
```
Not only is the `IoStream` code prettier, it's also safer.   

### try-with syntax
Obviously, `IoStream` goes hand in hand with the try-with syntax introduced in JDK 7.
```java
try (Writer w = IoStream.file("mouha.txt").printWriter()) {
    w.append("haha");
}
```

### Retrieving results 
Finally, it is often required to access the resource after all streams are closed in order to obtain the final operation result. This is a pattern especially common with auto-instantiated resources such as byte arrays or temp files. Then again, `IoStream` makes the job easier by allowing access to the resulting resource from the outmost object. The only catch is that you need to use IoStream's `Proxy<>` classes, all of which actually subclass the JDK own classes. This done to provide you with the  `getResource()` method, which return type follows the resource type you built.
```java
PrintWriterProxy<byte[]> writer = IoStream.bytes().printWriter();
// ...
writer.close();
byte[] myPrecious = writer.getResource();
```
As with `close()`, in this case, the classic JDK code forces you to juggle with the inner and outer streams. 
```java
ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
Writer writer = new PrintWriter(byteArrayOutputStream);
// ...
writer.close();
byteArrayOutputStream.close();
byte[] myPrecious = byteArrayOutputStream.toByteArray();
```

## Things you can do with it

Byte arrays, Files and Strings and Sockets are currently implemented. Non-deprecated Java classes semantics are preserved. Future version should add `Pipe`, `Random`,  `Console` and `Streams` resources, the last one being for decorating streams provided by other libraries. 

### Files
```java
FileOutputStream out = IoStream.file("noooes.txt").outputStream();

try (PrintWriter w = IoStream.file("mouha.txt").printWriter()) {
    w.append("haha");
}   

IoStream.file("doum.zip").zipInputStream(Charset.forName("UTF-8"));
IoStream.file("dam.txt", true).bufferedWriter();

DataOutputProxy<File> tmpout = IoStream.tempFile().dataOutputStream();
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

## FAQ
### Why not guava?
You mean [this](https://github.com/google/guava/wiki/IOExplained)?
Sure, but it's bigger and comes with its own classes and superseded the original Java classes.
Plus, I could not wrap my head around it in 10 seconds, which is all it should take for such a thing. So I spent 10 hours writing this lib instead.

### But my eyes are _still_ bleeding!
Hey, Java can be a bitch. We could push it further with compiler @Annotations or dynamically generated bytecode, but then we'd have a different set of problems. [Why](https://www.rust-lang.org/) [don't](https://clojure.org/) [you](https://www.ceylon-lang.org/) [use](https://nim-lang.org/) [a](http://fsharp.org/) [different](http://www.dangermouse.net/esoteric/piet.html) [language](http://lolcode.org/)?     

### Why doesn't `IoStream` support _$feature_
The goal is to support just Java stream classes (Sockets, Pipes) with their original semantics. No additional transitive deps. No fixing of the original sins. Some parts are still missing, but once it's done, it's done. You'll know because it'll be version 1.0.   

## Samples

### Standard comparison

IoStream enabled-code 
```java
public byte[] usingIoStream() throws IOException {
    PrintWriterProxy<byte[]> writer = IoStream.bytes().printWriter();
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
    PrintWriterProxy<byte[]> writer = IoStream.bytes().printWriter();

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
try (PrintWriterProxy<byte[]> writer = IoStream.bytes().printWriter()) {
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
