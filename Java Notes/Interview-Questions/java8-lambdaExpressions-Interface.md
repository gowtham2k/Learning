# Java 8, Lambda Expressions, Exception Handling & Interfaces — Interview Q&A

> **Structure:** Definition → Example → Real-World Analogy

---

## 1. Java 8 Features

---

### Q1. What are the major features introduced in Java 8?

**Definition:**
Java 8 introduced several powerful features that modernized the language — making code more concise, functional, and expressive.

**Key Features:**
- Lambda Expressions
- Functional Interfaces
- Stream API
- Optional class
- Default and Static methods in Interfaces
- Method References
- New Date/Time API (`java.time`)
- `forEach()` method
- `StringJoiner`

**Example:**
```java
// Before Java 8
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
for (String name : names) {
    System.out.println(name);
}

// Java 8 way
names.forEach(name -> System.out.println(name));
```

**Real-World Analogy:**
Think of Java 8 as a smartphone upgrade. Your old phone could make calls and send texts. The new one still does that, but now adds a camera, GPS, and apps — it's the same phone, just far more powerful.

---

### Q2. What is a Lambda Expression?

**Definition:**
A Lambda Expression is a short, anonymous function — a block of code with no name that can be passed around like a value.

**Syntax:**
```
(parameters) -> { body }
```

**Example:**
```java
// Without lambda
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Running!");
    }
};

// With lambda
Runnable r = () -> System.out.println("Running!");
```

**Real-World Analogy:**
Instead of writing a full recipe (define a class, implement a method), you just write a sticky note: *"Boil water for 5 minutes."* That sticky note is the lambda — quick, direct, and to the point.

---

### Q3. What is a Functional Interface?

**Definition:**
A Functional Interface is an interface that has **exactly one abstract method**. It serves as the target type for lambda expressions.

**Example:**
```java
@FunctionalInterface
interface Greet {
    void sayHello(String name);
}

Greet g = name -> System.out.println("Hello, " + name);
g.sayHello("Alice"); // Hello, Alice
```

**Real-World Analogy:**
A job description with one specific role — like a "Photographer". You hire someone (lambda) for that one job. If the job description had 10 roles, you couldn't assign just one person so easily.

---

### Q4. What is the `@FunctionalInterface` annotation?

**Definition:**
`@FunctionalInterface` is a marker annotation that tells the compiler: *"This interface must have exactly one abstract method."* If you accidentally add a second abstract method, the compiler throws an error.

**Example:**
```java
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
    // int anotherMethod(int a); // ❌ Compile error
}
```

**Real-World Analogy:**
It's like putting a "One Cook Only" sign in the kitchen. The sign doesn't cook anything — it just enforces the rule that only one chef is responsible.

---

### Q5. What is the Stream API?

**Definition:**
The Stream API is a way to process collections of data in a declarative, pipeline-style fashion — supporting operations like filter, map, sort, and collect.

**Example:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());

System.out.println(evens); // [2, 4, 6]
```

**Real-World Analogy:**
Think of a conveyor belt in a factory. Items go in, each station does one thing (filter, transform, pack), and a finished product comes out at the end. The stream is that belt.

---

### Q6. What is the difference between Collection and Stream?

**Definition:**

| Feature | Collection | Stream |
|---|---|---|
| Storage | Stores data | Processes data |
| Reusable | Yes | No (consumed once) |
| Iteration | External (you control loop) | Internal (stream handles it) |
| Modification | Can add/remove elements | Cannot modify source |
| Lazy evaluation | No | Yes |

**Example:**
```java
List<String> list = Arrays.asList("a", "b", "c"); // Collection
Stream<String> stream = list.stream();             // Stream from collection
stream.forEach(System.out::println);
// stream.forEach(...) again → ❌ IllegalStateException (stream already consumed)
```

**Real-World Analogy:**
A Collection is a **warehouse** — you store goods, go back, add more, remove some. A Stream is a **pipeline** — goods flow through once, getting processed, and you can't reuse the pipeline after the goods have passed through.

---

### Q7. What is the `Optional` class in Java 8?

**Definition:**
`Optional<T>` is a container object that may or may not contain a value. It's used to avoid `NullPointerException` by explicitly handling the possibility of absence.

**Example:**
```java
Optional<String> name = Optional.ofNullable(null);

// Without Optional
if (name != null) {
    System.out.println(name.toUpperCase());
}

// With Optional
name.ifPresent(n -> System.out.println(n.toUpperCase()));

