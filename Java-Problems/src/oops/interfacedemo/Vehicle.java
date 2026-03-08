package oops.interfacedemo;

// 3. so here instead of abstract class, we can use interface

// abstract public class Vehicle {
//     abstract void ride();
// }

/**
 * Interface example used to explain abstraction through contracts.
 */
public interface Vehicle {
    // Interface - tells 'WHAT TO DO', but not 'HOW TO DO'
    // We can achieve multiple inheritance because of this Interface
    void ride();

    // in interface every method is abstract method
    // on abstract class we can have normal defined methods, BUT in interface
    // every method should be abstract method
    // by default every method is 'public abstract'



// Interface - tells 'WHAT TO DO', but not 'HOW TO DO'
// class which implements this should define the ride(), but if not we can declare it
// as abstract class

    // Example only: an abstract class can partially implement an interface
    // and let concrete subclasses finish the remaining methods.
    abstract class PartialVehicle implements Vehicle {}

// WHEN TO USE INTERFACE AND ABSTRACT CLASS
/*
    - Mostly its suggested to use Interface, because if we define more abstract class
    later we can't extends more class

    - but in scenario like we should define a method too, we can use Abstract class

    --- the above is a general view, while developing Java they had these Interface and
    Abstract class based on the above concepts

    -- But now recently java made many changes like we can define a method in interface

    - To compete with another languages In java 1.8, they allowed to define a method in interface
    from 1.8 they introduced default method, with this we can define a method in interface

*/

// 7. we should use default to define a method in interface 
    default void display() {
        System.out.println("hello from Interface");
    }

// 8. we can declare variables too interface

    // we can't create instance variables, because there may be a chance for multiple inheritance problem

    int MAX_SPEED = 120; // this will be PUBLIC, FINAL and STATIC by default


    /*

- Tells what to do but not how to do
- Methods are abstract and public by default
- Methods can be defined in interface from Java 1.8 with the keyword default
- Variables are final, static, and public by default
- Interface cannot be instantiated — no constructors
- A class can implement interface/interfaces
- Class implementing an interface should define all abstract methods in the interface
- Supports multiple inheritance
- An interface can extend another interface/interfaces



Interface – Key Points

1️⃣ Defines a contract
It tells what a class should do, not how to do it.

2️⃣ Methods are public and abstract by default
No body unless using default or static methods (Java 8+).

3️⃣ Variables are constants
All variables are public, static, final by default.

4️⃣ Cannot create objects
Interfaces cannot be instantiated and have no constructors.

    5️⃣ Supports multiple inheritance
A class can implement multiple interfaces.
    */


// In interface, we can write many default methods, but if there is only one 
// abstract method present in an interface it is called as
// -> Single Abstract Method Interface (SAM Interface) or Functional Interface
// From Java 1.8, if we have functional Interface, we can use lambda expression


}
