# Java OOP — Interview Questions & Answers

> **Format:** Every answer follows **Definition → Example → Real-World Analogy**

---

# 1. OOP Fundamentals

---

### Q1. What is Object-Oriented Programming (OOP)?

**Definition:**
OOP is a programming paradigm where we design software around **objects** — instances of classes. Each object bundles its own data (fields) and behavior (methods) together.

**Example:**
```java
class Car {
    String brand;
    int speed;
    void drive() {
        System.out.println(brand + " is driving at " + speed + " km/h");
    }
}
Car myCar = new Car();
myCar.brand = "Toyota";
myCar.speed = 120;
myCar.drive(); // Toyota is driving at 120 km/h
```

**Real-World Analogy:**
A **TV remote** is an object — it has data (brand, battery level) and behavior (changeChannel, increaseVolume). You interact through its buttons (methods), not by touching the circuit board (data).

---

### Q2. What are the four pillars of OOP?

**Definition:**
1. **Encapsulation** — Wrapping data and methods together, restricting direct access
2. **Abstraction** — Hiding complex implementation, showing only what's necessary
3. **Inheritance** — One class acquires properties and behaviors of another
4. **Polymorphism** — Same method name behaves differently based on the object

**Example:**
```java
// Encapsulation — private fields + public methods
class Account { private double balance; public void deposit(double amt) { if(amt>0) balance+=amt; } }
// Abstraction — abstract method hides "how"
abstract class Shape { abstract double area(); }
// Inheritance — child reuses parent
class Dog extends Animal { }
// Polymorphism — same method, different behavior
Animal a = new Dog(); a.sound();
```

**Real-World Analogy:**
Think of a **car**: Encapsulation = engine covered by hood. Abstraction = press accelerator, don't know how fuel injection works. Inheritance = Electric Car inherits basic car features. Polymorphism = `start()` works differently for petrol vs electric car.

---

### Q3. Explain Encapsulation with an example.

**Definition:**
Encapsulation means bundling data and methods inside a class, and **restricting direct access** using `private`. You expose controlled access through `public` getters and setters.

**Example:**
```java
class BankAccount {
    private double balance;
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }
    public double getBalance() { return balance; }
}
BankAccount acc = new BankAccount();
acc.deposit(5000);
// acc.balance = -999; // ❌ NOT allowed
System.out.println(acc.getBalance()); // 5000.0
```

**Real-World Analogy:**
A **capsule pill**. The medicine (data) is wrapped inside. You can't directly touch the powder — you interact through the outer shell. Similarly, you interact with data through methods, not directly.

---

### Q4. Explain Abstraction with an example.

**Definition:**
Abstraction means hiding complex implementation details and exposing only the essential features. We define **what** an object does, not **how**.

**Example:**
```java
abstract class Payment {
    abstract void pay(double amount);
}
class UPI extends Payment {
    void pay(double amount) { System.out.println("Paid ₹" + amount + " via UPI"); }
}
Payment p = new UPI();
p.pay(500); // Paid ₹500.0 via UPI
```

**Real-World Analogy:**
An **ATM machine**. You insert card, enter PIN, withdraw cash. You don't know how the ATM communicates with the bank server internally. The complex process is **hidden** behind a simple interface.

---

### Q5. Explain Inheritance with an example.

**Definition:**
Inheritance allows a child class to acquire properties and methods of a parent class using `extends`. It promotes code reuse.

**Example:**
```java
class Animal {
    void eat() { System.out.println("Animal eats food"); }
}
class Dog extends Animal {
    void bark() { System.out.println("Dog barks"); }
}
Dog d = new Dog();
d.eat();  // inherited from Animal
d.bark(); // own method
```

**Real-World Analogy:**
A **child inheriting traits from parents** — eye color, height. You inherit traits automatically, plus develop your own unique traits. No need to build inherited traits from scratch.

---

### Q6. Explain Polymorphism with an example.

**Definition:**
Polymorphism means **"many forms."** Same method name behaves differently depending on the object. Two types: compile-time (overloading) and runtime (overriding).

**Example:**
```java
class Animal { void sound() { System.out.println("Some sound"); } }
class Cat extends Animal { void sound() { System.out.println("Meow"); } }
class Dog extends Animal { void sound() { System.out.println("Bark"); } }

Animal a1 = new Cat();
Animal a2 = new Dog();
a1.sound(); // Meow
a2.sound(); // Bark
```

**Real-World Analogy:**
The word **"open"**. You "open" a door, "open" a book, "open" a browser. Same word, different behavior depending on context.

---

### Q7. Difference between Class and Object.

**Definition:**

| Aspect | Class | Object |
|--------|-------|--------|
| What is it? | Blueprint/template | Instance of a class |
| Memory | No memory allocated | Memory allocated on creation |
| Created with | `class` keyword | `new` keyword |
| How many? | Defined once | Multiple from one class |

**Example:**
```java
class Student { String name; int age; }  // Class — blueprint
Student s1 = new Student(); s1.name = "Gowtham"; // Object — instance
Student s2 = new Student(); s2.name = "Ravi";    // Another object
```

**Real-World Analogy:**
A class is an **architect's blueprint** for a house. An object is an **actual house** built from it. One blueprint, many houses — each with its own address and furniture.

---

### Q8. What is a constructor?

**Definition:**
A constructor is a special method **automatically called when an object is created**. Same name as the class, no return type, used to initialize the object's state.

**Example:**
```java
class Employee {
    String name; double salary;
    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}
Employee e = new Employee("Gowtham", 75000);
```

**Real-World Analogy:**
When a **baby is born**, certain things are set immediately — name, birthdate. You don't call a separate method. A constructor sets up the object the moment it's born (`new`).

---

### Q9. What are types of constructors in Java?

**Definition:**
1. **Default constructor** — No parameters, provided by Java if none written
2. **Parameterized constructor** — Accepts parameters for specific initialization
3. **Copy constructor** — Takes another object and copies its values (manually written)

**Example:**
```java
class Student {
    String name; int age;
    Student() { name = "Unknown"; age = 0; }               // Default
    Student(String name, int age) { this.name=name; this.age=age; } // Parameterized
    Student(Student other) { this.name=other.name; this.age=other.age; } // Copy
}
```

**Real-World Analogy:**
Ordering **pizza**: Default = standard cheese pizza. Parameterized = large pepperoni with extra cheese. Copy = "same pizza that table ordered."

---

### Q10. Difference between default constructor and parameterized constructor.

**Definition:**

| Aspect | Default Constructor | Parameterized Constructor |
|--------|-------------------|--------------------------|
| Parameters | None | One or more |
| Purpose | Sets default values | Sets specific values |
| Auto-provided? | Yes, if none written | Must be written manually |

**Example:**
```java
class Book {
    String title; double price;
    Book() { title="Untitled"; price=0.0; }
    Book(String title, double price) { this.title=title; this.price=price; }
}
Book b1 = new Book();                    // Untitled, 0.0
Book b2 = new Book("Clean Code", 499);   // Clean Code, 499
```

**Real-World Analogy:**
Event registration: Default = show up as "Guest" with general seating. Parameterized = pre-register with name, email, preferred seat.

---

# 2. Encapsulation & Abstraction

---

### Q1. What is Encapsulation?

**Definition:**
Encapsulation is wrapping data (variables) and code (methods) together as a single unit (class), and **restricting direct access** to the data. Data is hidden behind `private` and exposed through controlled `public` methods.

**Example:**
```java
class User {
    private String password;
    public void setPassword(String password) {
        if (password.length() >= 8) this.password = password;
        else System.out.println("Password must be at least 8 characters");
    }
    public boolean authenticate(String input) { return this.password.equals(input); }
}
```

**Real-World Analogy:**
A **medicine capsule** — the drug is enclosed inside. You can't split it open and access chemicals directly. You consume the capsule as a whole.

---

### Q2. How do you achieve Encapsulation in Java?

**Definition:**
1. Declare fields as `private`
2. Provide `public` getter and setter methods
3. Add **validation logic** inside setters

**Example:**
```java
class Product {
    private String name;
    private double price;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { if (price >= 0) this.price = price; }
}
```

**Real-World Analogy:**
A **bank locker**. Your valuables (data) are inside. You go through bank staff (getters/setters) who verify your identity before giving access.

---

### Q3. Why do we use getters and setters?

**Definition:**
Getters and setters provide **controlled access** to private fields. They allow:
- **Validation** — reject invalid data
- **Logging** — track changes
- **Read-only** fields (only getter) or **write-only** fields (only setter)

**Example:**
```java
class Employee {
    private int age;
    public int getAge() { return age; }
    public void setAge(int age) {
        if (age >= 18 && age <= 65) this.age = age;
        else System.out.println("Invalid age for an employee");
    }
}
Employee e = new Employee();
e.setAge(10);  // Invalid age for an employee
e.setAge(25);  // Works fine
```

