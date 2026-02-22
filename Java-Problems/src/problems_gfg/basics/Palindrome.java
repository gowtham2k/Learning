package problems_gfg.basics;

public class Palindrome {
    static void main(String[] args) {
        String s1 = "abcjdcba";
        System.out.println(isPalindrome(s1));
    }

    public static boolean isPalindrome(String s1){
        char[] s2 = s1.toCharArray();

        int n = s2.length;

        int i = 0; int j = n-1;

        while(i<j){
            if(s2[i] != s2[j])
                return false;
            i++;
            j--;
        }
        return true;
    }
}
