package problems_gfg.basics;

/*
📄 Problem Statement

Given two strings:
txt → the main text
pat → the pattern to search
Return a list of all starting indices where the pattern appears in the text.

Example 1:
txt = "aaaaa"
pat = "aaa"

Output:
[0, 1, 2]

**/

import java.util.ArrayList;
import java.util.List;

public class OccurrencesOfStringPattern {
    static void main(String[] args) {

        String txt = "abc df abc fd ab";
        String pat = "ab";

        System.out.println(findOccurrences(txt, pat));

    }

    public static List<Integer> findOccurrences(String txt, String pat) {
        List<Integer> result = new ArrayList<>();

        // ✏️ TODO: Write your code here

        int pos = txt.indexOf(pat);

        while(pos>=0){
            result.add(pos);
            pos = txt.indexOf(pat, pos+1);
        }

        return result;
    }

}