String result = name.orElse("Default Name");
System.out.println(result); // Default Name
```

**Real-World Analogy:**
It's like a gift box that might be empty. Instead of tearing it open and being surprised it's empty (`NullPointerException`), Optional lets you check: *"Is there something inside?"* before you try to use it.

---

### Q8. What is Method Reference?

**Definition:**
Method Reference is a shorthand notation for a lambda that simply calls an existing method. It uses the `::` operator.

**Syntax:**
```
ClassName::methodName
```

**Example:**
```java
// Lambda
List<String> names = Arrays.asList("Alice", "Bob");
names.forEach(name -> System.out.println(name));

// Method reference (cleaner)
names.forEach(System.out::println);
```

**Real-World Analogy:**
Instead of saying *"Take the document and hand it to the printer"*, you just say *"Give it to the printer."* Method reference removes the redundant middle step.

---

### Q9. What are the types of Method References?

**Definition:**
There are 4 types:

| Type | Syntax | Example |
|---|---|---|
| Static method | `ClassName::staticMethod` | `Math::abs` |
| Instance method (specific object) | `object::instanceMethod` | `str::toUpperCase` |
| Instance method (arbitrary object) | `ClassName::instanceMethod` | `String::toLowerCase` |
| Constructor | `ClassName::new` | `ArrayList::new` |

**Example:**
```java
// 1. Static method reference
Function<Double, Double> abs = Math::abs;

// 2. Instance method on specific object
String str = "hello";
Supplier<String> upper = str::toUpperCase;

// 3. Instance method on arbitrary object
Function<String, String> lower = String::toLowerCase;

// 4. Constructor reference
Supplier<List<String>> listMaker = ArrayList::new;
```

**Real-World Analogy:**
Like speed-dialing. Instead of typing a full number, you press 1 for Mom, 2 for Work, etc. Each method reference type is a different kind of speed-dial shortcut.

---

### Q10. What are Default Methods in Interfaces?

**Definition:**
Default methods are methods in an interface that have a body (implementation). They were introduced in Java 8 so that new methods could be added to interfaces without breaking existing implementing classes.

**Example:**
```java
interface Vehicle {
    void start();

    default void horn() {
        System.out.println("Beep beep!");
    }
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car started");
    }
    // horn() is inherited from Vehicle
}

Car c = new Car();
c.horn(); // Beep beep!
```

**Real-World Analogy:**
Think of a TV remote that gets a software update. All existing remotes still work, but now they also have a new "Netflix" button by default — no one had to rebuild their remote.

---

### Q11. What are Static Methods in Interfaces?

**Definition:**
Static methods in interfaces belong to the interface itself — not to implementing classes. They cannot be overridden and are called using the interface name.

**Example:**
```java
interface MathUtils {
    static int square(int n) {
        return n * n;
    }
}

System.out.println(MathUtils.square(4)); // 16
// Cannot call: new MyClass().square(4) ❌
```

**Real-World Analogy:**
Like a utility room in a building. The tools there belong to the building, not to any individual tenant. Tenants can use them but can't take them home or modify them.

---

### Q12. What is the `forEach()` method?

**Definition:**
`forEach()` is a default method in `Iterable` (and also in `Stream`) that iterates over each element and performs an action (defined by a lambda or method reference).

**Example:**
```java
List<String> fruits = Arrays.asList("Apple", "Mango", "Banana");

// forEach with lambda
fruits.forEach(fruit -> System.out.println(fruit));

// forEach with method reference
fruits.forEach(System.out::println);
```

**Real-World Analogy:**
Like a teacher calling roll — they go through each student's name one by one and perform the same action (mark attendance). `forEach()` does that automatically without you writing a for-loop.

---

### Q13. What is the difference between `map()` and `flatMap()`?

**Definition:**
- **`map()`** — Transforms each element to one output. One-to-one.
- **`flatMap()`** — Transforms each element to a stream, then flattens all streams into one. One-to-many, then flatten.

**Example:**
```java
// map() - wraps each element
List<String> names = Arrays.asList("Alice", "Bob");
names.stream()
     .map(String::toUpperCase)
     .forEach(System.out::println);
// ALICE, BOB

// flatMap() - flattens nested structure
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4)
);
nested.stream()
      .flatMap(List::stream)
      .forEach(System.out::println);
// 1, 2, 3, 4
```

**Real-World Analogy:**
`map()` is like putting each book in its own box. `flatMap()` is like taking all books out of all boxes and putting them on one shelf — you flatten the nested structure.

---

### Q14. What is `filter()` in Stream API?

**Definition:**
`filter()` is an intermediate operation that takes a Predicate and keeps only the elements that satisfy the given condition.

**Example:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

numbers.stream()
       .filter(n -> n > 4)
       .forEach(System.out::println);
// 5, 6, 7, 8
```

**Real-World Analogy:**
Like a security checkpoint — only people with a valid pass (condition = true) get through. Everyone else is filtered out.

---

