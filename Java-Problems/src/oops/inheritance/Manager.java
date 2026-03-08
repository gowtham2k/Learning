package oops.inheritance;

// to use the employee features we have to inherit it

/**
 * Derived class used to show constructor chaining and method overriding.
 */
public class Manager extends Employee { // this is called as derived class, Employee is base class
    // employee - super class, parent class, base class || Manager - sub class, child class, derived class

    // when we can use oops.inheritance - 'is-a' rule ==> ex, Manager is also a Employee, Car is a Vehicle
    private double bonus;

    /* when we give like this initially we will get error like 

    Implicit super constructor Employee() is undefined. Must explicitly invoke another constructor
    
    Manager(String name, int salary, double bonus){
        this.name = name;
        this.salary = salary;
        this.bonus = bonus;
    }

    to avoid this we have to use a dummy constructor in employee

    Employee(){
        name = "";
        salary = 0;
    } 
        - we can avoid this by

    */

    /* now we are not making the variables as private, but we have to make it private and call those by using the methods only
    if we make the variables present in Employee as private, we will get error in this Manager Constructor as  - The field Employee.name is not visible
    
    how to fix this, we can't access the variable directly, we have to use the method, but this is constructor and no need to call the method here

        public Manager(String n, int s, double b){
        name = n;
        salary = s;
        bonus = b;
        }

        - what we are trying to do here is calling the 
        - in base class we are already having a constructor which is initializing the name and salary,
        - now again we are trying to create it in derived class, as it is private java is not allowing
        - so here what we can do is call the base class constructor to initialize them
        - by using super() - used to refer the super class in the sub class
    */

    // Manager(String name, int salary, double bonus){
    //     this.name = name;
    //     this.salary = salary;
    //     this.bonus = bonus;
    // }

    public Manager(String n, double s, double b) {
        // super(); // calling the super class, pointing the super class constructor
        super(n, s); // calling it and delegating initialization of name and salary to that Employee constructor

        /* if we didn't add super(n,s) java will the base class using super() without parameter
        if there is no constructor without param it will give error like 
        --- Implicit super constructor Employee() is undefined. Must explicitly invoke another constructor ---- 
        so that we manually passing n, s and mentioning the parameterized constructor
        */

        this.bonus = b;
    }

    // METHOD OVERRIDING
    @Override
    public double getSalary() {

        // here i want to return the salary + bonus

        // The field Employee.salary is not visible - we will get this error
        // return salary + bonus;

        // we may think we can call using
        // return getSalary() + bonus; - but the method name is already getSalary(), so java will assume we are again calling the method
        // how can we tell to call the base class salary
        return super.getSalary() + bonus;
    }

    @Override
    public void printInfo() {
        super.printInfo(); // calling the parent class method so that we can have name and salary
        System.out.println("bonus: " + bonus);
    }

    @Override
    public void work() {
        System.out.println("Manager is managing the team");
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
