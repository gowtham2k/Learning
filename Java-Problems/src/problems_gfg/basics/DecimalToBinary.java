package problems_gfg.basics;

public class DecimalToBinary {
    public static void main(String[] args) {
        int n = 46;

        System.out.println(toBinary(n));
    }

    public static String toBinary(int n){
        if (n == 0) return "0";  // handle edge case
        StringBuilder s = new StringBuilder();

        while(n>0){

            int remainder = n%2;
            s.append(remainder);
            n = n/2;
        }
        s.reverse();
        return s.toString();
    }
}
