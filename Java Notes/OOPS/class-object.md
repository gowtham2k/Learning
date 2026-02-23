# Java Classes and Objects Explained 🎓

## Introduction

Think of a **class** as a blueprint or template, and an **object** as the actual thing built from that blueprint. Just like how an architect's blueprint can be used to build many houses, a class can be used to create many objects!

---

## What Does a Class Contain?

A class is like a container that holds:

### 1. **Attributes (Instance Variables)**
These are the characteristics or properties of the class.
```java
class Student {
    String name;      // attribute
    int age;          // attribute
    String rollNumber; // attribute
}
```

### 2. **Methods (Behaviors)**
These are actions that objects of the class can perform.
```java
class Student {
    String name;
    int age;
    
    void study() {
        System.out.println(name + " is studying!");
    }
    
    void attendClass() {
        System.out.println(name + " is attending class!");
    }
}
```

### 3. **Constructors**
Special methods used to initialize objects when they're created.
```java
class Student {
    String name;
    int age;
    
    // Constructor
    Student(String studentName, int studentAge) {
        name = studentName;
        age = studentAge;
    }
}
```

---

## How Objects are Stored in Memory

When you create an object, **two things happen in memory:**

### Step 1: Object Creation
```java
Student student1 = new Student("Alice", 20);
```

Let me break this down:

```mermaid
graph TB
    A[Student student1 = new Student Alice, 20] --> B[Stack Memory]
    A --> C[Heap Memory]
    
    B --> D[student1<br/>Reference Variable<br/>Stores: 0x1A2B3C4D]
    C --> E[Actual Object<br/>Address: 0x1A2B3C4D<br/>name = Alice<br/>age = 20]
    
    D -.->|Points to| E
    
    style B fill:#e1f5ff
    style C fill:#fff4e1
    style D fill:#c8e6c9
    style E fill:#ffccbc
```

### Memory Allocation Breakdown:

1. **Stack Memory** - Stores the reference variable (`student1`)
2. **Heap Memory** - Stores the actual object with its data
3. **Reference Address** - The variable holds the memory address (like `0x1A2B3C4D`) pointing to the object in heap

---

## When is Memory Created?

Memory allocation happens at **runtime** when you use the `new` keyword:

```mermaid
sequenceDiagram
    participant Code
    participant JVM
    participant Stack
    participant Heap
    
    Code->>JVM: Student student1;
    JVM->>Stack: Create reference variable 'student1'
    Note over Stack: student1 = null (no object yet)
    
    Code->>JVM: new Student("Alice", 20);
    JVM->>Heap: Allocate memory for Student object
    Heap-->>JVM: Return memory address (0x1A2B)
    JVM->>Stack: Store address in student1
    Note over Stack: student1 = 0x1A2B
    
    Note over Stack,Heap: Now student1 points to the object!
```

### Timeline:
1. **Compile Time**: Class definition is checked for errors
2. **Runtime - Step 1**: Reference variable is created in stack (initialized to `null`)
3. **Runtime - Step 2**: `new` keyword triggers object creation in heap
4. **Runtime - Step 3**: Constructor runs to initialize the object
5. **Runtime - Step 4**: Memory address is stored in the reference variable

---

## What Happens When Variables Aren't Assigned?

Great question! Let's see what happens when you don't assign values to variables:

### Example:
```java
class Car {
    String brand;      // String (Reference type)
    int price;         // int (Primitive type)
    boolean isElectric; // boolean (Primitive type)
    double mileage;    // double (Primitive type)
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        
        System.out.println("Brand: " + myCar.brand);        // null
        System.out.println("Price: " + myCar.price);        // 0
        System.out.println("Electric: " + myCar.isElectric); // false
        System.out.println("Mileage: " + myCar.mileage);    // 0.0
    }
}
```

### Default Values Table:

```mermaid
graph LR
    A[Instance Variables] --> B[Reference Types]
    A --> C[Primitive Types]
    
    B --> D[String, Arrays, Objects<br/>Default: null]
    
    C --> E[int, byte, short, long<br/>Default: 0]
    C --> F[float, double<br/>Default: 0.0]
    C --> G[boolean<br/>Default: false]
    C --> H[char<br/>Default: '\\u0000']
    
    style A fill:#4fc3f7
    style B fill:#ffb74d
    style C fill:#aed581
    style D fill:#fff9c4
    style E fill:#fff9c4
    style F fill:#fff9c4
    style G fill:#fff9c4
    style H fill:#fff9c4
```

---

## Complete Memory Visualization

Let's see a complete example with multiple objects:

```java
class Student {
    String name;
    int age;
    
    Student(String n, int a) {
        name = n;
        age = a;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Alice", 20);
        Student s2 = new Student("Bob", 22);
        Student s3 = s1;  // Same reference!
    }
}
```

