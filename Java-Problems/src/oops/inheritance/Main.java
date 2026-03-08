package oops.inheritance;



public class Main {
    public static void main(String[] args) {
        // 1. Base class object using shared employee behavior.
        /*1. created a employee class with salary and name and raise salary method
        created one employee and raised the salary and print it */
        Employee e1 = new Employee("john", 10000);
        e1.raiseSalary(10);
        System.out.println(e1.getSalary());

        // 2. Child class adds manager-specific state on top of Employee.
        /* 2. now another employee is Manager, he may get bonus which is not applicable to normal employee */
        // do we need to create a new manager class and again write all the things in employee to manager, no need,
        // we can inherit the property and define only manager specific things in the manager class

        Manager m1 = new Manager("cena", 30000, 0);
        // for the manager I want to add this bonus above the salary
        m1.setBonus(15000);
        System.out.println(m1.getSalary());

        e1.printInfo();
        System.out.println("------------");
        m1.printInfo();


        Employee e2 = new Manager("roman", 30000, 0); // we can do like this and this is allowed, bcoz Manager "is-a" employee
        
        //🚨 Why error happens
        //Compiler looks at the reference type (Employee) and asks:
        //“Does Employee have setBonus()?” = Answer: ❌ NO, So compiler stops you.

        // 🔥 Visual memory model
        // Employee e2  ─────►  Manager object
        // (reference)          (real object)
        // Compiler only trusts the left side type.
        // e2.setBonus(); - this will give error

    // The Statement: Parent reference can hold child object, but can only access parent members.
    // Example:
    // Employee e = new Manager("Roman", 30000, 0);
/*
| Part       | Type                       |
| ---------- | -------------------------- |
| `Employee` | reference type (parent)    |
| `Manager`  | actual object type (child) |


Stack stores reference type = Employee

Heap stores actual object = Manager


🔎 What methods can be called?

The compiler checks the reference type.

Since the reference is Employee, the compiler only allows methods inside Employee.

Employee e = new Manager("Roman", 30000, 0);

e.getSalary();   // ✅ allowed (exists in Employee)
e.printInfo();   // ✅ allowed (exists in Employee)

e.setBonus();    // ❌ ERROR

Why?

Because setBonus() exists only in Manager.

The compiler thinks:

“e is an Employee reference, Employee does not have setBonus().”

*/



    // Manager m2 = new Employee(null, 0); this is not allowed

        e2.work();
        /*
        ⚡ What implementation runs?
        When you call: e.work();

        Two possibilities exist:

        Employee.work()
        Manager.work()
        
        Since the actual object is Manager, Java runs: Manager.work()

        📊 Flow of execution:

Employee e = new Manager();
        │
        │ reference type decides allowed methods
        ▼
Compiler allows: work()

At runtime:
        │
        │ object type decides implementation
        ▼
Manager.work() runs
        */


/*
🧠 What is Dynamic Binding?

Dynamic Binding means:

The method implementation to execute is decided at runtime based on the actual object type, not the reference type.

It happens when method overriding is involved.

Dynamic binding means the JVM decides the method implementation at runtime based on the actual object type.

*/

    }
    
}
