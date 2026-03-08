# Abstract Class in Java — Complete Guide

---

## 1. Concrete vs Abstract

### Concrete
A **concrete** thing refers to a **physical object** that exists in the real world. You can touch it, see it, and interact with it directly.

> Example: A chair, a book, a student sitting in a classroom — these are all concrete things.

### Abstract
An **abstract** thing is an **idea or concept** that does **not exist physically** in the real world. It is a generalized notion used to represent a category.

> Example: "Member" of a library — you cannot point to a generic "Member". A Member is always either a Student, a Teacher, or a Staff — never just a "Member" in isolation.

In Java:
- A **concrete class** is a regular class you can instantiate directly using `new`.
- An **abstract class** is a class that represents a concept/idea and **cannot be instantiated directly**.

---

## 2. The Member, Student, Teacher Example

Consider a library management system. Everyone who uses the library is a **Member**. But in reality, a Member is always one of:

- **Student**
- **Teacher**
- **Staff**

This maps perfectly to a class hierarchy:

```
        Member          <-- Abstract (concept)
       /   |   \
  Student Teacher Staff <-- Concrete (real objects)
```

In Java:

```java
abstract class Member {
    private String name;
    abstract void welcomeMessage();
}

class Student extends Member {
    void welcomeMessage() {
        System.out.println("Hello Student...");
    }
}

class Teacher extends Member {
    void welcomeMessage() {
        System.out.println("Hello Teacher...");
    }
}
```

---

## 3. Why Is `Member` Abstract? Why Use an Abstract Method?

### Why make `Member` abstract?

In the real world, **no one is just a "Member"** — they are always a Student, Teacher, or Staff. So it makes no sense to create an object of type `Member` directly. Making `Member` abstract enforces this real-world constraint in code.

### Why use an abstract method (`welcomeMessage()`)?

Every Member greets differently:
- A Student says *"Hello Student..."*
- A Teacher says *"Hello Teacher..."*

The **behavior exists for all Members**, but the **implementation differs** per type. So we declare `welcomeMessage()` as abstract in `Member` — this says:

> "Every Member must have a `welcomeMessage()`, but each subclass decides HOW it works."

### Why must the class be abstract if it has an abstract method?

If a class has even **one abstract method**, Java **forces** you to declare the class as `abstract`. The reason: an abstract method has no body, so if you could instantiate the class, calling that method would crash — there's nothing to execute. Java prevents this at compile time by requiring the class to be abstract.

```java
// Rule: abstract method → class must be abstract
abstract class Member {
    abstract void welcomeMessage(); // no body
}
```

---

## 4. Student and Teacher — The Concrete Subclasses

`Student` and `Teacher` extend `Member` and **provide the actual implementation** of `welcomeMessage()`:

```java
class Student extends Member {
    void welcomeMessage() {
        System.out.println("Hello Student...");
    }
}

class Teacher extends Member {
    void welcomeMessage() {
        System.out.println("Hello Teacher...");
    }
}
```

These are **concrete classes** — they have no abstract methods, so you can create objects from them directly using `new Student()` or `new Teacher()`.

---

## 5. Why Can't We Create `new Member()`?

```java
Member m1 = new Member(); // ❌ COMPILE ERROR
```

`Member` is declared `abstract`. The Java compiler **prevents instantiation** of abstract classes. Why?

- `Member` has an abstract method `welcomeMessage()` with **no body**.
- If you could create a `Member` object and call `m1.welcomeMessage()`, Java would have **nothing to execute** — no implementation exists.
- To protect you from this runtime crash, the compiler blocks it at compile time.

**In short:** Abstract classes are **incomplete by design**. They are meant to be extended, not instantiated.

---

## 6. Why Can't We Create an Object for an Abstract Class?

This is a fundamental rule of Java:

> **You cannot instantiate an abstract class.**

Reasons:
1. An abstract class is a **blueprint for a concept**, not a complete, usable entity.
2. It may contain abstract methods (methods with no body/implementation).
3. Allowing object creation would lead to calling methods that have no implementation — which is meaningless and dangerous.
4. Abstract classes exist to be **inherited** — their purpose is fulfilled only when a concrete subclass implements the abstract methods.

Think of it this way: You cannot "manufacture" the idea of a vehicle — you manufacture a car, a bus, or a bike. The "vehicle" concept is abstract.

---

## 7. The `Member[]` Array — Why Store Subtypes Under a Parent Reference?

### The Code

```java
Member[] m = new Member[4];
m[0] = new Student();
m[1] = new Student();
m[2] = new Teacher();
m[3] = new Teacher();

for (Member m1 : m) {
    m1.welcomeMessage();
}
```

### Output
```
Hello Student...
Hello Student...
Hello Teacher...
Hello Teacher...
```

### Why not just do this?