### Q15. What is the `reduce()` method?

**Definition:**
`reduce()` is a terminal operation that combines all elements of a stream into a single result using a BinaryOperator.

**Example:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

int sum = numbers.stream()
                 .reduce(0, (a, b) -> a + b);

System.out.println(sum); // 15
```

**Real-World Analogy:**
Like totaling a grocery bill — you start at 0, add item 1 (running total: 5), add item 2 (running total: 12), and so on until you have one final number.

---

---

## 2. Lambda Expressions

---

### Q16. What is a Lambda Expression? *(Deep Dive)*

**Definition:**
A Lambda Expression is an anonymous function — it has no name, no access modifier, no return type declared. It implements a functional interface's single abstract method inline.

**Example:**
```java
// Full anonymous class
Comparator<String> comp = new Comparator<String>() {
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
};

// Lambda equivalent
Comparator<String> comp = (a, b) -> a.compareTo(b);
```

**Real-World Analogy:**
Writing a one-time instruction on a sticky note vs. drafting a formal 5-page memo. Both communicate the same thing — the sticky note is just faster and lighter.

---

### Q17. Why were Lambda Expressions introduced in Java?

**Definition:**
Lambdas were introduced to:
1. Enable **functional programming** style in Java
2. Reduce **boilerplate code** (no more verbose anonymous classes)
3. Make it easy to use the **Stream API** and **Collections API**
4. Enable **parallel processing** more cleanly

**Example:**
```java
// Before Java 8 - verbose
Collections.sort(names, new Comparator<String>() {
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
});

// Java 8 - clean
Collections.sort(names, (a, b) -> a.compareTo(b));
```

**Real-World Analogy:**
Before email, you sent a physical letter to give someone a simple answer. Lambdas are like email — same message, far less overhead.

---

### Q18. What is the Syntax of a Lambda Expression?

**Definition:**
```
(parameter list) -> { body }
```
- Parentheses can be omitted if there's one parameter
- Curly braces can be omitted if body is a single expression
- `return` keyword can be omitted for single-expression bodies

**Example:**
```java
// No parameters
() -> System.out.println("Hello")

// One parameter (parentheses optional)
x -> x * x

// Multiple parameters
(a, b) -> a + b

// Multi-line body
(a, b) -> {
    int sum = a + b;
    return sum;
}
```

**Real-World Analogy:**
Like a text message format: short messages drop the greeting. *"ok"* vs. *"Dear John, I acknowledge your inquiry and respond: ok. Regards, Alice."* — same info, different verbosity.

---

### Q19. Can a Functional Interface have Multiple Methods?

**Definition:**
A Functional Interface can have:
- **Exactly one abstract method** (mandatory)
- **Any number of default methods** (allowed)
- **Any number of static methods** (allowed)
- Methods inherited from `Object` class (like `equals`, `toString`) don't count

**Example:**
```java
@FunctionalInterface
interface MyInterface {
    void doWork();             // ✅ One abstract method

    default void log() {       // ✅ Default method allowed
        System.out.println("Logging...");
    }

    static void help() {       // ✅ Static method allowed
        System.out.println("Help!");
    }

    // void anotherAbstract(); // ❌ Would break functional interface
}
```

**Real-World Analogy:**
A job description for a "Driver" has one core duty: drive. But the job may also come with optional perks (default methods) and company policies (static methods). The one core duty is what defines the role.

---

### Q20. What are Built-in Functional Interfaces in Java?

**Definition:**
Java 8 provides ready-made functional interfaces in `java.util.function` package.

---

#### `Predicate<T>`
Takes one input, returns `boolean`. Used for filtering/testing.

```java
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4)); // true
System.out.println(isEven.test(3)); // false
```

*Analogy: A bouncer at a club — each person is either allowed in (true) or not (false).*

---

#### `Function<T, R>`
Takes one input of type T, returns output of type R. Used for transformations.

```java
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Hello")); // 5
```

*Analogy: A converter plug — takes one type of input (UK plug) and converts it to another (US socket).*

---

#### `Consumer<T>`
Takes one input, returns nothing. Used for performing actions.

```java
Consumer<String> print = s -> System.out.println(s);
print.accept("Hello!"); // Hello!
```

*Analogy: A shredder — you feed it paper, it does something with it, nothing comes back out.*

---

#### `Supplier<T>`
Takes no input, returns a value. Used for lazy value generation.

```java
Supplier<String> greeting = () -> "Good Morning!";
System.out.println(greeting.get()); // Good Morning!
```

*Analogy: A vending machine — you press a button (call `get()`), it gives you something without you putting anything in.*

---

### Q21. Difference between Anonymous Class and Lambda Expression

**Definition:**

| Feature | Anonymous Class | Lambda Expression |
|---|---|---|
| Verbosity | Verbose | Concise |
| `this` keyword | Refers to anonymous class | Refers to enclosing class |
| Can have state | Yes (instance variables) | No |
| Applicable to | Any interface / abstract class | Only functional interfaces |
| Performance | New class file created | No separate class file |

**Example:**
```java
// Anonymous class
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println(this); // refers to anonymous Runnable
    }
};

