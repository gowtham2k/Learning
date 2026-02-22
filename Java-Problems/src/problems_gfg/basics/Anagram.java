package problems_gfg.basics;

import java.util.Arrays;

public class Anagram {
    static void main(String[] args) {
        String s1 = "abaac";
        String s2 = "aacba";
        System.out.println(isAnagram(s1,s2));
    }

    public static boolean isAnagram(String s1, String s2){
        // 1. to char array
        char[] sc1 = s1.toCharArray();
        // 2. sort it
        Arrays.sort(sc1);
        // 3. convert it to string
        s1 = new String(sc1);

        char[] sc2 = s2.toCharArray();
        Arrays.sort(sc2);
        s2 = new String(sc2);

        return s1.equals(s2);
    }
}