**Real-World Analogy:**
A **security guard** at a building entrance. Anyone can request entry (call setter), but the guard checks authorization (validation). Without a guard, anyone walks in freely.

---

### Q4. What are access modifiers in Java?

**Definition:**
Keywords that control visibility of classes, methods, and fields:
1. **`public`** — Accessible from everywhere
2. **`private`** — Accessible only within the same class
3. **`protected`** — Same package + subclasses in other packages
4. **`default`** (no keyword) — Same package only

**Example:**
```java
class MyClass {
    public int a;       // visible everywhere
    private int b;      // visible only in MyClass
    protected int c;    // same package + subclasses
    int d;              // same package only (default)
}
```

**Real-World Analogy:**
Levels of access in a **company office**: `public` = reception (anyone enters). `default` = office floor (employees only). `protected` = manager's cabin (employees + branch managers). `private` = CEO's vault (CEO only).

---

### Q5. Difference between public, private, protected, default.

**Definition:**

| Modifier | Same Class | Same Package | Subclass (other pkg) | Everywhere |
|----------|-----------|-------------|---------------------|------------|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `default` | ✅ | ✅ | ❌ | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |

**Example:**
```java
class Parent {
    public int a = 1;
    protected int b = 2;
    int c = 3;            // default
    private int d = 4;
}
class Child extends Parent {
    void test() {
        System.out.println(a); // ✅ public
        System.out.println(b); // ✅ protected
        System.out.println(c); // ✅ default (same package)
        // System.out.println(d); // ❌ private — compile error
    }
}
```

**Real-World Analogy:**
A **house**: `public` = front yard (visible to everyone). `protected` = living room (family + close relatives). `default` = kitchen (people in the house only). `private` = personal diary in a locked drawer (only you).

---

### Q6. What is Abstraction?

**Definition:**
Abstraction is hiding internal implementation details and showing only the **relevant functionality**. It focuses on **what** an object does, not **how**.

**Example:**
```java
abstract class Vehicle {
    abstract void start();
}
class Car extends Vehicle {
    void start() { System.out.println("Turn key, engine ignites"); }
}
class ElectricCar extends Vehicle {
    void start() { System.out.println("Press button, motor powers on silently"); }
}
Vehicle v = new Car();
v.start(); // You just call start() — the "how" is hidden
```

**Real-World Analogy:**
A **coffee machine**. You press one button — "Cappuccino." You don't know how it grinds beans, heats water, froths milk. The complex process is abstracted behind a simple button.

---

### Q7. How do you achieve Abstraction in Java?

**Definition:**
Two ways:
1. **Abstract classes** (partial abstraction) — both abstract and concrete methods
2. **Interfaces** (full abstraction) — only method signatures (before Java 8)

**Example:**
```java
// Abstract class
abstract class Shape {
    abstract double area();
    void display() { System.out.println("Area: " + area()); }
}
class Circle extends Shape {
    double radius;
    Circle(double r) { radius = r; }
    double area() { return Math.PI * radius * radius; }
}

// Interface
interface Drawable {
    void draw();
}
class Rectangle implements Drawable {
    public void draw() { System.out.println("Drawing rectangle"); }
}
```

**Real-World Analogy:**
A **TV remote** is an abstract interface — it says there must be power, volume, channel buttons. But **how** each brand implements them internally is different. The remote defines **what**, not **how**.

---

### Q8. Difference between Abstraction and Encapsulation.

**Definition:**

| Aspect | Abstraction | Encapsulation |
|--------|------------|---------------|
| Focus | Hiding complexity — what to show | Hiding data — how to protect |
| Purpose | Simplify the interface | Protect data integrity |
| Achieved by | Abstract classes, interfaces | Private fields, getters/setters |
| Level | Design level | Implementation level |

**Example:**
```java
// Abstraction — hides HOW payment works
abstract class Payment { abstract void pay(double amount); }

// Encapsulation — hides and protects balance data
class Wallet {
    private double balance;
    public void addMoney(double amount) { if (amount > 0) balance += amount; }
    public double getBalance() { return balance; }
}
```