// Lambda
Runnable r2 = () -> System.out.println(this); // refers to outer class
```

**Real-World Analogy:**
Anonymous class is like hiring a full-time contractor with a contract, office space, and business card. Lambda is like asking a friend to do a quick favor — no paperwork needed.

---

### Q22. Can Lambda Expressions be used without Functional Interfaces?

**Definition:**
**No.** Lambda expressions must always be assigned to a functional interface type. They are the implementation of that interface's single abstract method. Without a functional interface, the compiler has no type context for the lambda.

**Example:**
```java
// ✅ Valid - assigned to functional interface
Runnable r = () -> System.out.println("Running");

// ❌ Invalid - no target type
var x = () -> System.out.println("Running"); // compile error
```

**Real-World Analogy:**
A lambda is an answer — but you need a question first. The functional interface is the question. Without the question, the answer has no meaning or context.

---

### Q23. What is Effectively Final Variable in Lambda?

**Definition:**
A variable used inside a lambda must be **final** or **effectively final** — meaning its value must not change after being assigned, even if not explicitly declared `final`.

**Example:**
```java
int x = 10; // effectively final (never reassigned)
Runnable r = () -> System.out.println(x); // ✅ OK

int y = 10;
y = 20; // reassigned → no longer effectively final
Runnable r2 = () -> System.out.println(y); // ❌ Compile error
```

**Real-World Analogy:**
When you take a photo of a room, the photo captures that moment in time. If you change the room afterwards, the photo doesn't update. The lambda captures the variable's value at that point — so it must stay stable (not change).

---

---

## 3. Stream API

---

### Q24. What is Stream API? *(Deep Dive)*

**Definition:**
Stream API (`java.util.stream`) provides a sequence of elements supporting sequential and parallel aggregate operations. It allows you to process data in a declarative pipeline: source → intermediate operations → terminal operation.

**Example:**
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

long count = names.stream()
                  .filter(n -> n.startsWith("A") || n.startsWith("D"))
                  .map(String::toUpperCase)
                  .count();

System.out.println(count); // 2
```

**Real-World Analogy:**
An assembly line in a factory. Raw materials enter (source), go through multiple stations like cutting, painting, inspecting (intermediate ops), and finally a finished product is counted or packed (terminal op).

---

### Q25. What are Intermediate Operations?

**Definition:**
Intermediate operations are stream operations that return another stream. They are **lazy** — they don't execute until a terminal operation is called. Common ones: `filter()`, `map()`, `flatMap()`, `sorted()`, `distinct()`, `limit()`, `skip()`.

**Example:**
```java
Stream<Integer> stream = Arrays.asList(5, 3, 1, 4, 2)
    .stream()
    .filter(n -> n > 2)   // intermediate
    .sorted()              // intermediate
    .map(n -> n * 10);    // intermediate
// Nothing executes yet!

stream.forEach(System.out::println); // Terminal triggers all above
// 30, 40, 50
```

**Real-World Analogy:**
Writing down a recipe is intermediate — you're just defining steps. Actually cooking is the terminal operation. Until you start cooking, no food is made.

---

### Q26. What are Terminal Operations?

**Definition:**
Terminal operations trigger the processing of the pipeline and produce a result (or side effect). After a terminal operation, the stream is **consumed** and cannot be reused. Common ones: `forEach()`, `collect()`, `count()`, `reduce()`, `findFirst()`, `findAny()`, `anyMatch()`, `allMatch()`.

**Example:**
```java
List<String> result = names.stream()
    .filter(n -> n.length() > 3)
    .collect(Collectors.toList()); // terminal
```

**Real-World Analogy:**
Pressing "Send" on an email — that's the terminal operation. All your writing/editing (intermediate) only mattered once you hit Send.

---

### Q27. Difference between `map()` and `filter()`

**Definition:**

| | `map()` | `filter()` |
|---|---|---|
| Purpose | Transform each element | Keep or discard elements |
| Output size | Same as input | Same or smaller |
| Takes | `Function<T, R>` | `Predicate<T>` |

**Example:**
```java
List<String> names = Arrays.asList("alice", "bob", "charlie");

// map - transforms
names.stream().map(String::toUpperCase).forEach(System.out::println);
// ALICE, BOB, CHARLIE

// filter - selects
names.stream().filter(n -> n.length() > 3).forEach(System.out::println);
// alice, charlie
```

