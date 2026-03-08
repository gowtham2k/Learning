package oops.interfacedemo;

/**
 * Another implementation of Vehicle used to show polymorphism.
 */
public class Bike implements Vehicle {

    //4.  we will get this error: Cannot reduce the visibility of the inherited method from VehicleJava(67109273)

    // void ride(){
    //     System.out.println("riding a bike.....");
    // }

    // as in interface the methods are public and abstract by default, so here in bike class
    // if we declare the access modifier, it will be default so we have to make it to public

    @Override
    public void ride() {
        System.out.println("riding a bike....");
    }
}
