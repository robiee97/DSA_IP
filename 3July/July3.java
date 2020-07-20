
import java.util.*;

public class July3 {
    public static void main(String[] args) {
        // choclateDis();
        // maxSubArrSuminCircularArr();
        // sumOfSubseqWidths();
        // subArrProductLessThanK();
        // shortestPallindrom();
    }

    public static void choclateDis() {
        int[] arr = { 5, 45, 7, 23, 35, 11, 25, 21, 47, 51 };
        int k = 3;
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = i + k - 1;
        while (j < arr.length) {
            min = Math.min(min, arr[j] - arr[i]);
            i++;
            j++;
        }
        System.out.println(min);
    }

    public static int kadaneP(int[] arr) {
        int cm = 0;
        int om = 0;
        for (int i = 0; i < arr.length; i++) {
            cm = cm > 0 ? cm + arr[i] : arr[i];
            om = om > cm ? om : cm;
        }
        return om;
    }

    public static int kadaneN(int[] arr) {
        int cm = 0;
        int om = 0;
        for (int i = 0; i < arr.length; i++) {
            cm = cm < 0 ? cm + arr[i] : arr[i];
            om = om < cm ? om : cm;
        }
        return om;
    }

    public static void maxSubArrSuminCircularArr() {
        int[] arr = { 2, 3, -6, -1, -5, 7, 2, 3 };
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int kp = kadaneP(arr);
        int kn = sum - kadaneN(arr);
        System.out.println(Math.max(kn, kp));
    }

    public static void sumOfSubseqWidths() {
        int[] arr = { 6, 2, 5, 9 };
        Arrays.sort(arr);
        int sum = 0;
        for (int i = 0, j = arr.length - 1; i < arr.length && j >= 0; i++, j--) {
            sum += Math.pow(2, i) * arr[i];
            sum -= Math.pow(2, j) * arr[i];
        }
        System.out.println(sum);
    }

    public static void subArrProductLessThanK(){
        int[] arr={2,5,9,6,3,4,7,5,2,1,8};
        int k=100;
        int e=0;
        int s=0;
        int prod=1;
        while(e<arr.length){
            if(prod>k){//invalid ~ greater than k product
                while(prod>=k){
                    prod/=arr[s];
                    s++;
                }
            }else{//acquire and print subarrays
                while(e<arr.length&&prod<k){
                    prod*=arr[e];
                    for(int i=s;i<e;i++){
                        System.out.print(arr[i]+" ");
                    }
                    System.out.println();
                    e++;
                }
            }
        }
    }
    public static void shortestPallindrom(){

    }
}