**Real-World Analogy:**
`filter()` is a sieve — it lets through only what matches. `map()` is a paint booth — every item goes in and comes out transformed.

---

### Q28. What is the `collect()` method?

**Definition:**
`collect()` is a terminal operation that accumulates stream elements into a collection (like `List`, `Set`, `Map`) using a `Collector`.

**Example:**
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

List<String> longNames = names.stream()
    .filter(n -> n.length() > 3)
    .collect(Collectors.toList());

System.out.println(longNames); // [Alice, Charlie]

// Collect to Map
Map<String, Integer> nameLengths = names.stream()
    .collect(Collectors.toMap(n -> n, String::length));
```

**Real-World Analogy:**
The stream is water flowing through a pipeline. `collect()` is the bucket at the end — it gathers all the water that made it through into a usable container.

---

### Q29. Difference between `findFirst()` and `findAny()`

**Definition:**

| | `findFirst()` | `findAny()` |
|---|---|---|
| Returns | First element in encounter order | Any element (unpredictable in parallel) |
| Sequential stream | Same result | Same result |
| Parallel stream | Consistent (first by order) | Faster (no ordering constraint) |

**Example:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

Optional<Integer> first = numbers.stream().filter(n -> n > 2).findFirst();
System.out.println(first.get()); // always 3

Optional<Integer> any = numbers.parallelStream().filter(n -> n > 2).findAny();
System.out.println(any.get()); // could be 3, 4, or 5
```

**Real-World Analogy:**
`findFirst()` is asking the queue: "Who's first?" — always the person at the front. `findAny()` is shouting: "Anyone available?" — whoever responds fastest (useful when speed matters more than order).

---

### Q30. What is Parallel Stream?

**Definition:**
A Parallel Stream splits the data into multiple parts, processes them concurrently on multiple CPU cores using the Fork/Join framework, and then combines the results.

**Example:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

// Sequential
numbers.stream().forEach(System.out::print);       // 1 2 3 4 5 6 7 8

// Parallel (order may vary)
numbers.parallelStream().forEach(System.out::print); // e.g. 5 2 7 1 ...
```

**Real-World Analogy:**
Sequential stream is one cashier serving a long queue. Parallel stream is opening 4 checkout lanes — customers are served simultaneously, total time reduced.

---

---

## 4. Exception Handling

---

### Q31. What is an Exception in Java?

**Definition:**
An Exception is an event that disrupts the normal flow of a program during execution. It is an object that wraps an error signal and can be caught and handled.

**Example:**
```java
int[] arr = {1, 2, 3};
System.out.println(arr[5]); // throws ArrayIndexOutOfBoundsException
```

**Real-World Analogy:**
Like a speed bump on a road. Your car (program) is moving fine, hits the bump (exception) — if you handle it (slow down), you continue. If you ignore it, you damage the car (crash).

---

### Q32. Difference between Error and Exception

**Definition:**

| | Error | Exception |
|---|---|---|
| Package | `java.lang.Error` | `java.lang.Exception` |
| Cause | Serious system-level issues | Application-level issues |
| Recoverable | No | Yes (usually) |
| Examples | `OutOfMemoryError`, `StackOverflowError` | `NullPointerException`, `IOException` |

**Example:**
```java
// Error - cannot recover
// StackOverflowError from infinite recursion

// Exception - can recover
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```

**Real-World Analogy:**
Exception = a flat tire — annoying, but fixable. Error = the engine completely failing — you're not going anywhere without serious external help.

---

### Q33. What is Exception Handling?

**Definition:**
Exception Handling is the mechanism in Java to detect, catch, and respond to runtime exceptions gracefully — keeping the program alive and informing the user meaningfully.

**Java's Keywords:** `try`, `catch`, `finally`, `throw`, `throws`

**Example:**
```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Error: " + e.getMessage());
} finally {
    System.out.println("This always runs");
}
```

**Real-World Analogy:**
A pilot's emergency procedures manual. When something goes wrong mid-flight (exception), the pilot follows the manual (catch block) to land safely instead of panicking and crashing.

---

### Q34. What are the Types of Exceptions in Java?

**Definition:**
```
Throwable
├── Error (unchecked, unrecoverable)
└── Exception
    ├── Checked Exception (compile-time)
    └── RuntimeException (unchecked, run-time)
