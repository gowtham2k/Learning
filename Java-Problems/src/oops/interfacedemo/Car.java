package oops.interfacedemo;

/**
 * Concrete implementation of Vehicle.
 */
public class Car implements Vehicle {

    // 2. public class Car extends Vehicle{ -> we can use abstract class for this
    // BUT, if car need to inherits another class like 'extends Vehicle, Xyz'
    // this is not possible in java as this is like multiple inheritance.
    // We can't extend two abstract classes
    // so what we can do is to have the interface

    // 5. here we can implement many interfaces and one abstract class, like
    // public class Car implements Vehicle, AnotherInterface extends OneAbstractClass {}

    @Override
    public void ride() {
        System.out.println("riding a car");
    }

    // 8. Lets check can we override a default method from the interface
    @Override
    public void display() {
        System.out.println("Hello from Car");
    }
}
