package collections.arrays.problem;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {

//    @Override
//    public int compare(Product o1, Product o2) {
//        int p1 = o1.getPrice();
//        int p2 = o2.getPrice();
//
//        if(p1 > p2){
//            return -1;
//        } else if (p1<p2) {
//            return 1;
//        } else {
//            //If two products have the same price, sort by name alphabetically.
//            return o1.getName().compareTo(o2.getName());
//        }
//    }
    @Override
    public int compare(Product p1, Product p2){

        // by this we can compare lexicographical order - descending order.
        // if we need to do this manually we have to write a lot of code
        int result = p1.getName().compareTo(p2.getName());
        if (result > 0){
            return -1;
        } else if (result < 0){
            return 1;
        } else {
            return 0;
        }
    }
}