```mermaid
graph TB
    subgraph Stack["Stack Memory (Method: main)"]
        S1[s1<br/>0x001]
        S2[s2<br/>0x002]
        S3[s3<br/>0x001]
    end
    
    subgraph Heap["Heap Memory"]
        O1["Object 1<br/>Address: 0x001<br/>name: Alice<br/>age: 20"]
        O2["Object 2<br/>Address: 0x002<br/>name: Bob<br/>age: 22"]
    end
    
    S1 -.->|Points to| O1
    S2 -.->|Points to| O2
    S3 -.->|Also points to| O1
    
    style Stack fill:#e3f2fd
    style Heap fill:#fff3e0
    style S1 fill:#c8e6c9
    style S2 fill:#c8e6c9
    style S3 fill:#ffccbc
    style O1 fill:#b2dfdb
    style O2 fill:#b2dfdb
```

### Key Observations:
- `s1` and `s3` point to the **same object** (address `0x001`)
- `s2` points to a **different object** (address `0x002`)
- If you modify through `s3`, changes will reflect in `s1` (same object!)

---

## Object Lifecycle: Creation to Destruction

```mermaid
stateDiagram-v2
    [*] --> Declared: Student s1;
    Declared --> Created: new Student("Alice", 20);
    Created --> InUse: Object is being used
    InUse --> InUse: Methods called, values changed
    InUse --> Unreferenced: s1 = null; or method ends
    Unreferenced --> GarbageCollected: Garbage Collector runs
    GarbageCollected --> [*]: Memory freed
    
    note right of Declared
        Reference variable created
        Value: null
        Memory: Stack only
    end note
    
    note right of Created
        Object created in Heap
        Constructor executed
        Stack stores reference
    end note
    
    note right of Unreferenced
        No variables point to object
        Eligible for cleanup
    end note
```

---

## Real-World Analogy 🏠

Think of it like this:

| Programming Concept | Real-World Analogy |
|---------------------|-------------------|
| **Class** | Blueprint of a house |
| **Object** | Actual house built from blueprint |
| **Reference Variable** | Address written on a piece of paper |
| **new Keyword** | Construction company building the house |
| **Heap Memory** | The neighborhood where houses are built |
| **Stack Memory** | Your pocket where you keep addresses |
| **Attributes** | Features of the house (color, size, rooms) |
| **Methods** | Things you can do in the house (cook, sleep) |
| **Default Values** | Empty rooms (not furnished) |

---

## Quick Example with Unassigned Variables

```java
class Book {
    String title;
    String author;
    int pages;
    double price;
}

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book();  // Object created
        
        // All values are defaults because we didn't assign anything
        System.out.println(book1.title);  // null
        System.out.println(book1.author); // null
        System.out.println(book1.pages);  // 0
        System.out.println(book1.price);  // 0.0
        
        // Now let's assign some values
        book1.title = "Java Programming";
        book1.pages = 500;
        
        System.out.println(book1.title);  // Java Programming
        System.out.println(book1.author); // still null
        System.out.println(book1.pages);  // 500
        System.out.println(book1.price);  // still 0.0
    }
}
```

```mermaid
graph TD
    A[Book book1 = new Book] --> B{Memory Allocated}
    B --> C[Stack: book1 reference]
    B --> D[Heap: Book object]
    
    D --> E[title = null]
    D --> F[author = null]
    D --> G[pages = 0]
    D --> H[price = 0.0]
    
    I[Assign values] --> J[title = Java Programming]
    I --> K[pages = 500]
    
    J --> L[title = Java Programming]
    K --> M[pages = 500]
    
    style A fill:#4fc3f7
    style C fill:#c8e6c9
    style D fill:#ffccbc
    style E fill:#ffebee
    style F fill:#ffebee
    style G fill:#ffebee
    style H fill:#ffebee
    style L fill:#c8e6c9
    style M fill:#c8e6c9
```

---

## Summary 📝

### Key Takeaways:

1. **Class = Blueprint**, **Object = Instance built from blueprint**

2. **Memory Allocation:**
   - Stack stores reference variables (addresses)
   - Heap stores actual objects
   - `new` keyword triggers memory allocation

3. **Reference Variables:**
   - Hold memory addresses, not actual objects
   - Multiple variables can point to the same object

4. **When Memory is Created:**
   - Reference variable: immediately when declared
   - Object: when `new` keyword is used (runtime)

5. **Unassigned Variables:**
   - Get default values automatically
   - Reference types → `null`
   - Numeric types → `0` or `0.0`
   - Boolean → `false`
   - Char → `'\u0000'`

6. **Important:** If you don't assign values, Java won't throw an error for instance variables (class variables). They'll just have default values. But **local variables** (inside methods) MUST be assigned before use!

