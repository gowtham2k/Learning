package problems_gfg.basics;

public class DigitsAfterDecimal {
    static void main(String[] args) {
        String s = "34.868";

        int pos = s.indexOf('.');
        if (pos < 0) {
            System.out.println("");
        } else {
            System.out.println(s.substring(pos + 1));
        }
    }
}