```java
Student s1 = new Student();
Student s2 = new Student();
Teacher t1 = new Teacher();
Teacher t2 = new Teacher();

s1.welcomeMessage();
s2.welcomeMessage();
t1.welcomeMessage();
t2.welcomeMessage();
```

This works, but it has serious problems at scale:

| Approach | Problem |
|---|---|
| Individual `Student`/`Teacher` variables | You need separate code for each type. Adding a new type (`Staff`) means rewriting logic everywhere. |
| `Member[]` array | One loop handles ALL types. Adding `Staff` later requires zero changes to the loop. |

### The Real-World Power

Imagine a library with 10,000 members — some students, some teachers, some staff. Using `Member[]`:
- You store all of them in **one collection**.
- You call `welcomeMessage()` **once in a loop** — Java automatically calls the right version for each object (this is **polymorphism**).
- You write the loop **once** and it works for every current and future subtype.

This is the power of **programming to an abstraction** — your code depends on `Member`, not on specific types, making it **flexible, extensible, and maintainable**.

---

## 8. What Happens If You Comment Out the Abstract Method?

### Scenario

```java
class Member {           // not abstract anymore
    private String name;
    // abstract void welcomeMessage();  ← commented out
}

class Student extends Member {
    void welcomeMessage() {
        System.out.println("Hello Student...");
    }
}

class Teacher extends Member {
    void welcomeMessage() {
        System.out.println("Hello Teacher...");
    }
}
```

Now in `main`:

```java
Member[] m = new Member[4];
m[0] = new Student();
m[1] = new Student();
m[2] = new Teacher();
m[3] = new Teacher();

for (Member m1 : m) {
    m1.welcomeMessage(); // ❌ COMPILE ERROR
}
```

### Why the Error?