**Real-World Analogy:**
Abstraction = **steering wheel** (you turn it, don't know the internals). Encapsulation = **engine cover/hood** (parts are physically hidden and protected).

---

### Q9. From whom are we hiding implementation in abstraction?

**Definition:**
We hide implementation from the **user of the class** (the developer who calls the methods). The **writer** of the class knows the internals. The **user** only sees method names, parameters, and return types.

**Example:**
```java
abstract class Sorter {
    abstract void sort(int[] arr);
}
class BubbleSorter extends Sorter {
    void sort(int[] arr) { /* complex bubble sort logic */ }
}
// The USER doesn't care HOW it sorts
Sorter s = new BubbleSorter();
s.sort(myArray); // Just call sort()
```

**Real-World Analogy:**
**Google Search** — you type a query and get results. You (user) don't know Google's ranking algorithm. Google (developer) knows. Abstraction hides implementation from the **consumer**, not the **creator**.

---

### Q10. Can abstraction be achieved without abstract class?

**Definition:**
**Yes.** Abstraction can be achieved using:
1. **Interfaces** — pure contracts
2. **Regular classes with limited public methods** — expose only necessary methods, keep everything else private

Abstraction is a **concept**, not tied to the `abstract` keyword.

**Example:**
```java
// Abstraction using interface
interface Notifier { void send(String message); }
class EmailNotifier implements Notifier {
    public void send(String message) { System.out.println("Email: " + message); }
}

// Abstraction using regular class
class Calculator {
    public int add(int a, int b) { return computeSum(a, b); }
    private int computeSum(int a, int b) { return a + b; } // hidden
}
```

**Real-World Analogy:**
A **restaurant menu** provides abstraction without being "abstract." You see dish names and prices (interface). The kitchen process and recipes are hidden. The menu isn't an abstract class, but it still abstracts away complexity.

---

# 3. Inheritance

---

### Q1. What is Inheritance?

**Definition:**
Inheritance is a mechanism where a child class (subclass) acquires properties and behaviors of a parent class (superclass) using `extends`. It establishes an IS-A relationship and promotes code reuse.

**Example:**
```java
class Animal {
    void eat() { System.out.println("Animal is eating"); }
}
class Dog extends Animal {
    void bark() { System.out.println("Dog is barking"); }
}
Dog d = new Dog();
d.eat();  // inherited
d.bark(); // own method
```

**Real-World Analogy:**
A child inherits traits from parents — eye color, blood type, surname. The child also has unique traits. A subclass inherits fields and methods from parent, and adds its own.

---

### Q2. What are the types of inheritance in Java?

**Definition:**
Java supports:
1. **Single** — One child, one parent (`Dog extends Animal`)
2. **Multilevel** — Chain: `Puppy extends Dog extends Animal`
3. **Hierarchical** — Multiple children, one parent (`Dog extends Animal`, `Cat extends Animal`)

Java does **NOT** support with classes:
4. **Multiple** — One child extending two parents (`class C extends A, B` — NOT allowed)
5. **Hybrid** — Combination involving multiple inheritance

> Multiple inheritance IS supported through **interfaces**.

**Example:**
```java
// Single
class Dog extends Animal { }
// Multilevel
class Puppy extends Dog { } // Dog extends Animal
// Hierarchical
class Dog extends Animal { }
class Cat extends Animal { }
// Multiple — via interfaces
interface Swimmable { void swim(); }
interface Runnable { void run(); }
class Dog implements Swimmable, Runnable {
    public void swim() { System.out.println("Dog swims"); }
    public void run() { System.out.println("Dog runs"); }
}
```

**Real-World Analogy:**
Single = son inherits from father. Multilevel = grandson → father → grandfather. Hierarchical = son and daughter both inherit from same father. Multiple (not allowed) = child inheriting from two fathers — ambiguous!

---

### Q3. Why does Java not support multiple inheritance with classes?

**Definition:**
Because of the **Diamond Problem**. If two parent classes have a method with the same name, the child wouldn't know which version to use — creating ambiguity.

**Example:**
```java
// NOT valid Java — illustration only
class A { void show() { System.out.println("A"); } }
class B { void show() { System.out.println("B"); } }
// class C extends A, B { }  // ❌ COMPILE ERROR
// new C().show(); // Which show()? A's or B's? AMBIGUOUS!
```

Java solves this through interfaces. If two interfaces have the same default method, the class **must override** it:
```java
interface A { default void show() { System.out.println("A"); } }
interface B { default void show() { System.out.println("B"); } }
class C implements A, B {
    public void show() { A.super.show(); } // explicitly choose
}
```

**Real-World Analogy:**
Two **bosses** giving conflicting instructions. Boss A says "blue theme," Boss B says "red theme." You're stuck. Java says: "One boss (parent class) only. But follow multiple handbooks (interfaces)."

---

### Q4. What is IS-A relationship?

**Definition:**
IS-A is a relationship established through **inheritance** (`extends` or `implements`). It means the child class **is a type of** the parent class.

**Example:**
```java
class Animal { }
class Dog extends Animal { }   // Dog IS-A Animal ✅
interface Flyable { }
class Bird implements Flyable { } // Bird IS-A Flyable ✅
```

**Real-World Analogy:**
- Mango IS-A Fruit ✅
- Car IS-A Vehicle ✅
- Car IS-A Engine ❌ — a car HAS-A engine, it's not a type of engine

---

### Q5. What is HAS-A relationship?

**Definition:**
HAS-A is a relationship through **composition** — one class contains a reference to another class as a field. The class **has** another object as part of it.

**Example:**
```java
class Engine {
    void start() { System.out.println("Engine started"); }
}
class Car {
    private Engine engine = new Engine(); // HAS-A
    void startCar() {
        engine.start();
        System.out.println("Car is ready");
    }
}
```

**Real-World Analogy:**
- Car HAS-A Engine ✅
- House HAS-A Kitchen ✅
- Person HAS-A Address ✅

The engine is part of the car, not a type of car.

---

### Q6. Difference between Inheritance and Composition.

**Definition:**

| Aspect | Inheritance (IS-A) | Composition (HAS-A) |
|--------|-------------------|---------------------|
| Relationship | "is a type of" | "has a / contains" |
| Keyword | `extends` | Object as a field |
| Coupling | Tight | Loose |
| Flexibility | Less — can't change parent at runtime | More — can swap parts |
| When to use | True type relationship | When one object uses another |

**Example:**
```java
// Inheritance — Dog IS-A Animal
class Animal { void breathe() { } }
class Dog extends Animal { void bark() { } }

// Composition — Car HAS-A Engine
class Engine { void start() { } }
class Car {
    private Engine engine;
    Car(Engine engine) { this.engine = engine; }
    void startCar() { engine.start(); }
}
```

**Real-World Analogy:**
Inheritance = surgeon IS-A doctor (inherits all properties). Composition = hospital HAS-A doctor (contains doctors, can replace one without rebuilding hospital).

---

### Q7. What is method overriding in inheritance?

**Definition:**
Method overriding is when a child class provides its **own implementation** of a method already defined in the parent. Must have same name, same parameters, same return type. Child's version runs at **runtime**.

**Example:**
```java
class Animal {
    void sound() { System.out.println("Generic animal sound"); }
}
class Cat extends Animal {
    @Override
    void sound() { System.out.println("Meow"); }
}
Animal a = new Cat();
a.sound(); // Meow — child's version runs
```

**Real-World Analogy:**
Your parents taught you a **recipe** for cooking rice. You learned it (inherited), but tweaked it — added spices, different method. You **overrode** the original. When someone asks you to cook rice, your version runs.

---

### Q8. What happens when child class and parent class have the same variable name?

**Definition:**
This is called **variable hiding** (field shadowing). Unlike methods, variables are **NOT overridden**. The variable accessed depends on the **reference type**, not the object type.

**Example:**
```java
class Parent {
    int x = 10;
}
class Child extends Parent {
    int x = 20;
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.x); // 10 — reference type is Parent

        Child c = new Child();
        System.out.println(c.x); // 20 — reference type is Child
    }
}
```

**Key Point:** Variables are resolved at **compile time** (reference type). Methods are resolved at **runtime** (object type). This is a critical distinction.

**Real-World Analogy:**
Parent and child both own a bookshelf labeled **"Library."** When you ask the parent for "the Library," you see parent's books. When you ask the child, you see child's books. Same label, but which one you see depends on **who you're asking** (reference type).

---

# 4. Polymorphism

---

### Q1. What is Polymorphism?

**Definition:**
Polymorphism means **"many forms."** Same method or operation behaves differently based on the object it acts upon. Write flexible code that works with different types through a common interface.

**Example:**
```java
class Shape { void draw() { System.out.println("Drawing a shape"); } }
class Circle extends Shape { void draw() { System.out.println("Drawing a circle"); } }
class Square extends Shape { void draw() { System.out.println("Drawing a square"); } }

Shape s = new Circle();
s.draw(); // Drawing a circle
```

**Real-World Analogy:**
A person can be many things at once — student at school, son at home, customer at a shop. Behavior changes based on context, but the person is the same.

---

### Q2. What are types of polymorphism in Java?

**Definition:**
1. **Compile-time (Static)** — Method **overloading**. Java decides at compile time based on method signature.
2. **Runtime (Dynamic)** — Method **overriding**. Java decides at runtime based on actual object type.

**Example:**
```java
// Compile-time — Overloading
class MathUtils {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}

// Runtime — Overriding
class Animal { void sound() { System.out.println("Animal sound"); } }
class Dog extends Animal { void sound() { System.out.println("Bark"); } }
Animal a = new Dog();
a.sound(); // Bark — decided at runtime
```

**Real-World Analogy:**
Compile-time = **restaurant menu** — you decide before food is prepared. Runtime = **doorbell** — same bell, but response depends on who's home.

---

### Q3. What is Compile-time polymorphism?

**Definition:**
Achieved through **method overloading**. Multiple methods have the same name but different parameter lists. The compiler decides which to call based on the arguments.

**Example:**
```java
class Printer {
    void print(String text) { System.out.println("String: " + text); }
    void print(int number) { System.out.println("Number: " + number); }
    void print(String text, int copies) {
        for (int i = 0; i < copies; i++) System.out.println(text);
    }
}
Printer p = new Printer();
p.print("Hello");   // calls print(String)
p.print(42);        // calls print(int)
p.print("Hi", 3);   // calls print(String, int)
```

**Real-World Analogy:**
A **customer service number**. Same number, but pressing different keys (parameters) routes you to different departments — billing, tech support, complaints. Routing decided **before** connecting (compile time).

---

### Q4. What is Runtime polymorphism?

**Definition:**
Achieved through **method overriding**. A parent reference holds a child object, and the actual method that runs is determined at **runtime** based on the object's actual type.

**Example:**
```java
class Animal { void sound() { System.out.println("Animal sound"); } }
class Dog extends Animal {
    @Override
    void sound() { System.out.println("Dog barking"); }
}
class Cat extends Animal {
    @Override
    void sound() { System.out.println("Cat meowing"); }
}

Animal a;
a = new Dog();
a.sound(); // Dog barking — decided at RUNTIME
a = new Cat();
a.sound(); // Cat meowing — decided at RUNTIME
```

**Real-World Analogy:**
Pressing **"Play"** on a media player. Same button, but plays differently based on file loaded — MP3 plays music, MP4 plays video. The action is decided at runtime based on the actual content.

---

### Q5. Difference between Method Overloading and Method Overriding.

**Definition:**

| Aspect | Overloading | Overriding |
|--------|------------|------------|
| What changes? | Parameters | Implementation (body) |
| Where? | Same class | Parent → Child class |
| Parameters | Must be different | Must be same |
| Return type | Can differ | Must be same (or covariant) |
| Binding | Compile time | Runtime |
| `static` methods | ✅ Can be overloaded | ❌ Cannot be overridden |
| `private` methods | ✅ Can be overloaded | ❌ Cannot be overridden |
| `final` methods | ✅ Can be overloaded | ❌ Cannot be overridden |

**Example:**
```java
// Overloading — same class, different params
class Calc {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
// Overriding — parent-child, same signature
class Vehicle { void start() { System.out.println("Vehicle starts"); } }
class Car extends Vehicle {
    @Override void start() { System.out.println("Car starts with key"); }
}
```

**Real-World Analogy:**
Overloading = **Swiss Army knife** — one tool, many functions based on which you use. Overriding = **new generation iPhone** — same method (`takePhoto()`), but newer model does it better.

---

### Q6. What is Dynamic Method Dispatch?

**Definition:**
The mechanism by which Java resolves an overridden method call at **runtime**. When a parent reference holds a child object, JVM checks the **actual object type** to decide which version to execute.

**Example:**
```java
class Animal {
    void sound() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    void sound() { System.out.println("Dog barking"); }
}

Animal a = new Dog(); // parent reference, child object
a.sound();            // Output: Dog barking
```

Reference type is `Animal`, but actual object is `Dog`. At runtime, JVM dispatches the call to `Dog`'s `sound()`.

**Real-World Analogy:**
You order a **"surprise box"** online. The label says "Gift" (parent reference), but the content is a "Book" (child object). When you open it, you get the book's behavior. What happens is determined by **what's actually inside**, not the label.

---

# 5. Abstract Class vs Interface

---

### Q1. What is an abstract class?

**Definition:**
An abstract class is declared with `abstract` keyword and **cannot be instantiated**. It can have both **abstract methods** (no body) and **concrete methods** (with body). Used to define a common template for subclasses.

**Example:**
```java
abstract class Animal {
    String name;
    Animal(String name) { this.name = name; }
    abstract void sound();  // child MUST implement
    void breathe() { System.out.println(name + " is breathing"); } // shared
}
class Dog extends Animal {
    Dog(String name) { super(name); }
    void sound() { System.out.println(name + " says Woof!"); }
}
Animal a = new Dog("Buddy");
a.sound();    // Buddy says Woof!
a.breathe();  // Buddy is breathing
```

**Real-World Analogy:**
An **incomplete blueprint** for a building. It defines that every building must have a main entrance and emergency exit (abstract methods), and includes standard features like foundation and roof (concrete methods). You can't build from an incomplete blueprint alone.

---

### Q2. What is an interface?

**Definition:**
An interface is a **fully abstract contract** that defines what methods a class must implement. Declared with `interface`, implemented with `implements`. A class can implement **multiple interfaces**.

**Example:**
```java
interface Drivable { void drive(); void stop(); }
interface Electric { void charge(); }

class Tesla implements Drivable, Electric {
    public void drive() { System.out.println("Tesla driving silently"); }
    public void stop() { System.out.println("Tesla stopped"); }
    public void charge() { System.out.println("Tesla charging"); }
}
```

**Real-World Analogy:**
A **job contract**. It says "you must do X, Y, Z" but doesn't tell you **how**. Different employees implementing the same contract do tasks in their own way. One employee can sign multiple contracts (multiple interfaces).

---

### Q3. Difference between abstract class and interface.

**Definition:**

| Aspect | Abstract Class | Interface |
|--------|---------------|-----------|
| Keyword | `abstract class` | `interface` |
| Methods | Abstract + concrete | Abstract (+ default/static from Java 8) |
| Variables | Any type | Only `public static final` (constants) |
| Constructor | ✅ Yes | ❌ No |
| Inheritance | `extends` (single only) | `implements` (multiple allowed) |
| Access modifiers | Any | Methods are `public` by default |
| When to use | Shared code + template | Pure contract / capability |

**Example:**
```java
abstract class Vehicle {
    int speed;
    abstract void start();
    void stop() { System.out.println("Stopped"); }
}
interface Flyable { void fly(); }
class Helicopter extends Vehicle implements Flyable {
    void start() { System.out.println("Helicopter starts"); }
    public void fly() { System.out.println("Helicopter flying"); }
}
```

**Real-World Analogy:**
Abstract class = **partially built house** (some rooms ready, some just floor plans). Interface = **government regulation** ("every building MUST have a fire exit" — doesn't provide one, you build your own).

---

### Q4. When should you use abstract class vs interface?

**Definition:**
- **Abstract class when:** Subclasses share common code (fields, constructors, concrete methods), you want a template, clear IS-A relationship
- **Interface when:** Defining a capability/contract for unrelated classes, need multiple inheritance, want loose coupling

**Example:**
```java
// Abstract class — shared state + behavior
abstract class Employee {
    String name; double salary;
    abstract double calculateBonus();
    void showDetails() { System.out.println(name + " earns " + salary); }
}
// Interface — capability
interface Exportable { void exportToPDF(); }

class Manager extends Employee implements Exportable {
    double calculateBonus() { return salary * 0.2; }
    public void exportToPDF() { System.out.println("Exporting..."); }
}
```

**Real-World Analogy:**
Abstract class = **franchise (McDonald's)** — every branch shares logo, core menu, training. But each can customize local specials. Interface = **driver's license** — certifies you CAN drive. A teacher, doctor, or student can all have one — it's a capability, not a type.

---

### Q5. Can an abstract class have constructors?

**Definition:**
**Yes.** They are called when a **child class is instantiated** (through `super()`). Purpose is to initialize common fields.

**Example:**
```java
abstract class Animal {
    String name;
    Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor called");
    }
    abstract void sound();
}
class Dog extends Animal {
    Dog(String name) { super(name); }
    void sound() { System.out.println(name + " says Woof!"); }
}
Dog d = new Dog("Buddy");
// Output: Animal constructor called
```

**Real-World Analogy:**
You can't build an "Animal" directly (abstract), but when you build a Dog, the animal's basic setup (DNA, organs) still happens first. The abstract constructor is the **biological foundation** — it runs before specific features kick in.

---

### Q6. Can we create an object of an abstract class?

**Definition:**
**No.** You cannot use `new` on an abstract class directly. It may have abstract methods without a body — it's incomplete. Create an object of a **concrete child class** instead.

**Example:**
```java
abstract class Shape { abstract void draw(); }
// Shape s = new Shape(); // ❌ COMPILE ERROR

class Circle extends Shape {
    void draw() { System.out.println("Drawing circle"); }
}
Shape s = new Circle(); // ✅ Parent reference, child object
```

**However**, you CAN use an **anonymous class**:
```java
Shape s = new Shape() {
    void draw() { System.out.println("Anonymous shape"); }
};
```

**Real-World Analogy:**
You can't buy a **"Vehicle"** from a showroom — too vague. You buy a specific one: Car, Bike, Truck. "Vehicle" is just the category (abstract).

---

### Q7. Can an abstract class have concrete methods?

**Definition:**
**Yes.** Abstract classes can have **both abstract and concrete methods**. Concrete methods provide shared default behavior that all children inherit.

**Example:**
```java
abstract class Notification {
    abstract void send(String message);
    void log(String message) { System.out.println("LOG: " + message); } // concrete
}
class EmailNotification extends Notification {
    void send(String message) {
        System.out.println("Email: " + message);
        log(message); // using inherited concrete method
    }
}
```

**Real-World Analogy:**
A **recipe book** from grandmother. Some recipes are complete (concrete) — follow as-is. Some just say "add your favorite spice here" (abstract) — you fill in the blanks.

---

### Q8. Can an interface have variables?

**Definition:**
**Yes, but** all variables are implicitly `public static final` — they are **constants**. You cannot have mutable instance variables.

**Example:**
```java
interface MathConstants {
    double PI = 3.14159;      // public static final by default
    int MAX_VALUE = 1000;     // public static final by default
}
class Calculator implements MathConstants {
    double circumference(double radius) { return 2 * PI * radius; }
}
System.out.println(MathConstants.PI); // 3.14159
```

**Real-World Analogy:**
Interface variables are like **national laws** — fixed (final), apply everywhere (public), belong to the system (static). You can read them but never change them.

---

### Q9. Can an interface have methods with body?

**Definition:**
**Yes, since Java 8.** Interfaces can have:
1. **`default` methods** (Java 8) — methods with body, inherited by implementing classes
2. **`static` methods** (Java 8) — utility methods on the interface itself
3. **`private` methods** (Java 9) — helper methods within default methods

**Example:**
```java
interface Logger {
    void log(String message);  // abstract
    default void logInfo(String msg) { System.out.println("INFO: " + msg); }
    static void logError(String msg) { System.out.println("ERROR: " + msg); }
}
class AppLogger implements Logger {
    public void log(String message) { System.out.println("LOG: " + message); }
}
AppLogger logger = new AppLogger();
logger.logInfo("details"); // INFO: details (inherited default)
Logger.logError("crash");  // ERROR: crash (static on interface)
```

**Real-World Analogy:**
Before Java 8, interface = **strict contract** ("do these things, no help"). After Java 8, interface = **contract with a guidebook** ("here are some default ways, override if you want").

---

### Q10. What are default methods in interfaces (Java 8)?

**Definition:**
Methods with a body in an interface, declared with `default` keyword. Allow adding new methods **without breaking existing classes**. Classes can use the default or override it.

**Example:**
```java
interface Vehicle {
    void start();
    default void honk() { System.out.println("Beep beep!"); }
}
class Car implements Vehicle {
    public void start() { System.out.println("Car started"); }
}
class Truck implements Vehicle {
    public void start() { System.out.println("Truck started"); }
    @Override public void honk() { System.out.println("HOOOONK!"); }
}
new Car().honk();   // Beep beep! (default)
new Truck().honk(); // HOOOONK! (overridden)
```

**Real-World Analogy:**
A **company policy update**. Company adds: "Everyone gets free coffee" (default method). Existing employees automatically get it. A specific department can override: "We prefer tea."

---

### Q11. Can an interface extend another interface?

**Definition:**
**Yes.** An interface CAN extend another interface using `extends`. It can even extend **multiple interfaces**.

**Example:**
```java
interface Movable { void move(); }
interface Flyable extends Movable { void fly(); }

class Bird implements Flyable {
    public void move() { System.out.println("Bird walks"); }
    public void fly() { System.out.println("Bird flies"); }
}
```

**Real-World Analogy:**
A **certification hierarchy**. "Certified Pilot" extends "Certified Driver." If you're a pilot, you must also know how to drive. Pilot cert inherits all driver requirements and adds its own.

---

### Q12. Can an abstract class implement an interface?

**Definition:**
**Yes.** An abstract class CAN implement an interface. It's **not required** to implement all methods — it can leave some abstract for child classes.

**Example:**
```java
interface Playable { void play(); void pause(); }
abstract class MediaPlayer implements Playable {
    public void pause() { System.out.println("Paused"); } // implemented
    // play() left abstract — child must implement
}
class MusicPlayer extends MediaPlayer {
    public void play() { System.out.println("Playing music"); }
}
```

**Real-World Analogy:**
A **franchise headquarters** (abstract class) signs an agreement (interface). Headquarters implements common rules and delegates the rest to individual branches (child classes).

---

# 6. Method Overloading & Overriding

---

### Q1. What is Method Overloading?

**Definition:**
A class has **multiple methods with the same name but different parameter lists** (different number, type, or order). Java decides which to call at **compile time**.

**Example:**
```java
class Display {
    void show(String text) { System.out.println("String: " + text); }
    void show(int number) { System.out.println("Number: " + number); }
    void show(String text, int times) {
        for (int i = 0; i < times; i++) System.out.println(text);
    }
}
Display d = new Display();
d.show("Hello");    // String: Hello
d.show(42);         // Number: 42
d.show("Hi", 2);    // Hi (printed twice)
```

**Real-World Analogy:**
A **washing machine** with different modes. You press "Wash" for all, but it behaves differently based on input: cotton, delicate, wool. Same action name, different behavior based on input.

---

### Q2. What is Method Overriding?

**Definition:**
A child class provides its **own implementation** of a method already defined in the parent. Same name, same parameters, same return type. Child's version runs at **runtime**.

**Example:**
```java
class Bird { void fly() { System.out.println("Bird is flying"); } }
class Penguin extends Bird {
    @Override
    void fly() { System.out.println("Penguins can't fly, they swim!"); }
}
Bird b = new Penguin();
b.fly(); // Penguins can't fly, they swim!
```

**Real-World Analogy:**
Your parents' **recipe for dal**. You learned it (inherited), but make it differently — more spices, different technique. You **overrode** the original. When someone asks you to make dal, your version runs.

---

### Q3. Difference between Overloading and Overriding.

**Definition:**

| Aspect | Overloading | Overriding |
|--------|------------|------------|
| What changes? | Parameters | Implementation (body) |
| Where? | Same class | Parent → Child |
| Parameters | Must be different | Must be same |
| Return type | Can differ | Must be same (or covariant) |
| Binding | Compile time | Runtime |
| `static` methods | ✅ Can overload | ❌ Cannot override |
| `final` methods | ✅ Can overload | ❌ Cannot override |
| `private` methods | ✅ Can overload | ❌ Cannot override |

**Example:**
```java
// Overloading
class Calc {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
// Overriding
class Parent { void greet() { System.out.println("Hello from Parent"); } }
class Child extends Parent {
    @Override void greet() { System.out.println("Hello from Child"); }
}
```

**Real-World Analogy:**
Overloading = **doctor treating different patients** — same action ("treat"), varies by patient (params). Overriding = **new coach replacing old coach** — same role, same team, different strategy.

---

### Q4. Can constructors be overloaded?

**Definition:**
**Yes.** Very common in Java. Multiple constructors with different parameter lists allow creating objects in different ways.

**Example:**
```java
class Student {
    String name; int age;
    Student() { name = "Unknown"; age = 0; }
    Student(String name) { this.name = name; age = 0; }
    Student(String name, int age) { this.name = name; this.age = age; }
}
Student s1 = new Student();              // Unknown, 0
Student s2 = new Student("Gowtham");     // Gowtham, 0
Student s3 = new Student("Gowtham", 22); // Gowtham, 22
```

**Real-World Analogy:**
Ordering a **burger**: "Give me a burger" (default). "Chicken burger" (one param). "Chicken burger with extra cheese" (fully customized). Same order, different detail levels.

---

### Q5. Can constructors be overridden?

**Definition:**
**No.** Constructors cannot be overridden because:
1. Constructors are **not inherited**
2. Constructor must have **same name as its class** — child has a different name
3. Overriding requires same signature in parent and child — constructors don't qualify

**Example:**
```java
class Parent { Parent() { System.out.println("Parent constructor"); } }
class Child extends Parent {
    Child() {
        super(); // calls parent constructor, does NOT override it
        System.out.println("Child constructor");
    }
}
```

**Real-World Analogy:**
A **birth certificate** is unique to each person. Your child gets their own — doesn't "override" yours. Each generation has their own.

---

### Q6. Can static methods be overridden?

**Definition:**
**No.** Static methods are resolved at **compile time** based on reference type. If a child defines a static method with same signature, it's called **method hiding**, not overriding.

**Example:**
```java
class Parent {
    static void greet() { System.out.println("Hello from Parent"); }
}
class Child extends Parent {
    static void greet() { System.out.println("Hello from Child"); } // HIDING
}
Parent p = new Child();
p.greet(); // Hello from Parent — static uses REFERENCE type
```

**Real-World Analogy:**
A **company's official email** (static). It belongs to the company (class), not any employee (object). A branch (child) can create its own email, but the HQ email is used when you contact the main company.

---

### Q7. Can private methods be overridden?

**Definition:**
**No.** Private methods are **not visible** to the child class. If a child defines a method with the same name, it's a **completely new method**, not an override.

**Example:**
```java
class Parent {
    private void secret() { System.out.println("Parent's secret"); }
    void callSecret() { secret(); }
}
class Child extends Parent {
    private void secret() { System.out.println("Child's secret"); } // NEW method
}
Child c = new Child();
c.callSecret(); // Parent's secret — Parent's private method runs
```

**Real-World Analogy:**
Your **personal diary** (private). Your child can't read or modify it. If they write their own diary with the same title, it's a completely separate diary.

---

### Q8. Can final methods be overridden?

**Definition:**
**No.** A `final` method **cannot be overridden**. The `final` keyword locks the method — no subclass can change it.

**Example:**
```java
class BankAccount {
    final void calculateInterest() {
        System.out.println("Interest = Principal × Rate × Time");
    }
}
class SavingsAccount extends BankAccount {
    // void calculateInterest() { } // ❌ COMPILE ERROR
}
```

**Real-World Analogy:**
A **constitutional law**. No state or local government can override it. `final` = "this is the law, cannot be changed by anyone below."

---

### Q9. What is method hiding?

**Definition:**
When a child class defines a **static method with the same signature** as a static method in the parent. The method called depends on **reference type** (compile time), not object type.

**Example:**
```java
class Parent {
    static void display() { System.out.println("Parent static"); }
}
class Child extends Parent {
    static void display() { System.out.println("Child static"); } // HIDES
}
Parent p = new Child();
p.display();  // Parent static — reference type decides
Child c = new Child();
c.display();  // Child static — reference type decides
```

**Real-World Analogy:**
Two **stores with the same name** in different cities. If you're in City A (Parent reference), you visit City A's store. If in City B (Child reference), City B's store. Same name, which one you visit depends on **where you are**.

---

# 7. Polymorphism Scenarios (Common Interview Questions)

---

### Q1. Explain: `Animal a = new Dog();`

**Definition:**
This is **upcasting** + **runtime polymorphism**. A parent reference (`Animal`) holds a child object (`Dog`).

- **Is this valid?** ✅ Yes — Dog IS-A Animal
- **Which methods can be called?** Only methods defined in `Animal` (reference type)
- **Which implementation runs?** If Dog overrides a method, **Dog's version** runs at runtime

**Example:**
```java
class Animal {
    void sound() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    @Override
    void sound() { System.out.println("Bark"); }
    void fetch() { System.out.println("Fetching ball"); }
}

Animal a = new Dog();
a.sound();   // Bark ✅ — Dog's overridden version runs
// a.fetch(); // ❌ COMPILE ERROR — Animal doesn't know about fetch()
```

**Real-World Analogy:**
A remote labeled **"TV Remote"** (Animal reference) controlling a **Samsung TV** (Dog object). You can only press buttons any TV remote has (Animal methods). But when you press "Menu," Samsung-specific menu shows up (Dog's overridden method). You can't press Samsung-only buttons through a generic label.

---

### Q2. Explain: `Parent p = new Child()` vs `Child c = new Child()`

**Definition:**

| Aspect | `Parent p = new Child()` | `Child c = new Child()` |
|--------|------------------------|------------------------|
| Reference type | Parent | Child |
| Object type | Child | Child |
| Methods accessible | Only Parent's | All (Parent + Child) |
| Overridden methods | Child's version runs | Child's version runs |
| Child-specific methods | ❌ Not accessible | ✅ Accessible |

**Example:**
```java
class Parent {
    void greet() { System.out.println("Hello from Parent"); }
}
class Child extends Parent {
    @Override void greet() { System.out.println("Hello from Child"); }
    void play() { System.out.println("Child is playing"); }
}

Parent p = new Child();
p.greet();  // Hello from Child — overridden
// p.play(); // ❌ COMPILE ERROR

Child c = new Child();
c.greet();  // Hello from Child
c.play();   // Child is playing ✅
```

**Real-World Analogy:**
- `Parent p = new Child()` — You know your friend as a **"doctor"** (parent type). You can ask medical questions. But you don't know they're also a **guitarist** (child method). Through your limited view, you can't ask them to play guitar.
- `Child c = new Child()` — You know them fully — doctor AND guitarist. You can ask anything.

---

# 8. `this` & `super` Keywords

---

### Q1. What is `this` keyword?

**Definition:**
`this` refers to the **current object** — the instance currently executing. Used to:
1. Refer to current object's fields (resolve ambiguity with parameter names)
2. Call another constructor of the same class (`this()`)
3. Pass the current object as an argument

**Example:**
```java
class Employee {
    String name; int age;
    Employee(String name, int age) {
        this.name = name; // 'this.name' = field, 'name' = parameter
        this.age = age;
    }
    void display() {
        System.out.println("Name: " + this.name + ", Age: " + this.age);
    }
}
```

**Real-World Analogy:**
The word **"I"** or **"myself."** When you say "I am a developer," "I" refers to you — the current person. `this` refers to the current object — "I (this object) have this name and this age."

---

### Q2. What is `super` keyword?

**Definition:**
`super` refers to the **parent class**. Used to:
1. Access parent class fields (when hidden by child)
2. Call parent class methods (when overridden by child)
3. Call parent class constructor (`super()`)

**Example:**
```java
class Animal {
    String type = "Animal";
    void sound() { System.out.println("Some animal sound"); }
}
class Dog extends Animal {
    String type = "Dog";
    void sound() {
        super.sound(); // calls parent's sound()
        System.out.println("Bark");
    }
    void showType() {
        System.out.println(super.type); // Animal
        System.out.println(this.type);  // Dog
    }
}
```

**Real-World Analogy:**
The phrase **"my parent."** When you say "My parent taught me to cook," you're referring to the generation above you. `super` accesses the parent class's version of things.

---

### Q3. Difference between `this()` and `super()`.

**Definition:**

| Aspect | `this()` | `super()` |
|--------|---------|-----------|
| Calls | Constructor in **same class** | Constructor in **parent class** |
| Purpose | Constructor chaining within class | Initialize parent part |
| Position | Must be **first statement** | Must be **first statement** |
| Default | Not added automatically | `super()` added automatically if not written |

**Example:**
```java
class Animal {
    String name;
    Animal(String name) {
        this.name = name;
        System.out.println("Animal: " + name);
    }
}
class Dog extends Animal {
    String breed;
    Dog() { this("Unknown", "Mixed"); }  // this() — chains within Dog
    Dog(String name, String breed) {
        super(name);                      // super() — calls Animal constructor
        this.breed = breed;
        System.out.println("Dog: " + breed);
    }
}
new Dog();
// Output: Animal: Unknown
//         Dog: Mixed
```

**Real-World Analogy:**
`this()` = Calling a **colleague in the same department** for help. `super()` = Calling your **manager (parent)** to set up foundational work before you do your part.

---

### Q4. What is constructor chaining?

**Definition:**
Constructor chaining is when one constructor calls another, forming a chain:
1. **Within same class** — using `this()`
2. **Between parent and child** — using `super()`

Ensures initialization logic is centralized, not duplicated.

**Example:**
```java
class Person {
    String name; int age; String city;
    Person() { this("Unknown"); }
    Person(String name) { this(name, 0); }
    Person(String name, int age) { this(name, age, "Unknown"); }
    Person(String name, int age, String city) {
        this.name = name; this.age = age; this.city = city;
    }
}
new Person("Gowtham");
// Chains: Person("Gowtham") → Person("Gowtham", 0) → Person("Gowtham", 0, "Unknown")
```

**Real-World Analogy:**
Ordering **pizza with defaults**: "I want a pizza" → adds default size (Medium) → adds default crust (Regular) → adds default cheese (Mozzarella). Each step fills in missing details by chaining to the next.

---

### Q5. Can we call `super()` and `this()` together?

**Definition:**
**No.** Both must be the **first statement** in a constructor. Since only one statement can be first, you **cannot use both in the same constructor**.

**Workaround:** Use `this()` in one constructor to chain to another that calls `super()`:

**Example:**
```java
class Parent {
    Parent(String name) { System.out.println("Parent: " + name); }
}
class Child extends Parent {
    Child() {
        this(10);    // chains to Child(int), which calls super()
    }
    Child(int x) {
        super("Default"); // calls Parent constructor
        System.out.println("Child with " + x);
    }
}
// new Child() → Child(int) → Parent(String)
```

**Real-World Analogy:**
You can only make **one phone call at a time**. Can't call colleague (`this()`) and manager (`super()`) simultaneously. But you can call your colleague, who then calls the manager on your behalf — that's chaining.

---

# 9. Composition & Relationships

---

### Q1. What is Association?

**Definition:**
Association is a **relationship between two separate objects** where both can exist independently. It represents a "uses" or "knows about" relationship. Neither object owns the other.

**Example:**
```java
class Teacher {
    String name;
    Teacher(String name) { this.name = name; }
}
class Student {
    String name;
    Teacher teacher; // Student KNOWS about Teacher — association

    Student(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }
}

Teacher t = new Teacher("Mr. Kumar");
Student s = new Student("Gowtham", t);
// Both exist independently — deleting student doesn't delete teacher
```

**Real-World Analogy:**
A **doctor and patient** relationship. The doctor treats the patient, but both exist independently. If the patient leaves the hospital, the doctor still exists and vice versa.

---

### Q2. What is Aggregation?

**Definition:**
Aggregation is a **"HAS-A"** relationship where one object contains another, but both can exist **independently**. The contained object has its own lifecycle. It's a **weak** form of association.

**Example:**
```java
class Department {
    String name;
    Department(String name) { this.name = name; }
}
class University {
    String name;
    Department department; // University HAS-A Department — aggregation

    University(String name, Department dept) {
        this.name = name;
        this.department = dept;
    }
}

Department cs = new Department("Computer Science");
University uni = new University("MIT", cs);
// If university is destroyed, department can still exist independently
```

**Real-World Analogy:**
A **car and a driver**. The car has a driver, but the driver can exist without the car and can drive a different car. If the car is scrapped, the driver doesn't disappear.

---

### Q3. What is Composition?

**Definition:**
Composition is a **strong** "HAS-A" relationship where one object **owns** another, and the contained object **cannot exist without the parent**. If the parent is destroyed, the child is destroyed too.

**Example:**
```java
class Engine {
    String type;
    Engine(String type) { this.type = type; }
}
class Car {
    String brand;
    Engine engine; // Car HAS-A Engine — composition (engine created inside car)

    Car(String brand, String engineType) {
        this.brand = brand;
        this.engine = new Engine(engineType); // engine's lifecycle tied to car
    }
}

Car car = new Car("Toyota", "V6");
// If car is destroyed, the engine goes with it — strong ownership
```

**Real-World Analogy:**
A **human body and heart**. The body HAS-A heart. If the body dies, the heart dies too. The heart cannot exist independently outside the body. That's composition — **strong ownership with dependent lifecycle**.

---

### Q4. Difference between Aggregation and Composition.

**Definition:**

| Aspect | Aggregation (Weak HAS-A) | Composition (Strong HAS-A) |
|--------|-------------------------|---------------------------|
| Ownership | No ownership | Parent owns the child |
| Lifecycle | Independent — child survives parent | Dependent — child dies with parent |
| Creation | Child created outside, passed in | Child created inside parent |
| Example | University has Department | Car has Engine |

**Example:**
```java
// Aggregation — Department exists independently
class University {
    Department dept;
    University(Department dept) { this.dept = dept; } // passed in
}

// Composition — Engine's life is tied to Car
class Car {
    Engine engine;
    Car() { this.engine = new Engine("V6"); } // created inside
}
```

**Real-World Analogy:**
- **Aggregation** = A **company and employees**. If the company shuts down, employees still exist — they find new jobs.
- **Composition** = A **house and rooms**. If the house is demolished, the rooms are gone. Rooms can't exist without the house.

---

### Q5. Why is composition preferred over inheritance in some cases?

**Definition:**
Composition is preferred when:
1. **Flexibility** — You can change behavior at runtime by swapping components
2. **Loose coupling** — Changes to one class don't break the other
3. **No true IS-A** — The relationship is "has-a," not "is-a"
4. **Avoiding deep hierarchies** — Deep inheritance chains are hard to maintain

**Example:**
```java
// Composition — flexible, can swap engines
interface Engine { void start(); }
class PetrolEngine implements Engine {
    public void start() { System.out.println("Petrol engine roars"); }
}
class ElectricEngine implements Engine {
    public void start() { System.out.println("Electric engine hums"); }
}
class Car {
    private Engine engine;
    Car(Engine engine) { this.engine = engine; }
    void start() { engine.start(); }
    void setEngine(Engine engine) { this.engine = engine; } // swap at runtime!
}

Car car = new Car(new PetrolEngine());
car.start();  // Petrol engine roars
car.setEngine(new ElectricEngine());
car.start();  // Electric engine hums — swapped at runtime!
```

**Real-World Analogy:**
Think of a **phone case**. You don't weld the case onto the phone (inheritance). You snap it on and can swap it anytime (composition). If a new design comes out, you just change the case — not the phone.

---

# 10. Upcasting & Downcasting

---

### Q1. What is Upcasting?

**Definition:**
Upcasting is casting a **child object to a parent reference type**. It happens **implicitly** (automatically) and is always safe. After upcasting, you can only call methods defined in the parent type, but overridden methods will still run the child's version.

**Example:**
```java
class Animal {
    void sound() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    @Override
    void sound() { System.out.println("Bark"); }
    void fetch() { System.out.println("Fetching ball"); }
}

Animal a = new Dog(); // Upcasting — implicit, no cast needed
a.sound();   // Bark — child's overridden version
// a.fetch(); // ❌ Not accessible through parent reference
```

**Real-World Analogy:**
You introduce your friend as a **"musician"** (parent type) instead of a **"guitarist"** (child type). Everyone knows they can play music (parent methods), but nobody knows the specific instrument (child-specific methods) unless you tell them more.

---

### Q2. What is Downcasting?

**Definition:**
Downcasting is casting a **parent reference back to a child type**. It must be done **explicitly** using `(ChildType)` and is **risky** — if the actual object is not of that child type, it throws `ClassCastException` at runtime.

**Example:**
```java
class Animal {
    void sound() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    void fetch() { System.out.println("Fetching ball"); }
}

Animal a = new Dog();   // Upcasting
Dog d = (Dog) a;        // Downcasting — explicit cast, safe here
d.fetch();              // ✅ Now we can access Dog-specific methods

// Unsafe downcasting
Animal a2 = new Animal();
// Dog d2 = (Dog) a2;   // ❌ ClassCastException at runtime!
```

**Safe check using `instanceof`:**
```java
if (a instanceof Dog) {
    Dog d = (Dog) a;
    d.fetch(); // Safe
}
```

**Real-World Analogy:**
You know someone is a **"musician"** (parent). You **assume** they're a guitarist and ask them to play guitar (downcast). If they actually are a guitarist, great! If they're a pianist, your assumption is wrong — you get an error. Always **check first** (`instanceof`) before assuming.

---

### Q3. Is upcasting implicit or explicit?

**Definition:**
**Implicit.** Upcasting happens automatically without any cast operator. The compiler knows a child IS-A parent, so the assignment is safe and doesn't need an explicit cast.

**Example:**
```java
Dog d = new Dog();
Animal a = d;         // ✅ Implicit upcasting — no (Animal) needed
// Same as:
Animal a2 = (Animal) d; // Explicit — works but unnecessary
```

**Real-World Analogy:**
Saying **"a rose is a flower"** is natural and obvious. You don't need to "prove" it. Similarly, assigning a Dog to an Animal reference is natural — no explicit conversion needed.

---

### Q4. Is downcasting safe or risky?

**Definition:**
**Risky.** Downcasting can fail at runtime with `ClassCastException` if the actual object is not of the target type. Always use `instanceof` to check before downcasting.

**Example:**
```java
Animal a = new Dog();
Dog d = (Dog) a;       // ✅ Safe — actual object IS a Dog

Animal a2 = new Animal();
// Dog d2 = (Dog) a2;  // ❌ RUNTIME ERROR: ClassCastException

// Safe pattern
if (a instanceof Dog) {
    Dog d3 = (Dog) a;
    d3.fetch();
}
```

**Real-World Analogy:**
Opening a **labeled box**. The box says "Fruit" (parent). You assume it's an "Apple" (child) and try to bite it. If it's actually an apple, great. If it's a coconut, you break your teeth (`ClassCastException`). **Always check** what's inside before assuming.

---

# 11. Tricky OOP Questions

---

### Q1. Can an interface extend a class?

**Definition:**
**No.** An interface **cannot** extend a class. An interface can only extend another interface. A class can extend another class, and a class can implement an interface — but an interface extending a class is not allowed.

**Example:**
```java
class Animal { }
interface Movable { }

// interface Pet extends Animal { } // ❌ COMPILE ERROR — interface can't extend class
interface Flyable extends Movable { } // ✅ Interface extends interface — allowed
class Dog extends Animal implements Movable { } // ✅ Class extends class, implements interface
```

**Real-World Analogy:**
A **rulebook** (interface) can reference and build upon another **rulebook** (interface extends interface). But a rulebook cannot extend a **person** (class) — they're fundamentally different things. A person can follow rulebooks, but a rulebook cannot become a person.

---

### Q2. Can a class extend an interface?

**Definition:**
**No.** A class **cannot extend** an interface. A class **implements** an interface. The `extends` keyword is used for class-to-class or interface-to-interface relationships. The `implements` keyword is used for class-to-interface.

**Example:**
```java
interface Drawable { void draw(); }

// class Circle extends Drawable { } // ❌ COMPILE ERROR
class Circle implements Drawable {    // ✅ Correct
    public void draw() { System.out.println("Drawing circle"); }
}
```

**Real-World Analogy:**
You don't **"extend" a contract** (interface) — you **"implement"** it. A contract says what must be done; you sign it and do the work. "Extending" implies inheriting identity, but a contract is a set of rules, not an identity.

---

### Q3. What happens if two interfaces have the same default method?

**Definition:**
If a class implements two interfaces that have the same `default` method, the compiler forces the class to **override that method** to resolve the conflict. Otherwise, it's a compile error.

**Example:**
```java
interface A {
    default void greet() { System.out.println("Hello from A"); }
}
interface B {
    default void greet() { System.out.println("Hello from B"); }
}

class MyClass implements A, B {
    @Override
    public void greet() {
        A.super.greet(); // explicitly pick A's version
        // or B.super.greet(); // pick B's version
        // or write your own implementation
    }
}

new MyClass().greet(); // Hello from A
```

**Real-World Analogy:**
Two **managers** give you conflicting instructions for the same task. You can't follow both blindly. You must **decide** — follow Manager A's way, Manager B's way, or do it your own way. Java forces you to make that decision explicitly.

---

### Q4. Can an interface have constructors?

**Definition:**
**No.** Interfaces **cannot have constructors**. Constructors are used to initialize object state, and interfaces cannot be instantiated — they have no instance state to initialize. Only abstract classes and concrete classes can have constructors.

**Example:**
```java
interface Animal {
    // Animal() { }  // ❌ COMPILE ERROR — interfaces can't have constructors
    void sound();
}

abstract class Pet {
    Pet() { System.out.println("Pet created"); } // ✅ Abstract class can
    abstract void play();
}
```

**Real-World Analogy:**
A **traffic rule book** (interface) doesn't need a "setup process" (constructor). It just defines rules. But a **driving school** (abstract class) needs a setup — enrollment, fees, documents. Rules don't need initialization; institutions do.

---

### Q5. Can an interface have static methods?

**Definition:**
**Yes, since Java 8.** Interfaces can have `static` methods with a body. They are called on the **interface itself**, not on implementing classes. They are **not inherited** by implementing classes.

**Example:**
```java
interface MathHelper {
    static int square(int n) {
        return n * n;
    }
}

// Called on the interface directly
System.out.println(MathHelper.square(5)); // 25

class Calculator implements MathHelper { }
// Calculator.square(5); // ❌ NOT inherited — must call on MathHelper
```

**Real-World Analogy:**
A **government helpline number** (static method on interface). You call the government office directly to use it. Individual citizens (implementing classes) don't inherit the helpline — they use the government's number.

---

### Q6. Can an interface have private methods (Java 9)?

**Definition:**
**Yes, since Java 9.** Interfaces can have `private` methods to be used as **helper methods inside default methods**. They avoid code duplication within the interface and are not visible to implementing classes.

**Example:**
```java
interface Logger {
    default void logInfo(String msg) {
        log("INFO", msg);
    }

    default void logError(String msg) {
        log("ERROR", msg);
    }

    private void log(String level, String msg) {  // Java 9+
        System.out.println("[" + level + "] " + msg);
    }
}

class AppLogger implements Logger { }

new AppLogger().logInfo("Started");  // [INFO] Started
new AppLogger().logError("Crash");   // [ERROR] Crash
// new AppLogger().log("X","Y");     // ❌ private — not accessible
```

**Real-World Analogy:**
A **recipe book** (interface) has shared steps used across multiple recipes — like "prepare the base sauce." That shared step is written privately at the back (private method). Readers (implementing classes) can't access it directly, but the published recipes (default methods) use it internally.

---

# 12. Real Interview Questions from Developers

---

### Q1. Why do we need method overriding if we can create a new method?

**Definition:**
Method overriding is essential because it enables **runtime polymorphism**. If you just create a new method with a different name, you lose the ability to use a parent reference to call different child behaviors. Overriding lets you:
1. **Use a single parent type** to handle many child types uniformly
2. **Plug in new behavior** without changing existing calling code
3. **Follow the Open/Closed Principle** — open for extension, closed for modification

**Example:**
```java
// WITHOUT overriding — must know every child type
class Dog { void dogSound() { System.out.println("Bark"); } }
class Cat { void catSound() { System.out.println("Meow"); } }
// You'd need: if Dog → dogSound(), if Cat → catSound() — messy!

// WITH overriding — polymorphism works
class Animal { void sound() { System.out.println("..."); } }
class Dog extends Animal { @Override void sound() { System.out.println("Bark"); } }
class Cat extends Animal { @Override void sound() { System.out.println("Meow"); } }

// One loop handles ALL animal types — no if-else needed
Animal[] animals = { new Dog(), new Cat() };
for (Animal a : animals) {
    a.sound(); // Bark, Meow — correct version runs automatically
}
```

**Real-World Analogy:**
Imagine every **plug in your house had a different shape** (different method names). You'd need a different socket for each device. Overriding is like having a **standard plug shape** — every device fits into the same socket, but each does something different when plugged in. Standardization is the key.

---

### Q2. Explain OOP concepts using real-world examples.

**Definition:**
Here are all four pillars explained with a single **Hospital Management System** example:

**Encapsulation — Patient Records:**
```java
class Patient {
    private String name;
    private String diagnosis;  // sensitive — can't be accessed directly

    public String getName() { return name; }
    public void setDiagnosis(String diagnosis) {
        if (diagnosis != null && !diagnosis.isEmpty()) {
            this.diagnosis = diagnosis;
        }
    }
}
// Only authorized methods can read/write diagnosis — data is protected
```
*Like a patient's medical file — locked in a cabinet, only doctors access through proper channels.*

**Inheritance — Doctor Types:**
```java
class Doctor {
    String name;
    void examine() { System.out.println("Examining patient"); }
}
class Surgeon extends Doctor {
    void performSurgery() { System.out.println("Performing surgery"); }
}
class Dentist extends Doctor {
    void checkTeeth() { System.out.println("Checking teeth"); }
}
// Surgeon and Dentist inherit examine() and add their own specialization
```
*Like all doctors share basic medical training, but each specializes further.*

**Polymorphism — Treatment Process:**
```java
class Doctor {
    void treat() { System.out.println("General treatment"); }
}
class Surgeon extends Doctor {
    @Override void treat() { System.out.println("Surgical procedure"); }
}
class Dentist extends Doctor {
    @Override void treat() { System.out.println("Dental procedure"); }
}

Doctor[] doctors = { new Surgeon(), new Dentist() };
for (Doctor d : doctors) {
    d.treat(); // Each doctor treats differently — same method call
}
```
*Like pressing "Treat" on different doctor types — each treats in their own way.*

**Abstraction — Billing System:**
```java
abstract class Billing {
    abstract double calculateBill();
    void printBill() { System.out.println("Total: ₹" + calculateBill()); }
}
class InPatientBilling extends Billing {
    double calculateBill() { return 50000; } // room + treatment + food
}
class OutPatientBilling extends Billing {
    double calculateBill() { return 2000; }  // consultation only
}
```
*Like a billing counter — you just ask for the bill. How it's calculated internally is hidden from you.*

---

### Q3. Why does Java not support multiple inheritance?

**Definition:**
Java doesn't support multiple inheritance **with classes** to avoid the **Diamond Problem** — ambiguity when two parent classes have the same method. The child wouldn't know which version to use.

Java's solution:
1. **Single inheritance with classes** — one parent only
2. **Multiple inheritance with interfaces** — if conflict arises, the implementing class must resolve it explicitly

**Example:**
```java
// The Diamond Problem (NOT valid Java)
class A { void show() { System.out.println("A"); } }
class B { void show() { System.out.println("B"); } }
// class C extends A, B { }  // ❌ Which show() to use?

// Java's solution — interfaces with explicit resolution
interface A { default void show() { System.out.println("A"); } }
interface B { default void show() { System.out.println("B"); } }
class C implements A, B {
    @Override
    public void show() {
        A.super.show(); // Developer explicitly resolves the conflict
    }
}
```

**Real-World Analogy:**
A child with **two fathers** both claiming to be the parent — legal chaos. Java says: "One father (class) only, but you can have multiple mentors (interfaces). If two mentors teach the same thing differently, you must decide which way you'll do it."

---

### Q4. Explain runtime polymorphism using a real example.

**Definition:**
Runtime polymorphism is when the JVM decides **at runtime** which overridden method to execute based on the actual object type, not the reference type. This is the foundation of flexible, extensible software.

**Example — Payment Processing System:**
```java
class Payment {
    void process(double amount) {
        System.out.println("Processing payment of ₹" + amount);
    }
}

class UPIPayment extends Payment {
    @Override
    void process(double amount) {
        System.out.println("₹" + amount + " paid via UPI (Google Pay)");
    }
}

class CardPayment extends Payment {
    @Override
    void process(double amount) {
        System.out.println("₹" + amount + " charged to Credit Card");
    }
}

class CODPayment extends Payment {
    @Override
    void process(double amount) {
        System.out.println("₹" + amount + " to be collected on delivery");
    }
}

public class Main {
    public static void main(String[] args) {
        Payment[] payments = {
            new UPIPayment(),
            new CardPayment(),
            new CODPayment()
        };

        for (Payment p : payments) {
            p.process(1500); // JVM picks correct version at RUNTIME
        }
    }
}
```

**Output:**
```
₹1500.0 paid via UPI (Google Pay)
₹1500.0 charged to Credit Card
₹1500.0 to be collected on delivery
```

The calling code (`p.process(1500)`) is the same every time. But the **actual behavior** changes based on which object is behind the reference. Tomorrow, adding `WalletPayment` requires zero changes to the loop.

**Real-World Analogy:**
A **food delivery app**. You tap "Order" (same method). But behind the scenes: if it's Swiggy, Swiggy's delivery system kicks in. If it's Zomato, Zomato's system runs. The button (method call) is the same — the processing (implementation) depends on **which app is actually handling it** at runtime.

---

### Q5. What is the difference between object-oriented and object-based languages?

**Definition:**

| Aspect | Object-Oriented Language | Object-Based Language |
|--------|------------------------|---------------------|
| Supports all 4 pillars? | ✅ Yes (Encapsulation, Abstraction, Inheritance, Polymorphism) | ❌ Partial — supports objects but NOT inheritance/polymorphism |
| Inheritance | ✅ Supported | ❌ Not supported |
| Polymorphism | ✅ Supported | ❌ Not supported |
| Examples | Java, C++, C#, Python | JavaScript (pre-ES6), VBScript |
| Objects | Based on classes | Based on prototypes or built-in objects |

> **Note:** Modern JavaScript (ES6+) added `class` syntax and supports inheritance, making it closer to object-oriented. But classic JavaScript was object-based.

**Example:**
```java
// Object-Oriented (Java) — full OOP with inheritance and polymorphism
class Animal { void sound() { System.out.println("..."); } }
class Dog extends Animal {
    @Override void sound() { System.out.println("Bark"); }
}
Animal a = new Dog(); // polymorphism
a.sound(); // Bark
```

```javascript
// Object-Based (old JavaScript) — objects exist but no class-based inheritance
var car = {
    brand: "Toyota",
    drive: function() { console.log("Driving"); }
};
car.drive(); // Works — has objects
// But no real class-based inheritance or polymorphism
```

**Real-World Analogy:**
- **Object-oriented** = A full **family system**. There are parents (classes), children (subclasses), inheritance of traits, and different behaviors (polymorphism).
- **Object-based** = A **group of individuals**. Each person (object) exists independently with their own properties. But there's no family tree, no inheritance, no shared behaviors based on lineage.