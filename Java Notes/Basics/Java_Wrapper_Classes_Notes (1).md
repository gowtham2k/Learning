# Java Wrapper Classes

## 1. Introduction

In Java, data types are divided into two main categories:

-   **Primitive Types** -- Store actual values directly.
-   **Non-Primitive (Reference) Types** -- Store references (memory
    addresses) to objects.

Java is an Object-Oriented Programming (OOP) language. However,
primitive types are not objects. To bridge this gap, Java provides
**Wrapper Classes**.

------------------------------------------------------------------------

## 2. Primitive Types in Java

The 8 primitive types are:

-   byte
-   short
-   int
-   long
-   float
-   double
-   char
-   boolean

Example:

``` java
int x1 = 10;
```

Here, `x1` directly stores the value `10` in memory.

------------------------------------------------------------------------

## 3. What Are Wrapper Classes?

For every primitive type, Java provides a corresponding built-in class
known as a **Wrapper Class**.

These classes allow primitive values to be treated as objects.

### Primitive to Wrapper Mapping

  Primitive   Wrapper Class
  ----------- ---------------
  byte        Byte
  short       Short
  int         Integer
  long        Long
  float       Float
  double      Double
  char        Character
  boolean     Boolean

Note: - Wrapper class names start with a capital letter. - They are part
of `java.lang` package.

------------------------------------------------------------------------

## 4. Example of Wrapper Class Usage

``` java
int x1 = 10;          // Primitive variable
Integer x2 = 10;      // Wrapper class object
```

-   `x1` stores the value directly.
-   `x2` is a reference that points to an Integer object containing
    value `10`.

------------------------------------------------------------------------

## 5. Why Do We Need Wrapper Classes?

### 5.1 Collections Framework

Java Collections (like ArrayList, HashMap, HashSet) work only with
objects.

❌ Invalid:

``` java
ArrayList<int> list = new ArrayList<>();
```

Valid:

``` java
ArrayList<Integer> list = new ArrayList<>();
```

Generics require class types, not primitive types.

------------------------------------------------------------------------

### 5.2 Generics

Generics allow specifying a data type as a parameter.

Example:

``` java
ArrayList<Integer> numbers = new ArrayList<>();
```

Since generics work only with objects, wrapper classes are required.

------------------------------------------------------------------------

## 6. Why Does Java Still Have Primitive Types?

Primitive types are more efficient because:

-   They use less memory.
-   They are faster.
-   No object creation overhead.

Example:

``` java
int[] arr1 = new int[1000];        // Efficient
Integer[] arr2 = new Integer[1000]; // Creates 1000 objects (more memory usage)
```

Wrapper classes create objects, which adds extra overhead.

------------------------------------------------------------------------

## 7. Comparison with Python

-   In Python, everything is an object.
-   In Java, primitive types are not objects.
-   Wrapper classes help maintain object-oriented behavior when needed.

------------------------------------------------------------------------

## 8. Autoboxing and Unboxing

### Autoboxing

Automatic conversion of primitive to wrapper object.

``` java
Integer num = 5;  // int → Integer
```

### Unboxing

Automatic conversion of wrapper object to primitive.

``` java
int value = num;  // Integer → int
```

------------------------------------------------------------------------

## 9. Summary

-   Wrapper classes wrap primitive types into objects.
-   Required for Collections and Generics.
-   Primitive types are faster and memory efficient.
-   Wrapper classes provide object-oriented flexibility.

------------------------------------------------------------------------

### One-Line Definition

Wrapper classes are built-in Java classes that convert primitive data
types into objects so they can be used where objects are required.
