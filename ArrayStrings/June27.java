import java.util.*;

public class June27 {
    public static void main(String[] args) {
        // maxUnsortedSubArrToMakeSort();
        // squaresInSortedOrder();
        // poisonEveryKMinutes();
        // similarRGB();
        // productOfArr();
        // quadraticEq(); 
        // shortestDisBW2Num();
        // shortestDisBW2SameNum();
        // System.out.println(toeplitzMatrix());
        // System.out.println(oneAanagram());
        // longestPairInStr();
        // ransomeNote();
        // sortArr0_1UsingParity();
        // sortArrOddEvenUsingParity();
        // spiralMatrix();
        // removeComments();
    }

    public static void maxUnsortedSubArrToMakeSort() {
        int[] arr = { 2, 3, 5, 7, 9, 6, 12, 13, 14, 15 };
        int[] minsf = new int[arr.length];
        int[] maxsf = new int[arr.length];
        int min = arr[arr.length - 1];
        int max = arr[0];
        for (int i = 0, j = minsf.length - 1; i < maxsf.length && j >= 0; i++, j--) {
            min = Math.min(min, arr[j]);
            minsf[j] = min;
            max = Math.max(max, arr[i]);
            maxsf[i] = max;
        }
        System.out.println("unsorted indicies");
        for (int i = 0; i < arr.length; i++) {
            if (minsf[i] != maxsf[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void squaresInSortedOrder() {
        int arr[] = { -4, -1, 0, 3, 10 };// sorted
        int sa[] = new int[arr.length];
        int i = 0;
        int j = arr.length - 1;
        int c = arr.length - 1;
        ;
        while (i != j) {
            if (arr[i] * arr[i] < arr[j] * arr[j]) {
                sa[c] = arr[j] * arr[j];
                j--;
                c--;
            } else {
                sa[c] = arr[i] * arr[i];
                i++;
                c--;
            }
        }
        for (int itr : sa) {
            System.out.print(itr + " ");
        }
    }

    public static void poisonEveryKMinutes() {
        int[] arr = { 1, 4, 12, 14, 16, 24, 29, 37, 42, 49 };
        int k = 5;
        int s = arr[0];
        int e = arr[0] + k;
        for (int i : arr) {
            if (i <= e) {
                e = i + k;
            } else {
                System.out.println(s + " " + e + " => " + (e - s));
                s = i;
                e = i + k;
            }
        }
    }

    public static void similarRGB() {
        List<Character> arr=Arrays.asList('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f');
        String str = "a37b2d";
        String ans = "#";
        for (int i = 0; i < str.length(); i += 2) {
            String substr = str.substring(i, i + 2);
            int n=Integer.parseInt(substr,16);
            int nm1=0;
            int nn=0;
            int np1=0;

            String nnstr=substr.substring(0,1)+substr.substring(0,1);
            nn=Integer.parseInt(nnstr, 16);
                if(arr.indexOf(substr.charAt(0))>arr.indexOf(substr.charAt(1))){
                    String nm1str=arr.get(arr.indexOf(substr.charAt(0))-1).toString()+arr.get(arr.indexOf(substr.charAt(0))-1).toString();
                    nm1=Integer.parseInt(nm1str, 16);
                }else{
                    String np1str=arr.get(arr.indexOf(substr.charAt(0))+1).toString()+arr.get(arr.indexOf(substr.charAt(0))+1).toString();
                    np1=Integer.parseInt(np1str, 16);
                }
                if(nm1==0){
                    if((n-nn)<(np1-n)){
                        ans+=Integer.toHexString(nn).charAt(0);
                    }else{
                        ans+=Integer.toHexString(np1).charAt(0);
                    }
                }else if(np1==0){
                    if((n-nm1)<(nn-n)){
                        ans+=Integer.toHexString(nm1).charAt(0);
                    }else{
                        ans+=Integer.toHexString(nn).charAt(0);
                    }
                }
        }
        System.out.println(ans);
    }

    public static void productOfArr() {
        int[] arr = { 1, 2, 3, 4 };
        int[] leftMulsf = new int[arr.length];
        int[] rightMulsf = new int[arr.length];
        leftMulsf[0] = 1;
        rightMulsf[arr.length - 1] = 1;
        for (int i = 1, j = arr.length - 2; i < arr.length && j >= 0; i++, j--) {
            leftMulsf[i] = arr[i - 1] * leftMulsf[i - 1];
            rightMulsf[j] = arr[j + 1] * rightMulsf[j + 1];
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(leftMulsf[i] * rightMulsf[i] + " ");
        }
    }

    public static void quadraticEq() {

    }

    public static void shortestDisBW2Num() {

    }

    public static void shortestDisBW2SameNum() {

    }

    public static boolean toeplitzMatrix() {
        int[][] arr = { { 1, 2, 3, 4 }, { 4, 1, 2, 3 }, { 3, 4, 1, 2 }, { 2, 3, 4, 1 } };
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i + 1 < arr.length && j + 1 < arr[i].length) {
                    if (arr[i + 1][j + 1] != arr[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean oneAanagram() {
        String a = "abcdefg";
        // String b="abchefg";
        // String b="abcdfg";
        String b = "habcdefg";
        if (a.length() == b.length()) {
            int c = 0;
            int i = 0;
            int j = 0;
            while (i < a.length()) {
                if (a.charAt(i) != b.charAt(j)) {
                    c++;
                    i++;
                    j++;
                }
                i++;
                j++;
            }
            if (c > 1) {
                return false;
            }
        }

        else if (a.length() > b.length()) {
            int c = 0;
            int i = 0;
            int j = 0;
            while (i < a.length()) {
                if (a.charAt(i) != b.charAt(j)) {
                    c++;
                    i++;
                }
                i++;
                j++;
            }
            if (c > 1) {
                return false;
            }
        }

        else if (a.length() < b.length()) {
            int c = 0;
            int i = 0;
            int j = 0;
            while (i < a.length()) {
                if (a.charAt(i) != b.charAt(j)) {
                    c++;
                    j++;
                }
                i++;
                j++;
            }
            if (c  > 1) {
                return false;
            }
        }
        return true;
    }

    public static void longestPairInStr(){
        String str="aaaabbbccccccddeee";
        int max=0;
        int i=1;
        int s=0;
        while(i<str.length()){
            if(str.charAt(i-1)==str.charAt(i)){
                i++;
            }else{
                System.out.println(str.charAt(i-1)+" "+(i-s));
                max=Math.max(max, (i-s));
                s=i;
                i++;
            }
        }

        System.out.println(max);
    }
    public static void ransomeNote(){
        String one="a";
        String two="b";
        // String one="aa";
        // String two="ab";
        // String one="aa";
        // String two="aab";
        boolean flag=true;
        HashMap<Character,Integer> map1= new HashMap<>();
        HashMap<Character,Integer> map2= new HashMap<>();
        for(Character c:one.toCharArray()){
            map1.put(c, map1.containsKey(c)?map1.get(c)+1:1);
        }
        for(Character c:two.toCharArray()){
            map2.put(c, map2.containsKey(c)?map2.get(c)+1:1);
        }
        for(char c:map1.keySet()){
            if(map2.containsKey(c)){
                if(map2.get(c)>=map1.get(c)){
                    //ok
                }else{
                    flag=false;
                    break;
                }
            }else{
                flag=false;
                break;
            }
        }
        if(flag){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
    public static void sortArr0_1UsingParity(){
        int[] arr={0,1,0,1,0,1,0,1,0,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1};
        int i=0;
        int j=0;
        while(j<arr.length){
            if(arr[j]==0){
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                i++;
                j++;
            }else{
                j++;
            }
        }
        for(int itr:arr){
            System.out.print(itr);
        }
    }
    
    public static void sortArrOddEvenUsingParity(){
        int[] arr={2,0,7,3,1,8,8,0,9,2,7,9,5,4,3,7,0};
        int i=0;
        int j=0;
        while(j<arr.length){
            if(arr[j]%2==0){
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                i++;
                j++;
            }else{
                j++;
            }
        }
        for(int itr:arr){
            System.out.print(itr);
        }
    }
    public static void spiralMatrix(){
        int[][] arr={{2,4,3,5},
                     {5,7,4,6},
                     {5,4,3,8},
                     {7,8,9,0}};
        int tc=arr.length*arr[0].length;
        int sr=0;
        int er=arr.length-1;
        int sc=0;
        int ec=arr[0].length-1;
        while(tc>0){
            for(int i=sr;i<=er;i++){
                System.out.print(arr[i][sc]);
                tc--;
            }
            sc++;
            for(int i=sc;i<=ec;i++){
                System.out.print(arr[er][i]);
                tc--;
            }
            er--;
            for(int i=er;i>=sr;i--){
                System.out.print(arr[i][ec]);
                tc--;
            }
            ec--;
            for(int i=ec;i>=sc;i--){
                System.out.print(arr[sr][i]);
                tc--;
            }
            sr++;
        }
    }

    public static void removeComments(){
        String str="this is the java programming /*java1.8*/ all interview /*prep*/ ques are here";
        int i=0;    
        boolean f=true;
        while(i<str.length()){
            if(str.charAt(i)=='/'){
                f=!f;
                i++;
            }
            if(f){
                System.out.print(str.charAt(i));
                i++;
            }else{
                i++;
            }
        }
    }
}