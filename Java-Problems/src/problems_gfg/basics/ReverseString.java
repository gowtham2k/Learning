package problems_gfg.basics;

public class ReverseString {
    static void main(String[] args) {
        String s = "Hello E";
        System.out.println(reverseString(s));
    }

    public static String reverseString(String s){

        StringBuilder s1 = new StringBuilder();

        char[] s_char = s.toCharArray();
        int length = s_char.length;
        for (int i = length-1; i>=0; i--){
            s1.append(s_char[i]);
        }
        return s1.toString();
    }
}
