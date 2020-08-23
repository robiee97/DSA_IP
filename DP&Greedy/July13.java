import java.util.ArrayList;

import javafx.util.Pair;

public class July13 {

    public static void maxSumAltSubSeq() {

    }

    public static void highWayBillBoard() { // LIS technique
        int[] Km = { 6, 7, 12, 13, 14 };
        int[] cost = { 5, 6, 5, 3, 1 };
        int M = 5;
        int[] strg = new int[Km.length];
        int omax = Integer.MIN_VALUE;
        for (int i = 0; i < strg.length; i++) {
            if (i == 0) {
                strg[i] = cost[i];
            } else {
                int max = cost[i];
                for (int j = i - 1; j >= 0; j--) {
                    if (Km[i] - M > Km[j]) {
                        max = Math.max(max, cost[i] + strg[j]);
                    }
                }
                strg[i] = max;
                omax = Math.max(omax, max);
            }
        }
        System.out.println(omax);
    }

    public static void highWayBillBoard2() {
        int[] Km = { 6, 7, 12, 13, 14 };
        int[] cost = { 5, 6, 5, 3, 1 };
        int M = 5;
        int[] strg = new int[15];
        int p = 0;
        strg[0] = 0;
        for (int i = 1; i < strg.length; i++) {
            if (i == Km[p]) {
                strg[i] = Math.max(strg[i - 1], strg[Km[p] - M - 1] + cost[p]);
                p++;
            } else {
                strg[i] = strg[i - 1];
            }
        }
        System.out.println(strg[strg.length - 1]);
    }

    public static void templeOfRings() {
        int[] arr = { 1, 4, 3, 6, 2, 1 };
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                left[i] = 1;
            } else {
                if (arr[i] > arr[i - 1]) {
                    left[i] = left[i - 1] + 1;
                } else {
                    left[i] = 1;
                }
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i == arr.length - 1) {
                right[i] = 1;
            } else {
                if (arr[i] > arr[i + 1]) {
                    right[i] = right[i + 1] + 1;
                } else {
                    right[i] = 1;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(Math.max(left[i], right[i]) + " ");
        }
    }

    public static void nTiles() {

    }

    public class PairClass{
        int x;
        int y;
        public PairClass(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    public static void probabilityOfKnight() {
    //     int steps=5;
    //     int x=2;
    //     int y=2;
    //     // ArrayList<PairClass>[][] possibleMovesMatrix= 
    //     // {{new ArrayList<>(new PairClass(2, 1),new PairClass(1, 2)),new ArrayList<>(new PairClass(2, 0),new PairClass(2, 2),new PairClass(1, 3)),new ArrayList<>(new PairClass(2, 3),new PairClass(2, 1),new PairClass(1, 0)),new ArrayList<>(new PairClass(2, 2),new PairClass(1, 1))},
    //     //  {new ArrayList<>(new PairClass(0, 2),new PairClass(2, 2),new PairClass(3, 1)),new ArrayList<>(new PairClass(3, 0),new PairClass(3, 2),new PairClass(0, 3),new PairClass(2, 3)),new ArrayList<>(new PairClass(2, 3),new PairClass(2, 1),new PairClass(1, 0)),new ArrayList<>(new PairClass(2, 2),new PairClass(1, 1))},
    //     //  {new ArrayList<>(new PairClass(x, y)),new ArrayList<>(new PairClass(x, y)),new ArrayList<>(new PairClass(x, y)),new ArrayList<>(new PairClass(x, y))},
    //     //  {new ArrayList<>(new PairClass(x, y)),new ArrayList<>(new PairClass(x, y)),new ArrayList<>(new PairClass(x, y)),new ArrayList<>(new PairClass(x, y))}};
    //     int[][][] strg= new int[steps][4][4];
    //     for (int i = 0; i < steps; i++) {
    //         if(i==0){
    //             for (int j = 0; j < strg[i].length; j++) {
    //                 for (int j2 = 0; j2 < strg[i][0].length; j2++) {
    //                     strg[i][j][j2]=1;
    //                 }
    //             }
    //         }
    //         else{
    //             for (int j = 0; j < strg.length; j++) {
    //                 for (int j2 = 0; j2 < strg.length; j2++) {
    //                     for (PairClass pc : possibleMovesMatrix[j][j2]) {
    //                         strg[i][j][j2]+=strg[i-1][pc.x][pc.y]/8;
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     System.out.println(strg[steps-1][x][y]);
    }


    public static int myans=0;
    public static void booleanParanthesis() {
        String exp="TTFT";
        String op="|&^";
        countBooleanParanthesis(exp,op);
        System.out.println(myans);
    }
    public static class Box{
        int tc;
        int fc;
        int ac;
        public Box(){}
        public Box(int tc,int fc,int ac){
            this.tc=tc;
            this.fc=fc;
            this.ac=ac;
        }
    }
    public static Box countBooleanParanthesis(String exp,String op){
        if(exp.length()==1){
            if(exp.charAt(0)=='T'){
                return new Box(1, 0, 1);
            }else{
                return new Box(0, 1, 1);
            }
        }
        for (int i = 0; i < op.length(); i++) {
            Box lans=countBooleanParanthesis(exp.substring(0,i+1), op.substring(0,i+1));
            Box rans=countBooleanParanthesis(exp.substring(i+1), op.substring(i+1));
            if(op.charAt(i)=='|'){
                myans+=(lans.ac*rans.ac)-(lans.fc*rans.fc);
            }else if(op.charAt(i)=='&'){
                myans+=(lans.tc*rans.tc);
            }else{
                myans+=(lans.tc*rans.fc)-(lans.fc*rans.tc);
            }
        }
        return new Box();
    }

    public static void lonValidSSLen() {

    }

    public static void wordWrap() {
        String str = "geeks for geeks presents word wrap problem";
        int m = 15;
        // String str="aaa bb cc ddddd";
        // int m=6;
        String[] sarr = str.split(" ");
        int[] arr = new int[sarr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sarr[i].length();
        }
        int[] ssum = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {// suffix sum array
            if (i == arr.length - 1) {
                ssum[i] = arr[i];
            } else {
                ssum[i] = arr[i] + ssum[i + 1];
            }
        }
        int[] strg = new int[arr.length];
        for (int i = strg.length - 1; i >= 0; i--) {
            if ((m - ssum[i]) - (strg.length - i - 1) >= 0) {
                strg[i] = 0;
            } else {
                int min = Integer.MAX_VALUE;
                for (int j = i; j < strg.length - 1; j++) {
                    if (m - (ssum[i] - ssum[j + 1]) - (j - i) >= 0) {
                        min = Math.min(min, (int) (Math.pow((m - (ssum[i] - ssum[j + 1]) - (j - i)), 3) + strg[j + 1]));
                    }
                }
                strg[i] = min;
            }
        }
        System.out.println(strg[0]);
    }

    public static void main(String[] args) {
        // highWayBillBoard();
        // highWayBillBoard2();
        // templeOfRings();
        // wordWrap();
        // probabilityOfKnight();
        // booleanParanthesis();
    }
}