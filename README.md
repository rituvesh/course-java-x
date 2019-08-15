# Java 9 to 12 Course

Teaches you the ins and outs of Java 9, 10, 11, and 12 (not the module system, though).
The links below take you to the exercises in this project, the JEP responsible for introducing the feature, and to other sources if available.

## Setup

### Install Recent Java

First, download and install [OpenJDK 12](http://jdk.java.net/12).

### Build And Run

#### From Your IDE

This is a Maven project, so importing it in your IDE should be straightforward.
You can then build and run individual classes.

#### From Command Line

Alternatively, you can use command line scripts.
They are written for Linux but should look similar on other operating systems.
(If you want to edit them or write a new script for your OS, check out [this cheat sheet](https://blog.codefx.org/cheat-sheet-build-run-modules-jars.html) for `javac`, `jar`, and `java`.)
To make sure they execute the binaries from the correct Java version, take a look at `executables.sh` and set the correct paths.

To compile most classes, run `compile.sh`.
(This does not include the more involved JVM features, which have separate scripts - see below.)
To run a single class, call `run.sh` with its name.
If you want to compile and run a single class, `compile-run.sh` shortcuts the two scripts.

## Exercises

All exercises are marked with `TASK`.
Have a look at the `solutions` branch if you're stuck.

### Java 9

#### Language Changes

The language changes are rather small and there is no real point in doing any exercises - it suffices to take a look.

* [private interface methods](src/main/java/org/codefx/courses/java9/lang/private_interface_methods/PrivateInterfaceMethods.java)
  ([JEP 213](http://openjdk.java.net/jeps/213))
* [try-with-resources on effectively final variables](src/main/java/org/codefx/courses/java9/lang/try_with_resources/TryWithResources.java)
  ([JEP 213](http://openjdk.java.net/jeps/213))
* [diamond operator for anonymous classes](src/main/java/org/codefx/courses/java9/lang/diamond_operator/DiamondOperator.java)
  ([JEP 213](http://openjdk.java.net/jeps/213))
* [`@SaveVarargs` on private non-final methods](src/main/java/org/codefx/courses/java9/lang/safe_varargs/SafeVarargs.java)
  ([JEP 213](http://openjdk.java.net/jeps/213))
* [no warnings for deprecated imports](src/main/java/org/codefx/courses/java9/lang/deprecated_imports/DeprecatedImports.java)
  ([JEP 211](http://openjdk.java.net/jeps/211))

#### APIs

* [`Stream` improvements](src/main/java/org/codefx/courses/java9/api/stream)
* [`Optional` improvements](src/main/java/org/codefx/courses/java9/api/optional)
* [collection factory methods](src/main/java/org/codefx/courses/java9/api/collection_factory_methods/CollectionFactories.java)
  (instead of collection literals; [JEP 269](http://openjdk.java.net/jeps/269))
* [stack walking](src/main/java/org/codefx/courses/java9/api/stack_walking/StackWalking.java)
  ([JEP 259](http://openjdk.java.net/jeps/259), [article](https://www.sitepoint.com/deep-dive-into-java-9s-stack-walking-api/) including benchmarks)
* [processes](src/main/java/org/codefx/courses/java9/api/processes),
  particularly [`TaskManager`](src/main/java/org/codefx/courses/java9/api/processes/TaskManager.java)
  ([JEP 102](http://openjdk.java.net/jeps/102))
* [reactive streams (flow)](src/main/java/org/codefx/courses/java9/api/flow)
  ([JEP 266](http://openjdk.java.net/jeps/266))

#### JVM Features

* multi-release JARs ([classes](src/main/java/org/codefx/courses/java9/jvm/multi_release) and [script](multi-release.sh);
  [JEP 238](http://openjdk.java.net/jeps/238),
  [article](https://blog.codefx.org/tools/multi-release-jars-multiple-java-versions/))
* unified logging ([script](unified-logging.sh);
  [JEP 158](http://openjdk.java.net/jeps/158),
  [article](https://blog.codefx.org/java/unified-logging-with-the-xlog-option/))
* redirected platform logging ([classes](src/main/logging/java9/jvm/platform_logging) and [script](platform-logging.sh);
  [JEP 264](http://openjdk.java.net/jeps/264))

### Java 10

#### Language Change

* [local-variable type inference with `var`](src/main/java/org/codefx/courses/java10/lang/var)
  ([JEP 286](http://openjdk.java.net/jeps/286),
  [article](https://blog.codefx.org/tag/var/) and
  [video](https://www.youtube.com/watch?v=Le1DbpRZdRQ&index=1&list=PL_-IO8LOLuNp2stY1qBUtXlfMdJW7wvfT))

#### JVM Features

* application class-data sharing ([script](app-cds.sh);
  [JEP 310](http://openjdk.java.net/jeps/310),
  [article](https://blog.codefx.org/java/application-class-data-sharing/))

### Java 11

#### APIs

* [`String` improvements](src/main/java/org/codefx/courses/java11/api/string/LinesStripRepeat.java)
* [reactive HTTP/2 client](src/main/java/org/codefx/courses/java11/api/http2/WikipediaSearch.java)
  (JEPs [110](http://openjdk.java.net/jeps/110) & [321](http://openjdk.java.net/jeps/321))

#### JVM Features

* [single-source-file execution](echo)
  ([JEP 330](https://openjdk.java.net/jeps/330),
  [article](https://blog.codefx.org/java/scripting-java-shebang))

### Java 12

#### Language

* [`switch` expression](src/main/java/org/codefx/courses/java12/lang/switch_)
  ([JEP 325](https://openjdk.java.net/jeps/325),
  [article](https://blog.codefx.org/java/switch-expressions) and
  [video](https://www.youtube.com/watch?v=1znHEf3oSNI&list=PL_-IO8LOLuNp2stY1qBUtXlfMdJW7wvfT))

#### APIs

* [teeing collector](src/main/java/org/codefx/courses/java12/api/stream/TeeingCollector.java)



# TODO

* add tests for reactive streams (and HTTP client?)
* incorrect error handling in reactive HTTP/2 solution
* streamline Collectors.teeing solution with `String::compareTo` as `Comparator<String>`
* add JAR for App CDS (using the project's JAR doesn't work because of preview features; or does it? it may have failed beause of Java 11)
