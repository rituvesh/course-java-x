# Java 9 Course

Teaches you the ins and outs of Java 9 (not the module system, though).
The links below take you to the demo in this project, the JEP responsible for introducing the feature, and to other sources if available.

## Setup

The scripts are written for Linux but should look similar on other operating systems.
To make sure, they execute the binaries from the correct Java version, take a look at `executables.sh` and set the correct paths.

## Language Changes

The language changes are rather small and there is no real point in doing any exercises - it suffices to take a look.

* [private interface methods](src/main/java/java9/lang/private_interface_methods/PrivateInterfaceMethods.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* [try-with-resources on effectively final variables](src/main/java/java9/lang/try_with_resources/TryWithResources.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* [diamond operator for anonymous classes](src/main/java/java9/lang/diamond_operator/DiamondOperator.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* [`@SaveVarargs` on private non-final methods](src/main/java/java9/lang/safe_varargs/SafeVarargs.java) ([JEP 213](http://openjdk.java.net/jeps/213))
* [no warnings for deprecated imports](src/main/java/java9/lang/deprecated_imports/DeprecatedImports.java) ([JEP 211](http://openjdk.java.net/jeps/211))

## APIs

* [`Stream` improvements](src/main/java/java9/api/stream)
* [`Optional` improvements](src/main/java/java9/api/optional)
* [collection factory methods](src/main/java/java9/api/collection_factory_methods/CollectionFactories.java) (instead of collection literals; [JEP 269](http://openjdk.java.net/jeps/269))
* [stack walking](src/main/java/java9/api/stack_walking/StackWalking.java) ([JEP 259](http://openjdk.java.net/jeps/259), [post on SitePoint](https://www.sitepoint.com/deep-dive-into-java-9s-stack-walking-api/) including benchmarks)

## JVM Features

* multi-release JARs ([classes](src/main/java/java9/jvm/multi_release) and [script](multi-release.sh))
* redirected platform logging ([classes](src/main/logging/java9/jvm/platform_logging) and [script](platform-logging.sh))
