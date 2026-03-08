package oops.inheritance;

/**
 * Base class used to explain oops.inheritance.
 * Common state and behavior live here so child classes can reuse them.
 */
public class Employee {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Employee(){
    //     name = "";
    //     salary = 0;
    // }

    /**
     * Raises the current salary by a given percentage.
     */
    public double raiseSalary(double percentage) {
        return salary += salary * percentage / 100;
    }

    public void printInfo() {
        System.out.println("employee name: " + name);
        System.out.println("employee salary: " + salary);
    }

    public void work() {
        System.out.println("Employee is working.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
