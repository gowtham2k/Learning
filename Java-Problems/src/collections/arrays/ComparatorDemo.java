package collections.arrays;

import java.util.Comparator;

public class ComparatorDemo implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        // TODO Auto-generated method stub

        // there are two ways to convert an obj into a string
        String s1 = (String) o1;
        String s2 = o2.toString();

        if (s1.length() > s2.length()) {
            return 1;
        } else if (s1.length() < s2.length()){
            return -1;
        } else {
            return 0;
        }        
    }



    /*
================ COMPARABLE vs COMPARATOR ================

Comparable:
    Defines the object's NATURAL ordering.

    Example:
    String implements Comparable<String>

    Therefore:

    Arrays.sort(names);

    can sort Strings using their natural ordering.

Comparator:
    Defines a CUSTOM ordering.

    Example:

    Arrays.sort(names, comparator);

    can sort Strings:
        - by length
        - reverse alphabetical order
        - by some custom rule

Comparable → "How should this object normally be sorted?"

Comparator → "How do I want to sort this object THIS TIME?"
*/


    // why we need comparator

    
}
