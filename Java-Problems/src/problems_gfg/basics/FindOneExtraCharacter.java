package problems_gfg.basics;

import java.util.Arrays;

public class FindOneExtraCharacter {
    static void main(String[] args) {
        String a = "abcd";
        String b = "abbcsd";

        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(b1);

        int l = a1.length;
        for(int i=0; i<l-1; i++){
            if (a1[i] != b1[i]){
                System.out.println(b1[i]);
            }
        }
        System.out.println(b1[l]);

    }
}
