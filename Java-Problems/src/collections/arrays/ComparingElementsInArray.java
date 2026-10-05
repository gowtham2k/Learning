package collections.arrays;

import java.util.Arrays;

public class ComparingElementsInArray {
    static void main(String[] args) {
        int[] a = {1,2,3,4};
        int[] b = {1,2,3};
        // compare each element index wise and see are they equal

//        int i = 0;
//        boolean same = true;
//        while(i < a.length){
//            if(a[i] != b[i]){
//                same = false;
//                break;
//            }
//            i++;
//        }
//        System.out.println(same);

        // for this there is an inbuilt method in Arrays
        // this will do the same
        boolean same2 = Arrays.equals(a,b);
        System.out.println(same2);
    }
}