```

**Examples:**
- **Checked:** `IOException`, `SQLException`, `FileNotFoundException`
- **Unchecked (Runtime):** `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ClassCastException`

**Real-World Analogy:**
Checked = a mandatory car inspection before you drive — the law forces you to handle it upfront. Unchecked = a pothole you might hit while driving — it might happen, but no one checked beforehand.

---

### Q35. Difference between Checked and Unchecked Exception

**Definition:**

| | Checked Exception | Unchecked Exception |
|---|---|---|
| When detected | Compile time | Runtime |
| Must handle? | Yes (try-catch or throws) | No |
| Extends | `Exception` (not RuntimeException) | `RuntimeException` |
| Examples | `IOException`, `SQLException` | `NullPointerException`, `ArithmeticException` |

**Example:**
```java
// Checked - must handle
try {
    FileReader fr = new FileReader("file.txt"); // ✅ must catch IOException
} catch (IOException e) {
    e.printStackTrace();
}

// Unchecked - optional handling
String s = null;
s.length(); // throws NullPointerException at runtime
```

**Real-World Analogy:**
Checked = mandatory seat belt warning (car won't let you ignore it). Unchecked = no warning before a speed bump — you either know or you don't.

---

### Q36. What is `try-catch` block?

**Definition:**
`try` wraps the risky code. `catch` defines what to do if a specific exception occurs. Multiple catch blocks can handle different exception types.

**Example:**
```java
try {
    int[] arr = new int[3];
    arr[10] = 5; // risky
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Index out of bounds: " + e.getMessage());
} catch (Exception e) {
    System.out.println("General error: " + e.getMessage());
}
```

**Real-World Analogy:**
`try` is testing a recipe for the first time. `catch` is your backup plan for each possible failure — burnt toast → use the toaster; spilled milk → use a cloth. You have a response for each scenario.

---

### Q37. What is `finally` block?

**Definition:**
`finally` is a block that **always executes** — whether an exception occurred or not. It is used for cleanup actions like closing files, releasing connections, etc.

**Example:**
```java
Connection conn = null;
try {
    conn = getConnection();
    // do work
} catch (SQLException e) {
    e.printStackTrace();
} finally {
    if (conn != null) conn.close(); // always runs
}
```

**Real-World Analogy:**
Like turning off the lights when you leave a room — it doesn't matter whether you had a good or bad day inside. You always turn them off on the way out.

---

### Q38. When does `finally` NOT execute?

**Definition:**
`finally` does **not** execute in these rare cases:
1. `System.exit()` is called
2. JVM crashes
3. The thread running the code is killed

**Example:**
```java
try {
    System.out.println("In try");
    System.exit(0); // JVM exits here
} finally {
    System.out.println("In finally"); // ❌ Never printed
}
```

**Real-World Analogy:**
The "turn off lights" rule doesn't apply if the entire building is demolished (`System.exit`) — there's no building left to worry about.

---

### Q39. What is the `throw` keyword?

**Definition:**
`throw` is used to **explicitly throw an exception** from inside the code. You create the exception object and throw it manually.

**Example:**
```java
public void validateAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Age must be 18 or above");
    }
    System.out.println("Valid age");
}
```

**Real-World Analogy:**
Like a bouncer who physically stops someone and says *"You're not allowed in"* — they're actively raising the issue, not passively waiting for something to go wrong.

---

### Q40. What is the `throws` keyword?

**Definition:**
`throws` is used in a method signature to **declare** that the method might throw one or more checked exceptions — warning the caller to handle them.

**Example:**
```java
public void readFile(String path) throws IOException {
    FileReader fr = new FileReader(path);
}

// Caller must handle
try {
    readFile("data.txt");
} catch (IOException e) {
    e.printStackTrace();
}
```

**Real-World Analogy:**
A warning label on a product — *"May contain nuts."* The product isn't throwing nuts at you right now, but it's letting you know it might, so you're prepared.

---

### Q41. Difference between `throw` and `throws`

**Definition:**

| | `throw` | `throws` |
|---|---|---|
| Purpose | Actually throws an exception | Declares possible exceptions |
| Location | Inside method body | In method signature |
| Used with | Exception object (`new`) | Exception class name |
| Handles | One at a time | Multiple (comma-separated) |

**Example:**
```java
// throws in signature
public void process() throws IOException, SQLException {
    // throw in body
    throw new IOException("File not found");
}
```

**Real-World Analogy:**
`throws` = a "Wet Floor" sign placed before you walk in (warning). `throw` = actually slipping and falling (the event occurring).

---

### Q42. What is a Custom Exception?

**Definition:**
A Custom Exception is a user-defined exception class that extends `Exception` (checked) or `RuntimeException` (unchecked) to represent application-specific error scenarios.

**Example:**
```java
// Custom checked exception
class InsufficientFundsException extends Exception {
    private double amount;

