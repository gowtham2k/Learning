package oops.interfacedemo;

/*
we can think like to solve some problems in abstract class, interface was introduced
*/

/**
 * Demonstrates interface-based design, polymorphism, and default methods.
 */
public class Main {
    public static void main(String[] args) {
        Car c = new Car();
        Mechanic m = new Mechanic();
        m.check(c);

        // 1. here in the m.check we can't pass bike to mechanic as in the Mechanic we are defined 
        // car to pass. As per oops we should not write the redundant code/ another method
        // to pass the bike obj. We can create a parent class and create the ride method there
        // and then make the derived class inherit it

        Bike b = new Bike();
        m.check(b);

        //6. Mechanic will invoke bike and car's ride by like dynamic binding

        // we can do like this too, so that we are receiving car, bike in Mechanic
        Vehicle v = new Bike();
        // but we can't do this
        // Vehicle v1 = new Vehicle();

        v.display(); // as bike implements V, we can use display()
        c.display(); // we can override default method too


        // Vehicle v1 = new Vehicle(); -> we can't do like this but we can create anonymous class
        /*
    Use Anonymous Inner Class when:

    ✅ Implementation is small
    ✅ Used only once
    ✅ Don't want to create a separate class

    an anonymous inner class is often used when: We create a class only to override or implement a method for one-time use.

*/

        Vehicle v1 = new Vehicle() {
            public void ride(){
                System.out.println("riding from anonymous inner class....");
            }
        };

        v1.ride();

        // lambda expression - to use this we should have functional interface, only one abstract method
        // 1. after Vehicle v2, it will be always new Vehicle() - so we don't need to mention it
        // 2. we don't need to mention the we will override ride() because we are having only one abstract method in the interface

        Vehicle v2 = () -> System.out.println("riding from lambda expression");
        v2.ride();

    }
}
