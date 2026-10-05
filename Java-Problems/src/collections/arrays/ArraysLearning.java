package collections.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysLearning {
    public static void main(String[] args) {

        int[] arr = {5, 2, 5, 8, 9, 2, 34, 23};

        // inside Arrays util class there are methods to accept the array of primitive types and sort it

        System.out.println("before sorting...");
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();

        Arrays.sort(arr);

        System.out.println("after sorting...");
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
        System.out.println();

        // Arrays also having util functions for non-primitive types also...
        // what if we need to sort the non-primitive types

        String[] names = {"tony", "thor", "parker", "strange", "captain", "hulk"};

        System.out.println("before sorting...");
        for (String s : names) {
            System.out.println(s);
        }

        // there is a sort() method in Arrays class which accepts Object and sort it
        Arrays.sort(names);

        System.out.println();
        System.out.println("after sorting...");
        for (String s : names) {
            System.out.println(s);
        }


        // here Inheritance concept is applied
        // an obj behaving as another class obj
        // sort(Object[] o) -> here we are passing names, which is of type String, - Object is parent of String


        // parent - child extends parent - ch

        // method overloading is applied here, ex, sort(primitive_types[]) and sort(Object[] o)

        //************************************************* */

        /*
        we can also do
        - string - descending order
        - string - ascending order based on its length
        - string - descending order based on its length */


        /*
        Why do we need Comparator?

        Arrays.sort(names) already knows how to sort Strings.

        By default, String is sorted according to its natural ordering
        (lexicographical / dictionary order).

        Example:

        String[] names = {"tony", "thor", "parker", "strange"};

        Arrays.sort(names);

        Result:
        parker
        strange
        thor
        tony

        But what if we want a DIFFERENT sorting rule?

        Examples:
        1. Sort Strings by length
        2. Sort Strings by length in descending order
        3. Sort Strings alphabetically in descending order
        4. Sort objects by price
        5. Sort objects by age
        6. Sort objects by salary

        Arrays.sort() cannot know which custom rule WE want.

        Comparator allows us to provide our own sorting rule.

        So:

        Arrays.sort(names);
                ↓
        uses String's natural ordering

        Arrays.sort(names, comp);
                ↓
        uses the Comparator's custom ordering
        */


                //Dynamic Binding
                Comparator comp = new ComparatorDemo();

                Arrays.sort(names, comp);

                System.out.println("-------after sorting--------");

                for (String s : names) {
                    System.out.println(s);
                }

        /*
        Dynamic Binding

        Comparator comp = new ComparatorDemo();

        Reference type  → Comparator
        Object type     → ComparatorDemo

        The reference is of type Comparator,
        but the actual object created is ComparatorDemo.

        At compile time:
            Java knows only that 'comp' is a Comparator.

        At runtime:
            Java looks at the actual object stored in 'comp'.

        Actual object = ComparatorDemo

        Therefore, when:

            comp.compare(o1, o2);

        is executed,

        Java calls:

            ComparatorDemo.compare()

        This is Dynamic Binding / Runtime Method Dispatch.

        The method that gets executed is decided at runtime
        based on the actual object, not just the reference type.
        */

    }
}
