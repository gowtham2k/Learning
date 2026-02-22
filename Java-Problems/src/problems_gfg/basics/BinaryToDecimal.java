package problems_gfg.basics;

public class BinaryToDecimal {
    static void main(String[] args) {

        String binary = "111";
        System.out.println(toDecimal(binary));
    }

    public static int toDecimal(String binary){
        int ans =0;
        int j =1;
        int l = binary.length();
        for(int i = l-1; i>=0; i--){
            if (binary.charAt(i)=='1'){
                ans += j;
            }
            j*=2;
        }
        return ans;
    }
}