    public InsufficientFundsException(double amount) {
        super("Insufficient funds. Short by: " + amount);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

// Usage
public void withdraw(double balance, double amount) throws InsufficientFundsException {
    if (amount > balance) {
        throw new InsufficientFundsException(amount - balance);
    }
}
```

**Real-World Analogy:**
Like a custom alarm code in a building. Instead of generic *"ALARM"*, it says *"FIRE IN ZONE 3"* — specific, meaningful, and actionable.

---

### Q43. What is `try-with-resources`?

**Definition:**
`try-with-resources` (Java 7+) automatically closes resources (like files, DB connections) that implement `AutoCloseable` at the end of the try block — no need for `finally`.

**Example:**
```java
// Old way (Java 6)
FileReader fr = null;
try {
    fr = new FileReader("file.txt");
} finally {
    if (fr != null) fr.close();
}

// try-with-resources (Java 7+)
try (FileReader fr = new FileReader("file.txt")) {
    // use fr
} // fr.close() called automatically
```

**Multiple resources:**
```java
try (Connection conn = getConnection();
     Statement stmt = conn.createStatement()) {
    // use both — both auto-closed
}
```

**Real-World Analogy:**
Like a hotel room where the key card cuts the electricity when you leave — you don't have to remember to turn things off. The system handles cleanup automatically.

---

---

## 5. Interfaces (Advanced)

---

### Q44. What is an Interface in Java?

**Definition:**
An Interface is a blueprint/contract that defines what a class must do (abstract methods) without specifying how. A class `implements` an interface and provides the implementation.

**Example:**
```java
interface Drawable {
    void draw(); // abstract by default
}

class Circle implements Drawable {
    public void draw() {
        System.out.println("Drawing a circle");
    }
}
```

**Real-World Analogy:**
A power socket interface — every device that plugs in must follow the same pin layout (contract). The socket doesn't care whether it's a phone or laptop; it just enforces the interface.

---

### Q45. Why do we use Interfaces?

**Definition:**
Interfaces are used for:
1. **Abstraction** — hide implementation details
2. **Multiple inheritance** — a class can implement many interfaces
3. **Loose coupling** — code depends on interfaces, not concrete classes
4. **Polymorphism** — different implementations, same interface

**Example:**
```java
interface Payment {
    void pay(double amount);
}

class CreditCard implements Payment {
    public void pay(double amount) { System.out.println("Paid by Credit Card: " + amount); }
}

class PayPal implements Payment {
    public void pay(double amount) { System.out.println("Paid by PayPal: " + amount); }
}

Payment p = new PayPal();
p.pay(500); // works with any implementation
```

**Real-World Analogy:**
A universal USB standard. Whether it's a mouse, keyboard, or charger — they all follow the USB interface. Your computer (client code) doesn't need to know what's plugged in; it just uses the standard.

---

### Q46. Difference between Interface and Abstract Class

**Definition:**

| Feature | Interface | Abstract Class |
|---|---|---|
| Methods | Abstract, default, static, private | Abstract and concrete |
| Variables | `public static final` only | Any type |
| Multiple inheritance | Yes (a class can implement many) | No (single class extension) |
| Constructor | No | Yes |
| Access modifiers | Methods are `public` by default | Any modifier |
| When to use | Unrelated classes share behavior | Related classes share code |

**Example:**
```java
interface Flyable {
    void fly();
}

abstract class Animal {
    String name;
    abstract void sound();
    void breathe() { System.out.println("Breathing"); }
}

class Bird extends Animal implements Flyable {
    void sound() { System.out.println("Tweet"); }
    public void fly() { System.out.println("Flapping wings"); }
}
```

**Real-World Analogy:**
Interface = a job description (what you must do). Abstract class = a partial house built on a foundation (some rooms done, you finish the rest). Both define a contract, but abstract class gives you more to start with.

---

### Q47. Can an Interface have Variables?

**Definition:**
Yes, but all variables in an interface are **implicitly `public static final`** — meaning they are constants. They cannot be instance variables.

**Example:**
```java
interface Config {
    int MAX_RETRIES = 3;         // implicitly public static final
    String DEFAULT_URL = "http://api.example.com";
}

// Equivalent to:
// public static final int MAX_RETRIES = 3;
```

**Real-World Analogy:**
Like laws in a country — they're the same for everyone (static), everyone can see them (public), and they can't be changed by individuals (final).

---

### Q48. Can an Interface have Methods with Body?

**Definition:**
Yes — since Java 8, interfaces can have:
- **Default methods** — with body, using `default` keyword (can be overridden)
- **Static methods** — with body, using `static` keyword (cannot be overridden)
- **Private methods** — since Java 9, used as helpers within the interface

**Example:**
```java
interface Logger {
    default void log(String msg) {
        System.out.println("[LOG]: " + msg);
    }

    static void warn(String msg) {
        System.out.println("[WARN]: " + msg);
    }
}
```

**Real-World Analogy:**
An interface used to be a pure rulebook with no examples. Java 8 added example implementations (default methods) so that existing implementors don't need to rewrite from scratch with every update.

---

### Q49. What are Default Methods in Interfaces? *(Advanced)*

*(See Q10 for definition. Advanced points:)*

**Key Points:**
- Introduced in Java 8 to enable backward compatibility
- Can be overridden by implementing class
- If two interfaces have the same default method, implementing class **must** override it

**Example:**
```java
interface A {
    default void show() { System.out.println("A"); }
}

interface B {
    default void show() { System.out.println("B"); }
}

class C implements A, B {
    public void show() { A.super.show(); } // must override, choose which
}
```

**Real-World Analogy:**
Two parents giving different advice (same topic). The child (implementing class) must decide which parent's advice to follow — or come up with their own.

---

### Q50. What are Static Methods in Interfaces? *(Advanced)*

*(See Q11 for definition. Advanced points:)*

**Key Points:**
- Belong to the interface, not implementing class
- Cannot be overridden (no polymorphism)
- Called via interface name only: `InterfaceName.method()`
- Useful for utility/factory methods related to the interface

**Example:**
```java
interface Validator {
    boolean validate(String input);

    static Validator nonEmpty() {
        return input -> input != null && !input.isEmpty();
    }
}

Validator v = Validator.nonEmpty();
System.out.println(v.validate("Hello")); // true
```

---

### Q51. Can an Interface have Private Methods?

**Definition:**
Yes — since **Java 9**, interfaces can have `private` methods. They are used as helper methods inside the interface itself — shared between default or static methods, without exposing them to implementing classes.

**Example:**
```java
interface DataProcessor {
    default void process(String data) {
        String cleaned = clean(data); // calls private helper
        System.out.println("Processing: " + cleaned);
    }

    private String clean(String data) {
        return data.trim().toLowerCase();
    }
}
```

**Real-World Analogy:**
Like a recipe that has internal prep steps (*chop vegetables, marinate*) that the diner (implementing class) never sees. The final dish is served, not the prep.

---

### Q52. Can an Interface extend another Interface?

**Definition:**
Yes — an interface can extend one or more other interfaces using `extends`. The child interface inherits all abstract methods and default methods of the parent(s).

**Example:**
```java
interface Animal {
    void breathe();
}

interface Pet extends Animal {
    void play();
}

class Dog implements Pet {
    public void breathe() { System.out.println("Breathing"); }
    public void play() { System.out.println("Fetching ball"); }
}
```

**Real-World Analogy:**
A "Premium Member" card extends a "Regular Member" card — it has all regular benefits plus more. The issuing organization (implementing class) must honor all the terms from both cards.

---

### Q53. Can a Class implement Multiple Interfaces?

**Definition:**
Yes — Java allows a class to implement multiple interfaces, separated by commas. This is Java's way of achieving **multiple inheritance of type**.

**Example:**
```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    public void fly() { System.out.println("Duck flying"); }
    public void swim() { System.out.println("Duck swimming"); }
}
```

**Real-World Analogy:**
A Swiss Army knife implements multiple tools — knife, scissors, screwdriver. One object, multiple capabilities, all following their own contracts.

---

### Q54. What happens if two Interfaces have the same Default Method?

**Definition:**
If a class implements two interfaces that both define the same default method, there is a **conflict**. The compiler forces the implementing class to **explicitly override** the method.

**Example:**
```java
interface A {
    default void greet() { System.out.println("Hello from A"); }
}

interface B {
    default void greet() { System.out.println("Hello from B"); }
}

class C implements A, B {
    // ❌ Compile error if not overridden
    @Override
    public void greet() {
        A.super.greet(); // explicitly choose A's version
        // or write custom logic
    }
}
```

**Real-World Analogy:**
Two managers both give you conflicting instructions for the same task. You can't follow both, so you go to HR (the compiler), who says: *"You must pick one or decide for yourself."*

---

### Q55. What is a Marker Interface?

**Definition:**
A Marker Interface (also called Tag Interface) is an **empty interface** — it has no methods or fields. It is used to signal or "mark" a class with some special behavior that the JVM or framework can detect at runtime.

**Examples:** `Serializable`, `Cloneable`, `Remote`

**Example:**
```java
// Serializable is a marker interface
class Employee implements Serializable {
    int id;
    String name;
    // JVM knows this object can be serialized
}

ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("emp.dat"));
oos.writeObject(new Employee()); // works because of Serializable marker
```

**Real-World Analogy:**
Like sticking a "Fragile" sticker on a package. The sticker carries no instructions — but it signals to the delivery person (JVM) to handle it differently.

---

*End of Document*