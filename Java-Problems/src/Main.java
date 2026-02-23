class Student{
    String name;
    int age;
}

class Main{
    static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "john";
        s1.age = 20;

        System.out.println(s1.name);
        System.out.println(s1.age);

        Student s2 = s1;
        System.out.println("-------after s2 = s1");
        System.out.println(s2.name);
        System.out.println(s2.age);

        s2.name = "cena";
        System.out.println("after changing s2 name");
        System.out.println("s2 name "+ s2.name);
        System.out.println("s1 name "+ s1.name);
        
    }
}