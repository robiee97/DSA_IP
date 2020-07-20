import java.util.HashSet;

public class July8IP2 {

    public static void addRemRep() {
        String str1 = "abcde";
        String str2 = "acxye";

        int[][] strg = new int[str1.length() + 1][str2.length() + 1];
        for (int i = strg.length - 1; i >= 0; i--) {
            for (int j = strg[0].length - 1; j >= 0; j--) {
                if (j == strg[0].length - 1 && i == strg.length - 1) {
                    strg[i][j] = 0;
                } else if (j < strg[0].length - 1 && i == strg.length - 1) {
                    strg[i][j] = strg[i][j + 1];
                } else if (i < strg.length - 1 && j == strg.length - 1) {
                    strg[i][j] = strg[i + 1][j];
                } else {
                    if (str1.charAt(i) == str2.charAt(j)) {
                        strg[i][j] = strg[i + 1][j + 1];
                    } else {
                        int min = Math.min(strg[i + 1][j + 1], Math.min(strg[i + 1][j], strg[i][j + 1]));
                        strg[i][j] = min + 1;
                    }
                }
            }
        }
        System.out.println(strg[0][0]);
    }

    public static void maxsumNoadjacent() {
        int[] arr = { 5, 6, 10, 100, 10 };
        int[] inc = new int[arr.length];
        int[] exc = new int[arr.length];
        inc[0] = arr[0];
        exc[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            inc[i] = exc[i - 1] + arr[i];
            exc[i] = Math.max(inc[i - 1], exc[i - 1]);
        }
        System.out.println(Math.max(inc[arr.length - 1], exc[arr.length - 1]));
    }

    public static void matrixChainMult() {

    }

    public static void greedyCoinPick() {

    }

    public static void optimalBST() {

    }

    public static void catalanNo() {

    }

    public static void jobSequence() {

    }

    public static void fractionalKS() {

    }

    public static void largestSquare(){
        int[][] mat= {{1,0,0,1,0,0,1,0},
        {1,1,1,1,1,1,1,1},
        {1,1,0,1,1,1,1,1},
        {1,0,1,1,1,1,1,0},
        {0,1,1,1,1,1,1,1},
        {1,0,1,0,1,1,0,1},
        {1,0,0,1,1,1,1,1}};

        int max=0;
        int[][] strg= new int[mat.length][mat[0].length];
        for (int i = strg.length-1; i >=0 ; i--) {
            for (int j = strg[0].length-1; j >=0 ; j--) {
                if(i==strg.length-1 &&j == strg[0].length-1){
                    strg[i][j]=mat[i][j];
                }else if(j == strg[0].length-1){
                    strg[i][j]=mat[i][j];
                }else if(i == strg.length-1){
                    strg[i][j]=mat[i][j];
                }else{
                    if(mat[i][j]==0){
                        strg[i][j]=0;
                    }else{
                        strg[i][j]=1+Math.min(strg[i][j+1],Math.min(strg[i+1][j], strg[i+1][j+1]));
                    }
                }
                max=Math.max(max, strg[i][j]);
            }
        }
        System.out.println(max);
    }

    public static void largestRectangleOf1s(){
        //LAH
    }

    public static void main(String[] args) {
        // addRemRep();
        // countEncoding();
        // matrixChainMult();
        // greedyCoinPick();
        // optimalBST();
        // catalanNo();
        // maxsumNoadjacent();
        // jobSequence();
        // fractionalKS();
        // largestSquare();
        // largestRectangleOf1s();
    }
}