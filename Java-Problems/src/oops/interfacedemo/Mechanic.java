package oops.interfacedemo;

/**
 * Depends on the interface type instead of one concrete class.
 * This keeps the method generic and reusable.
 */
public class Mechanic {

    // instead of this we can make this more generic

    // void check(Car c){
    //     System.out.println("Checking.....");
    //     c.ride();
    // }

    public void check(Vehicle v) {
        System.out.println("checking....");
        v.ride();
    }
}
