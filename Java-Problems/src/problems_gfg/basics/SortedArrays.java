package problems_gfg.basics;

public class SortedArrays {
    public static void main(String[] args) {

//        int[] n = {1,2,3,4,5,5,78};
        int[] n = { 12, 43, 54, 22, 33, 55};

        System.out.println(isSorted(n));
    }

    public static boolean isSorted(int[] arr){

        int length = arr.length;

        for (int i =0; i<length-1; i++){
            if (arr[i] > arr[i+1]){
                return false;
            }
        }
        return true;
    }
}