---

## Practice Question 🤔

What will be the output?

```java
class Person {
    String name;
    int age;
    
    Person(String name) {
        this.name = name;
        // age not assigned in constructor!
    }
}

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("John");
        System.out.println(p1.name);
        System.out.println(p1.age);
    }
}
```

**Answer:** 
```
John
0
```

Why? Because `name` is assigned in the constructor, but `age` gets the default value of `0` (default for int).

---


# 📌 Call by Value vs Call by Reference in Java (Teacher Style)

> ✅ **Important Truth:**\
> Java is **always call by value**.\
> But for objects, the **value being passed is the reference**, which
> creates confusion.

------------------------------------------------------------------------

# 🧠 1. Call by Value (Primitive Types)

## 🔹 Definition

When a method receives a **copy of the value**, any change inside the
method **does NOT affect the original variable**.

------------------------------------------------------------------------

## ✅ Example

``` java
class PassingRefDemo {

    static void increment(int a) {
        a++; // changes only the copy
    }

    public static void main(String[] args) {
        int a = 10;
        increment(a);
        increment(a);
        System.out.println(a);
    }
}
```

------------------------------------------------------------------------

## 🔍 Step-by-Step Execution

### Step 1 --- Before method call

``` mermaid
flowchart TB
    subgraph Stack
        A1["main(): a = 10"]
    end
```

------------------------------------------------------------------------

### Step 2 --- First method call: `increment(a)`

👉 Java creates a **new stack frame**\
👉 Value **10 is copied**

``` mermaid
flowchart TB
    subgraph Stack
        M1["main(): a = 10"]
        M2["increment(): a = 10 (copy)"]
    end
```

Inside method:

    a++ → becomes 11 (ONLY inside method)

After method ends → frame destroyed ❌

------------------------------------------------------------------------

### Step 3 --- Second call

Same thing happens again.

------------------------------------------------------------------------

## ✅ Final Output

    10

✔ Original `a` never changed\
✔ Because primitive uses pure call by value

------------------------------------------------------------------------

# 🧠 2. Call by Value with Objects (The Confusing Part)

Now your second example:

``` java
Box b1 = new Box(5,3,4);
Box b2 = new Box(5,3,4);

System.out.println(b1.isEqual(b2));
System.out.println(b2.length);
```

------------------------------------------------------------------------

## 🔥 VERY IMPORTANT

Java passes:

-   ❌ NOT the object\
-   ❌ NOT true call by reference\
-   ✅ **Copy of the reference**

------------------------------------------------------------------------

# 🧱 Heap vs Stack Memory

## 📦 Memory Areas

  Memory   Stores
  -------- -----------------------------
  Stack    local variables, references
  Heap     actual objects

------------------------------------------------------------------------

# 🔬 Execution Flow with Diagram

## ✅ Step 1 --- Object Creation

``` java
Box b1 = new Box(5,3,4);
Box b2 = new Box(5,3,4);
```

### Memory State

``` mermaid
flowchart LR

    subgraph Stack
        S1["b1 → 5000"]
        S2["b2 → 7000"]
    end

    subgraph Heap
        H1["Box @5000\nl=5,b=3,h=4"]
        H2["Box @7000\nl=5,b=3,h=4"]
    end
```

------------------------------------------------------------------------

## ✅ Step 2 --- Method Call

``` java
b1.isEqual(b2);
```

Method:

``` java
boolean isEqual(Box b) {
    b.length++;  // ⚠️ modifying object
}
```

### 🔴 What Actually Gets Passed

Java copies the **reference value**.

``` mermaid
flowchart LR

    subgraph Stack
        M1["main: b2 → 7000"]
        M2["isEqual: b → 7000 (copy of reference)"]
    end

    subgraph Heap
        H1["Box @7000\nl=5,b=3,h=4"]
    end
```

------------------------------------------------------------------------

## 🚨 Key Insight

Even though reference is copied...

👉 BOTH references point to **same heap object**

So this line:

``` java
b.length++;
```

✅ modifies the real object in heap

------------------------------------------------------------------------

## ✅ Step 3 --- After Modification

``` mermaid
flowchart LR

    subgraph Stack
        S1["b2 → 7000"]
    end

    subgraph Heap
        H1["Box @7000\nl=6,b=3,h=4"]
    end
```

------------------------------------------------------------------------

## ✅ Final Output

    false
    6

------------------------------------------------------------------------

# 🎯 Final Teacher Summary

> 🟢 Java = always call by value\
> 🟡 For primitives → value copied\
> 🔴 For objects → reference value copied\
> 🔥 Object state CAN change\
> ❌ Reference itself cannot change outside


Happy Learning! 🚀