When you write `m1.welcomeMessage()`, the compiler looks at the **type of `m1`**, which is `Member`. Since `Member` has **no `welcomeMessage()` method** (it's commented out), the compiler says:

> "I don't know what `welcomeMessage()` is for a `Member`. I won't allow this."

Even though at runtime `m1` might actually be a `Student` or `Teacher` (which do have the method), the **compiler checks at compile time** against the reference type (`Member`), not the runtime type.

### The Fix: Keep the Abstract Method in the Parent Class

By having `abstract void welcomeMessage()` in `Member`:
- The compiler knows every `Member` has a `welcomeMessage()`.
- The call `m1.welcomeMessage()` compiles successfully.
- At **runtime**, Java calls the correct version based on the actual object type (polymorphism).

**Lesson:** The abstract method in the parent class acts as a **contract** — it guarantees the method exists for all subclasses, making it safe to call via a parent reference.

---

## 9. Derived Classes Must Implement Abstract Methods + Concrete Methods in Abstract Classes

### Rule: Subclasses Must Implement All Abstract Methods

If a class extends an abstract class, it **must implement every abstract method**, or it must itself be declared `abstract`.

```java
abstract class Member {
    abstract void welcomeMessage(); // must be implemented by subclasses
}

class Student extends Member {
    @Override
    void welcomeMessage() {        // ✅ implemented
        System.out.println("Hello Student...");
    }
}
```

### Concrete Methods Are Allowed in Abstract Classes

An abstract class is not all abstract — it can have **regular (concrete) methods** too. These are shared implementations that all subclasses inherit without needing to override.

```java
abstract class Member {
    private String name;

    // Concrete method — shared by all subclasses
    void displayName() {
        System.out.println("Member name: " + name);
    }

    // Abstract method — each subclass must implement this
    abstract void welcomeMessage();
}
```

This is a key advantage: abstract classes let you **share common behavior** (concrete methods) while **enforcing different behavior** (abstract methods) across subclasses.

---

## 10. What If a Derived Class Does Not Implement the Abstract Method?

If a subclass extends an abstract class but **does not implement** all abstract methods, there are exactly **two outcomes**:

### Scenario 1: Compiler Error

If the subclass is a regular (non-abstract) class and doesn't implement the abstract method, the compiler **immediately throws an error**:

```java
class Student extends Member {
    // welcomeMessage() NOT implemented ❌
}
// Error: Student is not abstract and does not override abstract method
//        welcomeMessage() in Member
```

### Scenario 2: The Subclass Must Also Be Declared Abstract

If you don't want to implement the abstract method yet (perhaps you want to defer it to the next level of subclass), you must declare the subclass as `abstract` too:

```java
abstract class Student extends Member {
    // welcomeMessage() not implemented here
    // Some further subclass of Student must implement it
}

class GraduateStudent extends Student {
    void welcomeMessage() {   // ✅ finally implemented here
        System.out.println("Hello Graduate Student...");
    }
}
```

This creates a **chain of abstraction** — you can defer the implementation as far down as needed, but at some point a **concrete class must implement it**.

---

## 11. What Is an Abstract Class? — Complete Picture

### Definition

An **abstract class** in Java is a class declared with the `abstract` keyword. It:
- **Cannot be instantiated** (no `new AbstractClass()`).
- **Can have abstract methods** (methods without a body, declared with `abstract`).
- **Can have concrete methods** (regular methods with a body).
- **Can have constructors, fields, and static methods**.
- Is meant to be **extended** by concrete subclasses.

```java
abstract class Shape {
    String color;

    Shape(String color) {       // constructor allowed
        this.color = color;
    }

    void displayColor() {       // concrete method
        System.out.println("Color: " + color);
    }

    abstract double area();     // abstract method — subclass must implement
}
```

---

## 12. Where Is It Used in the Real World?

### Common Real-World Use Cases

| Scenario | Abstract Class | Concrete Subclasses |
|---|---|---|
| Library system | `Member` | `Student`, `Teacher`, `Staff` |
| Shapes | `Shape` | `Circle`, `Rectangle`, `Triangle` |
| Vehicles | `Vehicle` | `Car`, `Bus`, `Bike` |
| Payments | `Payment` | `CreditCard`, `UPI`, `NetBanking` |
| Animals | `Animal` | `Dog`, `Cat`, `Bird` |
| Employees | `Employee` | `Manager`, `Developer`, `Designer` |

### Why Use Abstract Classes in Real Projects?

1. **Enforce a contract** — guarantees that all subclasses implement certain methods.
2. **Code reuse** — share common logic (concrete methods) across all subclasses.
3. **Polymorphism** — write code against the abstract type and it works for all subtypes automatically.
4. **Extensibility** — adding a new subclass doesn't require changing existing code (Open/Closed Principle).

---

## 13. Rules of Abstract Classes — Summary

| Rule | Detail |
|---|---|
| Declared with `abstract` keyword | `abstract class Member { }` |
| Cannot be instantiated | `new Member()` → compile error |
| Can have abstract methods | No body, must end with `;` |
| Can have concrete methods | Regular methods with implementation |
| Can have constructors | Used by subclasses via `super()` |
| Can have fields (variables) | Both `private` and `public` |
| Subclass must implement all abstract methods | Or the subclass must also be `abstract` |
| A class with even one abstract method must be abstract | Compiler enforces this |
| Abstract class can extend another abstract class | And may choose not to implement abstract methods |
| Abstract class can implement an interface | And may defer method implementations to subclasses |

---

## 14. Abstract Class vs Interface — Quick Comparison

| Feature | Abstract Class | Interface |
|---|---|---|
| Instantiation | Cannot be instantiated | Cannot be instantiated |
| Methods | Abstract + Concrete | Abstract (default/static also allowed in Java 8+) |
| Fields | Can have instance variables | Only `public static final` constants |
| Constructors | Yes | No |
| Inheritance | Single (one parent class) | Multiple (a class can implement many interfaces) |
| Use when | Classes share common state/behavior | Unrelated classes share a capability |

---

## 15. Complete Code Example

```java
package OOP;

abstract class Member {
    private String name;

    // Abstract method — no body, must be implemented by subclasses
    abstract void welcomeMessage();

    // Concrete method — shared by all subclasses
    void displayInfo() {
        System.out.println("I am a Member.");
    }
}

class Student extends Member {
    @Override
    void welcomeMessage() {
        System.out.println("Hello Student...");
    }
}

class Teacher extends Member {
    @Override
    void welcomeMessage() {
        System.out.println("Hello Teacher...");
    }
}

public class AbstractDemo {
    public static void main(String[] args) {

        // ✅ Concrete objects
        Student s1 = new Student();
        Teacher t1 = new Teacher();

        // ❌ Cannot instantiate abstract class
        // Member m1 = new Member(); // Compile Error

        // ✅ Parent reference holding child objects (Polymorphism)
        Member m2 = new Student();  // allowed
        m2.welcomeMessage();        // prints: Hello Student...

        // ✅ Array of Members — polymorphic collection
        Member[] m = new Member[4];
        m[0] = new Student();
        m[1] = new Student();
        m[2] = new Teacher();
        m[3] = new Teacher();

        for (Member m1 : m) {
            m1.welcomeMessage(); // calls correct version per object
        }
    }
}
```

### Output

```
Hello Student...
Hello Student...
Hello Student...
Hello Teacher...
Hello Teacher...
```

---

## Key Takeaways

- **Abstract** = concept/idea, cannot exist on its own → use `abstract class`.
- **Concrete** = real thing, can be created → use regular class.
- Abstract methods have **no body** — they force subclasses to provide the implementation.
- A class with an abstract method **must** be declared abstract.
- Abstract classes **cannot be instantiated** — they can only be subclassed.
- Subclasses **must implement** all abstract methods, or must themselves be abstract.
- Abstract classes can have **both abstract and concrete methods**.
- Using a parent reference (`Member[] m`) enables **polymorphism** — one loop, many behaviors.
- The abstract method in the parent is a **contract** that makes calling the method via parent reference safe and valid.