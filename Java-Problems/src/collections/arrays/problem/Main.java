package collections.arrays.problem;

/*
*sort the products by:
*Price in descending order
*
* */

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    static void main(String[] args) {
        Product[] products = {
                new Product("pixel", 80000),
                new Product("xiaomi", 80000),
                new Product("samsung", 90000),
                new Product("onePlus", 70000),
                new Product("vivo", 70000),
                new Product("iphone", 1_00_000)
        };

        Comparator<Product> pc = new ProductComparator();
        Arrays.sort(products, pc);

        System.out.print(Arrays.toString(products) + " ");
    }
}
