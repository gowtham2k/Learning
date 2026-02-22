# Autoboxing and Unboxing in Java

## 1. Introduction

In Java, every primitive data type has a corresponding Wrapper Class.

### Primitive → Wrapper Mapping

-   int → Integer
-   char → Character
-   double → Double
-   boolean → Boolean
-   long → Long
-   float → Float
-   byte → Byte
-   short → Short

Autoboxing and Unboxing were introduced in **Java 5**.

------------------------------------------------------------------------

## 2. What is Autoboxing?

**Autoboxing** is the automatic conversion of a primitive type into its
corresponding wrapper class object.

### Example

``` java
int x = 10;
Integer obj = x;   // Autoboxing
```

### What Compiler Actually Does

``` java
Integer obj = Integer.valueOf(x);
```

------------------------------------------------------------------------

## 3. What is Unboxing?

**Unboxing** is the automatic conversion of a wrapper class object into
its corresponding primitive type.

### Example

``` java
Integer obj = 10;
int x = obj;   // Auto-Unboxing
```

### What Compiler Actually Does

``` java
int x = obj.intValue();
```

------------------------------------------------------------------------

# 4. Example 1 -- Autoboxing and Auto-Unboxing

``` java
class Test {
    public static void main(String args[]) {

        int x1 = 10;

        Integer x2 = x1;   // Autoboxing

        int x3 = x2;       // Auto-Unboxing

        System.out.println(x1);
        System.out.println(x2);
        System.out.println(x3);
    }
}
```

## Step-by-Step Explanation

### Step 1

``` java
int x1 = 10;
```

-   `x1` is a primitive variable.
-   Value 10 is stored directly in stack memory.

### Step 2

``` java
Integer x2 = x1;
```

-   Primitive `int` is automatically converted into an `Integer` object.
-   Object is created in heap memory.
-   `x2` stores the reference (address) of that object.
-   This is called **Autoboxing**.

### Step 3

``` java
int x3 = x2;
```

-   The `Integer` object is converted back into primitive `int`.
-   Value is extracted using `.intValue()` internally.
-   This is called **Auto-Unboxing**.

### Output

    10
    10
    10

------------------------------------------------------------------------

# 5. Example 2 -- Wrapper Comparison

``` java
class Test {
    public static void main(String args[]) {

        Integer x1 = 400;
        Integer x2 = 400;

        if (x1 == x2)
            System.out.println("Same");
        else
            System.out.println("Not Same");
    }
}
```

## Output

    Not Same

------------------------------------------------------------------------

## Why Output is "Not Same"?

-   `Integer x1 = 400;`
-   `Integer x2 = 400;`
-   Two separate objects are created in heap.
-   `==` compares references (memory addresses), not values.
-   Since addresses are different → Output is `"Not Same"`.

------------------------------------------------------------------------

# 6. Integer Caching (Important Concept)

Java caches Integer values from **-128 to 127**.

### Example (Within Cache Range)

``` java
Integer a = 100;
Integer b = 100;

System.out.println(a == b);  // true
```

Reason: - Both refer to same cached object.

### Example (Outside Cache Range)

``` java
Integer a = 400;
Integer b = 400;

System.out.println(a == b);  // false
```

Reason: - 400 is outside cache range. - Two different objects are
created.

------------------------------------------------------------------------

# 7. Important Points

-   Autoboxing = Primitive → Wrapper
-   Unboxing = Wrapper → Primitive
-   Collections require wrapper classes.
-   `==` compares references for objects.
-   Use `.equals()` to compare values.

### Correct Way to Compare Values

``` java
System.out.println(x1.equals(x2));
```

------------------------------------------------------------------------

# 8. Summary

-   Java supports both primitive and object types.
-   Wrapper classes allow primitives to behave like objects.
-   Autoboxing and Unboxing are automatic conversions.
-   Be careful while comparing wrapper objects using `==`.
-   Understand Integer caching for interviews.

------------------------------------------------------------------------
