# Java Interfaces — Complete Teacher-to-Student Guide
> *From Zero to Expert — with Diagrams, Code & Real-World Examples*

---

## Table of Contents
1. [What is an Interface?](#1-what-is-an-interface)
2. [Why Do We Need Interfaces?](#2-why-do-we-need-interfaces)
3. [Rules to Remember BEFORE Creating an Interface](#3-rules-to-remember-before-creating-an-interface)
4. [Interface — Key Concepts A to Z](#4-interface--key-concepts-a-to-z)
5. [Interface vs Abstract Class](#5-interface-vs-abstract-class)
6. [Multiple Inheritance via Interfaces](#6-multiple-inheritance-via-interfaces)
7. [Can an Interface Extend a Class?](#7-can-an-interface-extend-a-class)
8. [Real-World Practical Example — Smart Home System](#8-real-world-practical-example--smart-home-system)
9. [Complete Rules Reference](#9-complete-rules-reference)
10. [Interview Quick Reference](#10-interview-quick-reference)
11. [Key Takeaways](#11-key-takeaways)

---

## 1. What is an Interface?

Before we write a single line of code, let's understand the idea using an everyday example.

### Real-World Analogy: The Power Socket

Think about a power socket on your wall. Every device — your phone charger, laptop, fan, or TV — has a plug that fits that socket. The socket does **not** care what the device does internally. It only enforces one rule:

> **"If you want power from this socket, you MUST have the correct plug."**
>
> The socket defines **WHAT** is needed (a plug with specific pins).
> Each device decides **HOW** it uses that power.

An interface in Java works exactly the same way.

```
┌──────────────────────────────────────────────────────────┐
│                    INTERFACE = CONTRACT                  │
│                                                          │
│   Defines  →  WHAT a class must do                       │
│   Hides    →  HOW it does it                             │
│   Enforces →  Every implementing class MUST comply       │
└──────────────────────────────────────────────────────────┘
```

### The Official Definition

> An **interface** in Java is a reference type that can contain:
> - Abstract method declarations (no body) — default before Java 8
> - `default` methods with a body — since **Java 8**
> - `static` methods with a body — since **Java 8**
> - `private` methods — since **Java 9**
> - Constants (`public static final` variables)
>
> A class **implements** an interface and must provide a body for every abstract method.

### Basic Syntax

```java
// Declaring an interface
interface Printable {
    void print();     // abstract by default — no body needed
}

// Implementing an interface
class Document implements Printable {
    @Override
    public void print() {
        System.out.println("Printing the document...");
    }
}

// Using it
public class Main {
    public static void main(String[] args) {
        Printable p = new Document();
        p.print();   // Output: Printing the document...
    }
}
```

---

## 2. Why Do We Need Interfaces?

This is the most important question. Let's see the **problem first**, then the **solution**.

### The Problem — Without Interfaces

```java
// ❌ BAD — No interface, tightly coupled
class EmailSender {
    void sendEmail(String msg) {
        System.out.println("Sending EMAIL: " + msg);
    }
}

class NotificationService {
    // Directly depends on EmailSender — cannot swap it!
    EmailSender emailSender = new EmailSender();

    void notify(String msg) {
        emailSender.sendEmail(msg);  // STUCK with Email only!
    }
}
```

**Problems with this approach:**
1. If we want to switch to SMS, we must **rewrite** `NotificationService`
2. We cannot **test** `NotificationService` independently
3. We cannot support **multiple** notification types at once
4. Every new notification type **breaks existing code**

### The Solution — With Interface

```java
// ✅ GOOD — Interface defines the contract
interface Notifiable {
    void sendNotification(String message);
}

class EmailSender implements Notifiable {
    public void sendNotification(String message) {
        System.out.println("📧 Email: " + message);
    }
}

class SMSSender implements Notifiable {
    public void sendNotification(String message) {
        System.out.println("📱 SMS: " + message);
    }
}

class PushNotification implements Notifiable {
    public void sendNotification(String message) {
        System.out.println("🔔 Push: " + message);
    }
}

// NotificationService depends on the INTERFACE, not a specific class
class NotificationService {
    private Notifiable notifier;  // Can be ANY implementation

    NotificationService(Notifiable notifier) {
        this.notifier = notifier;
    }

    void notify(String message) {
        notifier.sendNotification(message);
    }
}

public class Main {
    public static void main(String[] args) {
        NotificationService s1 = new NotificationService(new EmailSender());
        s1.notify("Welcome!");   // 📧 Email: Welcome!

        NotificationService s2 = new NotificationService(new SMSSender());
        s2.notify("Your OTP");   // 📱 SMS: Your OTP
    }
}
```

**Benefits gained:**

| Benefit | Explanation |
|---------|-------------|
| ✅ Swap freely | Change `EmailSender` → `SMSSender` with zero changes to `NotificationService` |
| ✅ Extensible | Add `WhatsAppSender` without touching any existing code |
| ✅ Testable | Mock `Notifiable` in unit tests |
| ✅ Contract enforced | Every notifier is **guaranteed** to have `sendNotification()` |

---

## 3. Rules to Remember BEFORE Creating an Interface

> 🏫 **Teacher says:** Before you type `interface`, ask yourself these questions. These rules will save you from bad design decisions.

---

### Rule 1 — Ask: "Is this a CAPABILITY or a TYPE?"

```
Is the thing you're modeling a CAPABILITY that many unrelated classes share?
         │
        YES → Use an Interface
         │
        NO → Is it a shared TYPE with common state/behavior?
                │
               YES → Use an Abstract Class
```

**Examples:**
- `Flyable` → capability → **Interface** (Bird, Airplane, Drone can all fly — they are unrelated)
- `Animal` → shared type → **Abstract Class** (Dog, Cat share `name`, `age`, `eat()`)

---

### Rule 2 — An Interface Should Have NO State

Interfaces **cannot** have instance variables. If your design requires shared state (fields that hold data), use an abstract class instead.

```java
// ❌ WRONG thinking — don't try to add state to an interface
interface BankAccount {
    double balance = 0;    // This is public static final — a CONSTANT, not state!
    void deposit(double amount);
}

// ✅ CORRECT — if you need state, use abstract class
abstract class BankAccount {
    protected double balance = 0;    // real instance variable
    abstract void deposit(double amount);
}
```

> **Rule:** If you need fields that each object owns separately → **Abstract Class**, not Interface.

---

### Rule 3 — Name Interfaces as Capabilities (Adjectives)

Good interface names describe a **capability**, often ending in `-able` or `-ible`.

| ✅ Good Names | ❌ Bad Names |
|--------------|-------------|
| `Printable`  | `PrintInterface` |
| `Serializable` | `ISerializable` |
| `Flyable`    | `FlyingThing` |
| `Comparable` | `CompareClass` |
| `Runnable`   | `ThreadInterface` |

---

### Rule 4 — Keep Interfaces Focused (Interface Segregation Principle)

Do **not** stuff every method into one fat interface. Split it into smaller, focused contracts.

```java
// ❌ FAT interface — forces classes to implement methods they don't need
interface Worker {
    void code();
    void test();
    void manage();
    void design();
}

// ✅ SEGREGATED interfaces — each class implements only what it needs
interface Coder   { void code(); }
interface Tester  { void test(); }
interface Manager { void manage(); }
interface Designer{ void design(); }

class Developer implements Coder, Tester {    // only what's relevant
    public void code() { System.out.println("Writing code..."); }
    public void test() { System.out.println("Running tests..."); }
}
```

> **Rule:** Each interface should have **one clear responsibility**. If you find yourself adding unrelated methods, split it.

---

### Rule 5 — An Interface Is a PUBLIC Contract

All methods in an interface are `public` by default. There is no such thing as a `private` abstract method or a `protected` abstract method in an interface.

```java
interface Example {
    void methodA();              // public abstract — ✅
    public void methodB();       // same as above — ✅ (redundant but valid)
    // private void methodC();   // ❌ NOT allowed (private abstract makes no sense)
    // protected void methodD(); // ❌ NOT allowed
}
```

> **Exception:** `private` methods are allowed since Java 9, but only as **helper methods** for `default` methods — they are never abstract.

---

### Rule 6 — A Class Can Implement Many Interfaces, But Extend Only One Class

```java
// ✅ Implement as many interfaces as needed
class SmartPhone implements Callable, Browsable, Playable, Chargeable { ... }

// ❌ Cannot extend more than one class
class SmartPhone extends Nokia, Samsung { ... }  // COMPILE ERROR
```

> **Rule:** Use interfaces when you need a class to fulfil multiple contracts. Use inheritance only for a single parent-child type relationship.

---

### Rule 7 — Every Abstract Method MUST Be Implemented

If a class implements an interface, it **must** provide a body for every abstract method — or the class itself must be declared `abstract`.

```java
interface Greetable {
    void greet();
    void farewell();
}

// ❌ Missing farewell() — COMPILE ERROR
class Friend implements Greetable {
    public void greet() { System.out.println("Hello!"); }
    // farewell() not implemented — error!
}

// ✅ Option A: implement both
class Friend implements Greetable {
    public void greet()   { System.out.println("Hello!"); }
    public void farewell(){ System.out.println("Goodbye!"); }
}

// ✅ Option B: declare the class abstract and defer
abstract class Friend implements Greetable {
    public void greet() { System.out.println("Hello!"); }
    // farewell() left for subclass to implement
}
```

---

### Rule 8 — Resolve Default Method Conflicts Explicitly

If two interfaces have a `default` method with the **same name**, the implementing class **must** override it.

```java
interface A { default void hello() { System.out.println("Hello from A"); } }
interface B { default void hello() { System.out.println("Hello from B"); } }

// ❌ COMPILE ERROR if you don't override hello()
class C implements A, B {
    @Override
    public void hello() {
        A.super.hello();   // explicitly pick A's version, or write your own
    }
}
```

---

### Rule 9 — Interfaces Cannot Have Constructors

You can never instantiate an interface. It has no constructor.

```java
interface Flyable { void fly(); }

Flyable f = new Flyable();   // ❌ COMPILE ERROR
```

> **But** you CAN use an interface as a reference type:
> ```java
> Flyable f = new Bird();    // ✅ reference is Flyable, object is Bird
> ```

---

### Rule 10 — Think "Programming to an Interface, Not to an Implementation"

This is the golden rule of good OOP design.

```java
// ❌ Programming to the implementation (brittle)
ArrayList<String> list = new ArrayList<>();

// ✅ Programming to the interface (flexible)
List<String> list = new ArrayList<>();
// Can switch to LinkedList tomorrow with zero other changes
```

> **Rule:** Your variables, parameters, and return types should be the **interface type**, not the concrete class type — whenever possible.

---

## 4. Interface — Key Concepts A to Z

### 4.1 All Methods Are `public abstract` by Default

```java
interface Shape {
    double area();              // same as: public abstract double area();
    double perimeter();         // same as: public abstract double perimeter();
}
```

> 💡 The compiler adds `public abstract` for you. Writing it yourself is redundant but not wrong.

---

### 4.2 All Fields Are `public static final` by Default

```java
interface MathConstants {
    double PI = 3.14159;        // same as: public static final double PI = 3.14159;
    int MAX_SIZE = 100;         // same as: public static final int MAX_SIZE = 100;
}
```

---

### 4.3 No Constructors Allowed

```java
interface Flyable { void fly(); }

Flyable f = new Flyable();   // ❌ COMPILE ERROR — cannot instantiate interface
```

---

### 4.4 `default` Methods (Java 8+)

Since Java 8, interfaces can have `default` methods — methods **with a body**. A class that implements the interface gets this method for free unless it overrides it.

```java
interface Vehicle {
    void start();                     // abstract — must implement

    default void fuelCheck() {        // default — free implementation
        System.out.println("Checking fuel level...");
    }
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car started!");
    }
    // fuelCheck() is inherited automatically — no need to override
}

Car c = new Car();
c.start();       // Car started!
c.fuelCheck();   // Checking fuel level...
```

---

### 4.5 `static` Methods (Java 8+)

Static methods in interfaces belong to the interface itself — they are **not** inherited by implementing classes.

```java
interface MathUtils {
    static int square(int n) { return n * n; }
}

// Call using the interface name directly
int result = MathUtils.square(5);   // 25

// You CANNOT call it like this:
// MyClass.square(5);   // ❌ — not inherited
```

---

### 4.6 `private` Methods (Java 9+)

`private` methods in interfaces are helper methods that avoid code duplication among `default` methods.

```java
interface Logger {
    default void logInfo(String msg)  { log("INFO", msg);  }
    default void logError(String msg) { log("ERROR", msg); }

    private void log(String level, String msg) {  // shared helper
        System.out.println("[" + level + "] " + msg);
    }
}
```

---

## 5. Interface vs Abstract Class

```
┌─────────────────────────────────────────────────────────────┐
│                    INTERFACE                                │
│  • Pure contract (WHAT to do)                               │
│  • All abstract by default  • No constructors               │
│  • Multiple inheritance supported                           │
│  • 'implements' keyword      • CAN-DO relationship          │
└─────────────────────────────────────────────────────────────┘
                            vs
┌─────────────────────────────────────────────────────────────┐
│                 ABSTRACT CLASS                              │
│  • Partial blueprint (WHAT + some HOW)                      │
│  • Mix of abstract + concrete methods                       │
│  • Has constructors + instance fields                       │
│  • 'extends' keyword         • IS-A relationship            │
└─────────────────────────────────────────────────────────────┘
```

### Detailed Comparison Table

| Feature | Interface | Abstract Class |
|---------|-----------|----------------|
| Keyword | `interface` | `abstract class` |
| Instantiation | Cannot be instantiated | Cannot be instantiated |
| Methods | Abstract by default (`+default`/`static` Java 8+) | Mix of abstract and concrete |
| Fields / Variables | Only `public static final` (constants) | Any type of instance variables |
| Constructors | ❌ Not allowed | ✅ Allowed (called via `super()`) |
| Access Modifiers | Methods are `public` by default | Any (`private`, `protected`, `public`) |
| Multiple Inheritance | ✅ A class can implement many interfaces | ❌ A class can extend only ONE |
| Relationship | CAN-DO (`Flyable`, `Printable`, `Serializable`) | IS-A (`Car` IS-A `Vehicle`) |
| When to Use | Define a capability across unrelated classes | Share code among closely related classes |
| Inheritance Keyword | `implements` | `extends` |

### Decision Guide

```
Do the classes share COMMON STATE (fields) or COMMON BEHAVIOR (concrete methods)?
         |                           |
        YES                          NO
         |                           |
  Use ABSTRACT CLASS         Use INTERFACE
  (shared fields +           (pure contract,
   shared methods)            multiple impl.)

Examples:
  Abstract Class → Animal (Dog, Cat share name, age, eat())
  Interface      → Flyable (Bird, Airplane, Drone — unrelated but can fly)
```

### Code Comparison

```java
// ── ABSTRACT CLASS approach ──────────────────────────────────
abstract class Payment {
    private String orderId;
    private double amount;

    Payment(String orderId, double amount) {
        this.orderId = orderId;
        this.amount  = amount;
    }

    // Shared method — all payments use this
    void logTransaction() {
        System.out.println("TXN " + orderId + " | ₹" + amount);
    }

    abstract void processPayment();  // each payment type decides HOW
}

class UPIPayment extends Payment {
    UPIPayment(String id, double amt) { super(id, amt); }

    public void processPayment() {
        System.out.println("Processing via UPI...");
        logTransaction();            // reuses shared method
    }
}
```

```java
// ── INTERFACE approach ───────────────────────────────────────
interface Payable {
    void processPayment(double amount);
    boolean refund(String transactionId);
}

// An unrelated class like Wallet can also implement Payable
class WalletPayment implements Payable {
    public void processPayment(double amount) {
        System.out.println("Deducting ₹" + amount + " from wallet");
    }
    public boolean refund(String txnId) {
        System.out.println("Refunding TXN " + txnId);
        return true;
    }
}
```

> 🏫 **Teacher's Rule:** If the classes are **closely related AND share real state** → Abstract Class. If you just need a **shared contract across any class** → Interface.

---

## 6. Multiple Inheritance via Interfaces

### Why Java Removed Multiple Class Inheritance

```
            A
           / \
          B   C
           \ /
            D

Both B and C inherit from A.
D extends both B and C.
If A has greet(), and B and C both override it differently,
which version does D get?  → AMBIGUITY! (Diamond Problem)

Java's solution: Disallow extending two classes.
Use interfaces instead — the compiler resolves ambiguity clearly.
```

### Multiple Inheritance in Action

A class in Java can implement **as many interfaces as it wants**.

```java
interface Flyable {
    void fly();
    default String getType() { return "Flying Object"; }
}

interface Swimmable {
    void swim();
    default String getType() { return "Swimming Object"; }
}

// Duck can BOTH fly and swim — multiple inheritance!
class Duck implements Flyable, Swimmable {

    public void fly() {
        System.out.println("Duck is flying!");
    }

    public void swim() {
        System.out.println("Duck is swimming!");
    }

    // Both Flyable and Swimmable have getType() — must override to resolve conflict
    @Override
    public String getType() {
        return "Duck — can fly AND swim";
    }
}

public class Main {
    public static void main(String[] args) {
        Duck d = new Duck();
        d.fly();         // Duck is flying!
        d.swim();        // Duck is swimming!
        System.out.println(d.getType());  // Duck — can fly AND swim

        // Polymorphism in action!
        Flyable   f = new Duck();   // Duck IS-A Flyable  ✅
        Swimmable s = new Duck();   // Duck IS-A Swimmable ✅
    }
}
```

### Class Diagram

```
     <<interface>>          <<interface>>
       Flyable                Swimmable
      fly()                  swim()
      getType()               getType()
           \                    /
            \                  /
         implements        implements
                \          /
                 v        v
                   Duck
               fly()  swim()  getType() (overridden)

Duck IS-A Flyable    ✅
Duck IS-A Swimmable  ✅
Duck IS-A Object     ✅
```

### Resolving Default Method Conflicts

```java
interface A { default void hello() { System.out.println("Hello from A"); } }
interface B { default void hello() { System.out.println("Hello from B"); } }

class C implements A, B {
    // MUST override hello() — otherwise compile error
    @Override
    public void hello() {
        A.super.hello();   // explicitly choose A's version, or write your own
    }
}
```

> 🎓 **Key Insight:** Interfaces give you the freedom of multiple inheritance **without** the ambiguity of the diamond problem — the compiler forces you to resolve any conflict explicitly.

---

## 7. Can an Interface Extend a Class?

### Short Answer

```
❌  An interface CANNOT extend a class (not even an abstract class).
✅  An interface CAN extend one or more OTHER interfaces.
```

**Why?** Because an interface is a pure contract. Extending a class would inherit state (fields) and implementation — which would break the purity of the interface.

---

### Interface Extending Another Interface

```java
interface Animal {
    void breathe();
}

interface Pet extends Animal {          // ✅ interface extends interface
    void play();
}

interface ServiceAnimal extends Pet {   // ✅ chaining interfaces
    void performDuty();
}

// Any class implementing ServiceAnimal must provide ALL three methods
class GuideDog implements ServiceAnimal {
    public void breathe()     { System.out.println("Dog breathing");      }
    public void play()        { System.out.println("Dog playing");        }
    public void performDuty() { System.out.println("Guiding the blind");  }
}
```

### Interface Inheritance Chain Diagram

```
  <<interface>>
    Animal         breathe()
       |
     extends
       |
  <<interface>>
     Pet           breathe()  +  play()
       |
     extends
       |
  <<interface>>
  ServiceAnimal    breathe()  +  play()  +  performDuty()
       |
   implements
       |
    GuideDog   ← must implement ALL 3 methods
```

---

### Interface Extending Multiple Interfaces

```java
interface Flyable   { void fly();  }
interface Swimmable { void swim(); }
interface Walkable  { void walk(); }

// An interface that combines all three
interface Amphibious extends Flyable, Swimmable, Walkable {
    void surviveHarshConditions();
}

class AmphibiousDrone implements Amphibious {
    public void fly()                    { System.out.println("Drone flying");      }
    public void swim()                   { System.out.println("Drone swimming");     }
    public void walk()                   { System.out.println("Drone walking");      }
    public void surviveHarshConditions() { System.out.println("All-terrain mode");  }
}
```

---

### Why Can't an Interface Extend a Class — Deep Dive

```java
// Imagine if this was ALLOWED (it is NOT):
class BankAccount {
    private double balance = 0;   // STATE (instance variable)
    void deposit(double amt) { balance += amt; }
}

// ❌ NOT ALLOWED — interface cannot extend a class
interface Transaction extends BankAccount {
    void withdraw(double amount);
}

// PROBLEM: If allowed, Transaction would inherit 'balance' and 'deposit()'
// This means EVERY class implementing Transaction would secretly carry
// a bank balance — completely nonsensical and dangerous!

// Java says: "Interfaces are contracts about BEHAVIOR, not STATE."
```

### The Mental Model

```
Interface  →  extends    →  Interface      ✅  (contract inherits contract)
Interface  →  extends    →  Class          ❌  (contracts cannot inherit state)
Class      →  extends    →  Abstract Class ✅  (class inherits class)
Class      →  implements →  Interface      ✅  (class fulfils contract)
Class      →  extends ONE Class + implements MANY Interfaces  ✅
```

---

## 8. Real-World Practical Example — Smart Home System

Let's build a **Smart Home system** from scratch using interfaces. This demonstrates every concept we have learned.

### The Scenario

```
A smart home has many types of devices:
  • Smart Bulb       — can be switched ON/OFF and dimmed
  • Smart TV         — can be switched ON/OFF and can stream
  • Smart Thermostat — can be switched ON/OFF and set temperature
  • Smart Speaker    — can be switched ON/OFF and play music

ALL devices share:   Switchable (ON/OFF)
SOME devices share:  Streamable (TV, Speaker)
ONE device has:      Dimmable (Bulb)

This is the PERFECT use case for interfaces.
```

### Class & Interface Diagram

```
         <<interface>>        <<interface>>       <<interface>>
          Switchable           Streamable           Dimmable
         turnOn()             stream(String)       setDimLevel(int)
         turnOff()            stopStream()         getDimLevel()
         isOn()
         toggle() [default]
              |                    |                   |
     ─────────┼────────────────────┼───────────────────┼──────
     |         |                   |                   |
  SmartBulb  SmartTV          SmartSpeaker         SmartBulb
 implements  implements        implements          implements
 Switchable  Switchable        Switchable          Dimmable
  Dimmable   Streamable        Streamable
```

---

### Step 1 — Define the Interfaces (Contracts)

```java
// Contract 1: Every device must support ON/OFF
interface Switchable {
    void turnOn();
    void turnOff();
    boolean isOn();

    // Default method — shared utility
    default void toggle() {
        if (isOn()) turnOff();
        else        turnOn();
    }
}

// Contract 2: Streaming devices
interface Streamable {
    void stream(String contentName);
    void stopStream();
}

// Contract 3: Dimmable devices
interface Dimmable {
    void setDimLevel(int percent);  // 0–100
    int  getDimLevel();
}
```

---

### Step 2 — Implement the Devices

```java
// Smart Bulb: implements Switchable AND Dimmable
class SmartBulb implements Switchable, Dimmable {
    private boolean on       = false;
    private int     dimLevel = 100;

    public void turnOn()  { on = true;  System.out.println("💡 Bulb ON");  }
    public void turnOff() { on = false; System.out.println("💡 Bulb OFF"); }
    public boolean isOn() { return on; }

    public void setDimLevel(int percent) {
        dimLevel = Math.max(0, Math.min(100, percent));
        System.out.println("💡 Brightness: " + dimLevel + "%");
    }
    public int getDimLevel() { return dimLevel; }
}

// Smart TV: implements Switchable AND Streamable
class SmartTV implements Switchable, Streamable {
    private boolean on = false;

    public void turnOn()  { on = true;  System.out.println("📺 TV ON");  }
    public void turnOff() { on = false; System.out.println("📺 TV OFF"); }
    public boolean isOn() { return on; }

    public void stream(String name) {
        System.out.println("📺 Streaming: " + name);
    }
    public void stopStream() {
        System.out.println("📺 Stopped streaming");
    }
}

// Smart Speaker: implements Switchable AND Streamable
class SmartSpeaker implements Switchable, Streamable {
    private boolean on = false;

    public void turnOn()  { on = true;  System.out.println("🔊 Speaker ON");  }
    public void turnOff() { on = false; System.out.println("🔊 Speaker OFF"); }
    public boolean isOn() { return on; }

    public void stream(String name) {
        System.out.println("🔊 Playing: " + name);
    }
    public void stopStream() {
        System.out.println("🔊 Music stopped");
    }
}
```

---

### Step 3 — The Smart Home Controller (Uses Abstractions)

```java
import java.util.ArrayList;
import java.util.List;

class SmartHomeController {

    // A list of ALL devices — only knows about Switchable contract
    private List<Switchable> allDevices = new ArrayList<>();

    void addDevice(Switchable device) {
        allDevices.add(device);
    }

    // Turn off every device in the house — ONE method, ALL devices!
    void goodNightMode() {
        System.out.println("\n🌙 Good Night Mode — turning everything OFF");
        for (Switchable device : allDevices) {
            device.turnOff();
        }
    }

    void goodMorningMode() {
        System.out.println("\n☀️  Good Morning Mode — turning everything ON");
        for (Switchable device : allDevices) {
            device.turnOn();
        }
    }

    void movieNight(List<Streamable> streamDevices, String movie) {
        System.out.println("\n🎬 Movie Night: " + movie);
        for (Streamable device : streamDevices) {
            device.stream(movie);
        }
    }
}
```

---

### Step 4 — Running the Full System

```java
public class SmartHome {
    public static void main(String[] args) {

        SmartBulb    bulb    = new SmartBulb();
        SmartTV      tv      = new SmartTV();
        SmartSpeaker speaker = new SmartSpeaker();

        SmartHomeController home = new SmartHomeController();
        home.addDevice(bulb);
        home.addDevice(tv);
        home.addDevice(speaker);

        // Good morning!
        home.goodMorningMode();
        // ☀️  Good Morning Mode — turning everything ON
        // 💡 Bulb ON   📺 TV ON   🔊 Speaker ON

        // Dim the bulb
        bulb.setDimLevel(40);
        // 💡 Brightness: 40%

        // Movie night
        List<Streamable> streamers = List.of(tv, speaker);
        home.movieNight(streamers, "Inception");
        // 🎬 Movie Night: Inception
        // 📺 Streaming: Inception
        // 🔊 Playing: Inception

        // Good night!
        home.goodNightMode();
        // 🌙 Good Night Mode — turning everything OFF
        // 💡 Bulb OFF   📺 TV OFF   🔊 Speaker OFF

        // Toggle using the default method from Switchable
        bulb.toggle();
        // 💡 Bulb ON
    }
}
```

---

### What This Example Demonstrates

| Concept | Where It Appears | What You Learn |
|---------|-----------------|----------------|
| Multiple interfaces | `SmartBulb implements Switchable, Dimmable` | One class can fulfil multiple contracts |
| `default` method | `Switchable.toggle()` | Shared utility code in an interface |
| Polymorphism | `List<Switchable> allDevices` | All devices treated uniformly via interface |
| Loose coupling | Controller uses `Switchable` — not `SmartBulb` directly | Add any new device with zero changes to controller |
| Interface segregation | `Streamable` is separate from `Switchable` | Classes only implement what they need |

---

# Java Method Priority: Class vs Interface

## Scenario

In Java, a class can **extend a class** and **implement an interface**
at the same time.

Sometimes both the **parent class** and the **interface** may have a
method with the **same name**.

The question is:

> Which method will Java call?

------------------------------------------------------------------------

# Example

## Interface

``` java
interface A {
    default void printName() {
        System.out.println("Interface A");
    }
}
```

## Parent Class

``` java
class B {
    void printName() {
        System.out.println("Class B");
    }
}
```

## Child Class

``` java
class C extends B implements A {
}
```

## Main Class

``` java
public class Main {
    public static void main(String[] args) {

        C obj = new C();
        obj.printName();

    }
}
```

------------------------------------------------------------------------

# Output

    Class B

------------------------------------------------------------------------

# Why?

Java gives **higher priority to class methods** than interface default
methods.

Priority order:

    Class method > Interface default method

So Java chooses the method from **Class B** instead of the interface.

------------------------------------------------------------------------

# Full Priority Rule

Java resolves methods in this order:

    1. Method in Child Class
    2. Method in Parent Class
    3. Default Method in Interface

Or simply:

    Class > Interface

------------------------------------------------------------------------

# If Child Class Overrides the Method

If the child class defines its own method, it gets the highest priority.

``` java
class C extends B implements A {

    public void printName() {
        System.out.println("Class C");
    }

}
```

Output:

    Class C

New priority order:

    C > B > A

------------------------------------------------------------------------

# Special Case: Multiple Interfaces

If two interfaces provide the same default method:

``` java
interface A {
    default void show() {}
}

interface B {
    default void show() {}
}

class C implements A, B {}
```

Java will give a **compile-time error**.

You must override the method.

``` java
class C implements A, B {

    public void show() {
        System.out.println("Resolved conflict");
    }

}
```

------------------------------------------------------------------------

# Key Takeaway

Java always prefers **class methods over interface default methods**.

Priority rule:

    Child Class > Parent Class > Interface

------------------------------------------------------------------------

# Interview One-Line Answer

If both a class and an interface contain the same method, **Java gives
priority to the class method over the interface default method**.
# Java Method Priority: Class vs Interface

## Scenario

In Java, a class can **extend a class** and **implement an interface**
at the same time.

Sometimes both the **parent class** and the **interface** may have a
method with the **same name**.

The question is:

> Which method will Java call?

------------------------------------------------------------------------

# Example

## Interface

``` java
interface A {
    default void printName() {
        System.out.println("Interface A");
    }
}
```

## Parent Class

``` java
class B {
    void printName() {
        System.out.println("Class B");
    }
}
```

## Child Class

``` java
class C extends B implements A {
}
```

## Main Class

``` java
public class Main {
    public static void main(String[] args) {

        C obj = new C();
        obj.printName();

    }
}
```

------------------------------------------------------------------------

# Output

    Class B

------------------------------------------------------------------------

# Why?

Java gives **higher priority to class methods** than interface default
methods.

Priority order:

    Class method > Interface default method

So Java chooses the method from **Class B** instead of the interface.

------------------------------------------------------------------------

# Full Priority Rule

Java resolves methods in this order:

    1. Method in Child Class
    2. Method in Parent Class
    3. Default Method in Interface

Or simply:

    Class > Interface

------------------------------------------------------------------------

# If Child Class Overrides the Method

If the child class defines its own method, it gets the highest priority.

``` java
class C extends B implements A {

    public void printName() {
        System.out.println("Class C");
    }

}
```

Output:

    Class C

New priority order:

    C > B > A

------------------------------------------------------------------------

# Special Case: Multiple Interfaces

If two interfaces provide the same default method:

``` java
interface A {
    default void show() {}
}

interface B {
    default void show() {}
}

class C implements A, B {}
```

Java will give a **compile-time error**.

You must override the method.

``` java
class C implements A, B {

    public void show() {
        System.out.println("Resolved conflict");
    }

}
```

------------------------------------------------------------------------

# Key Takeaway

Java always prefers **class methods over interface default methods**.

Priority rule:

    Child Class > Parent Class > Interface

------------------------------------------------------------------------

# Interview One-Line Answer

If both a class and an interface contain the same method, **Java gives
priority to the class method over the interface default method**.


## 9. Complete Rules Reference

| Rule | Detail |
|------|--------|
| Keyword | `interface MyInterface { }` |
| Implement | `class MyClass implements MyInterface { }` |
| Multiple implements | `class MyClass implements A, B, C { }` |
| Interface extends interface | `interface B extends A { }` — ✅ allowed |
| Interface extends class | ❌ **NOT allowed — ever** |
| Instantiation | `new MyInterface()` — ❌ compile error |
| Abstract methods | `public` + `abstract` by default — no body |
| Fields | `public static final` by default — constants only |
| Constructors | ❌ Not allowed in interfaces |
| `default` methods | Allowed since Java 8 — has a body, inherited by class |
| `static` methods | Allowed since Java 8 — NOT inherited by implementing class |
| `private` methods | Allowed since Java 9 — helper for `default` methods only |
| Must implement all methods | Or the implementing class must be `abstract` |
| Conflict resolution | If two interfaces share a `default` method name, class **must** override |
| Reference type | `Flyable f = new Bird();` — ✅ valid |

---

## 10. Interview Quick Reference

### Q: Can an interface extend a class?
> **No.** An interface can only extend other interfaces, not classes. The reason: extending a class would inherit instance state, which violates the principle that interfaces are pure contracts.

---

### Q: How does Java achieve multiple inheritance?
> Java allows a class to implement multiple interfaces:
> ```java
> class Duck implements Flyable, Swimmable { }
> ```
> This gives multiple inheritance of **type** and **behavior**. If two interfaces have conflicting `default` methods, the class **must** override to resolve.

---

### Q: What is the difference between interface and abstract class?
> - **Abstract class:** partial blueprint — has state + shared behavior + constructors.
> - **Interface:** pure contract — no state, methods are abstract by default, multiple allowed.
> - **Rule of thumb:** `IS-A type` → abstract class. `CAN-DO capability` → interface.

---

### Q: Can an interface have a constructor?
> **No.** Interfaces cannot be instantiated, so constructors make no sense. Any shared initialisation logic must go in the implementing class.

---

### Q: What are `default` methods and why were they added?
> `default` methods (Java 8) allow interfaces to add new methods **without breaking** existing implementing classes. Before Java 8, adding a method to an interface would force every implementing class to add it — breaking all existing code.

---

### Q: What is "programming to an interface"?
> It means your variables, parameters, and return types should use the **interface type**, not the concrete class. This makes code flexible, testable, and easy to extend.
> ```java
> List<String> list = new ArrayList<>();  // ✅ interface type
> ```

---

## 11. Key Takeaways

| # | Rule | Remember This |
|---|------|---------------|
| 1 | Interface = Contract | Defines WHAT, never HOW |
| 2 | Use interface for capability | `Flyable`, `Printable`, `Serializable` — unrelated classes sharing a skill |
| 3 | Use abstract class for type | `Animal → Dog, Cat` — related classes sharing state |
| 4 | Multiple inheritance | A class can `implement` as many interfaces as needed |
| 5 | Interface cannot extend class | Only interfaces can extend interfaces |
| 6 | `default` methods (Java 8) | Provide shared utility code without breaking old implementations |
| 7 | Program to the interface | Depend on abstract types, not concrete classes |
| 8 | Keep interfaces small | One responsibility per interface (Interface Segregation Principle) |
| 9 | Name with capability adjectives | `Runnable`, `Comparable`, `Serializable` — not `IRunner` |
| 10 | Conflict resolution | Two interfaces, same `default` method → implementing class MUST override |

---

> *"Good code depends on abstractions, not implementations."*
> — Interface-Driven Design