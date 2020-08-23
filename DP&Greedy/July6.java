import java.util.*;

public class July6 {
    public static void main(String[] args) {
        // longestIncSubq();
        // longestDecSubq();
        // longestBitonicSubq();
        // maxSumIncSubq();
        // longestIncSubArr();
        // maxDeletionToSortArr();
        // longestCommonSubseq();
        // longestPallSubseq();
        // countPallSubseq();
        // longestPallSubstr();
        // countPallSubstr();
        // minPalCut();
        // eggDrop();
        // primeNSemiPrime();
    }

    public static int[] longestIncSubq() {
        int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
        int[] strg = new int[arr.length];
        strg[0] = 1;
        for (int i = 1; i < strg.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    max = Math.max(max, strg[j]);
                }
            }
            strg[i] = max + 1;
        }

        return strg;
        // int longestSeq=strg[0];
        // for(int i:strg){
        // longestSeq= Math.max(longestSeq, i);
        // }
        // System.out.println(longestSeq);
    }

    public static int[] longestDecSubq() {
        int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
        int[] strg = new int[arr.length];
        strg[arr.length - 1] = 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < strg.length; j++) {
                if (arr[j] < arr[i]) {
                    max = Math.max(max, strg[j]);
                }
            }
            strg[i] = max + 1;
        }
        return strg;
        // int longestSeq=strg[0];
        // for(int i:strg){
        // longestSeq= Math.max(longestSeq, i);
        // }
        // System.out.print(longestSeq);
    }

    public static void longestBitonicSubq() {
        int[] inc = longestIncSubq();
        int[] dec = longestDecSubq();
        int max = 0;
        for (int i = 0; i < inc.length; i++) {
            max = Math.max(max, (inc[i] + dec[i] - 1));
        }
        System.out.println(max);
    }

    public static void maxSumIncSubq() {
        int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
        int[] strg = new int[arr.length];
        strg[0] = arr[0];
        for (int i = 1; i < strg.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    max = Math.max(max, strg[j]);
                }
            }
            strg[i] = max + arr[i];
        }
    }

    public static void longestIncSubArr() {
        int[] arr = { 5, 6, 42, 3, 1, 93, 8, 5, 4, 2, 9, 4, 0, 43, 3 };
        int[] strg = new int[arr.length];
        strg[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                strg[i] = strg[i - 1] + 1;
            } else {
                strg[i] = 1;
            }
        }
        int max = 0;
        for (int i : strg) {
            // System.out.print(i+" ");
            max = Math.max(max, i);
        }
        System.out.println(max);
    }

    public static void maxDeletionToSortArr() {
        int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
        int[] inc = longestIncSubq();
        int lis = 0;
        for (int i : inc) {
            lis = Math.max(lis, i);
        }
        System.out.println(arr.length - lis);
    }

    public static void longestCommonSubseq() {
        String s1 = "abed";
        String s2 = "abcd";

        int[][] strg = new int[s1.length() + 1][s2.length() + 1];
        for (int i = strg.length - 1; i >= 0; i--) {
            for (int j = strg.length - 1; j >= 0; j--) {
                if (i == strg.length - 1 || j == strg[0].length - 1) {
                    strg[i][j] = 0;
                } else {
                    if (s1.charAt(i) == s2.charAt(j)) {
                        strg[i][j] = 1 + strg[i + 1][j + 1];
                    } else {
                        strg[i][j] = Math.max(strg[i][j + 1], strg[i + 1][j]);
                    }
                }
            }
        }
        System.out.println(strg[0][0]);
    }

    public static void longestPallSubseq() {
        String str = "abckycbc";
        int[][] strg = new int[str.length()][str.length()];
        for (int g = 0; g < strg.length; g++) {
            for (int i = 0; i < strg.length - g; i++) {
                int j = i + g;
                if (g == 0) {
                    strg[i][j] = 1;
                } else if (g == 1) {
                    strg[i][j] = str.charAt(i) == str.charAt(j) ? 2 : 1;
                } else {
                    if (str.charAt(i) == str.charAt(j)) {
                        strg[i][j] = 2 + strg[i + 1][j - 1];
                    } else {
                        strg[i][j] = Math.max(strg[i + 1][j], strg[i][j - 1]);
                    }
                }
            }
        }
        System.out.println(strg[0][strg.length - 1]);
    }

    public static void countPallSubseq() {
        String str = "abckycbc";
        int[][] strg = new int[str.length()][str.length()];
        for (int g = 0; g < strg.length; g++) {
            for (int i = 0; i < strg.length - g; i++) {
                int j = i + g;
                if (g == 0) {
                    strg[i][j] = 1;
                } else if (g == 1) {
                    strg[i][j] = str.charAt(i) == str.charAt(j) ? 3 : 2;
                } else {
                    if (str.charAt(i) == str.charAt(j)) {
                        strg[i][j] = strg[i + 1][j] + strg[i][j - 1] + 1;
                    } else {
                        strg[i][j] = strg[i + 1][j] + strg[i][j - 1] - strg[i + 1][j - 1];
                    }
                }
            }
        }
        System.out.println(strg[0][strg.length - 1]);
    }

    public static void longestPallSubstr() {
        String str = "abccbc";
        int longest = 0;
        boolean[][] strg = new boolean[str.length()][str.length()];
        for (int g = 0; g < strg.length; g++) {
            for (int i = 0; i < strg.length - g; i++) {
                int j = i + g;
                if (g == 0) {
                    strg[i][j] = true;
                } else if (g == 1) {
                    strg[i][j] = str.charAt(i) == str.charAt(j);
                } else {
                    strg[i][j] = str.charAt(i) == str.charAt(j) && strg[i + 1][j - 1];
                }
                if (strg[i][j]) {
                    longest = g + 1;
                }
            }
        }
        System.out.println(longest);
    }

    public static void countPallSubstr() {
        String str = "abccbc";
        int count = 0;
        boolean[][] strg = new boolean[str.length()][str.length()];
        for (int g = 0; g < strg.length; g++) {
            for (int i = 0; i < strg.length - g; i++) {
                int j = i + g;
                if (g == 0) {
                    strg[i][j] = true;
                } else if (g == 1) {
                    strg[i][j] = str.charAt(i) == str.charAt(j);
                } else {
                    strg[i][j] = str.charAt(i) == str.charAt(j) && strg[i + 1][j - 1];
                }
                if (strg[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void minPalCut() {
        String str = "abccbc";
        int[][] strg = new int[str.length()][str.length()];
        for (int g = 0; g < strg.length; g++) {
            for (int i = 0; i < strg.length - g; i++) {
                int j = i + g;
                if (g == 0) {
                    strg[i][j] = 0;
                } else if (g == 1) {
                    strg[i][j] = str.charAt(i) == str.charAt(j) ? 0 : 1;
                } else {
                    if (str.charAt(i) == str.charAt(j) && strg[i + 1][j - 1] == 0) {
                        strg[i][j] = 0;
                    } else {
                        int min = str.length();
                        for (int k = 0; k < g; k++) {
                            int left = strg[i][i + k];
                            int right = strg[i + 1 + k][j];
                            min = Math.min(min, left + right);
                        }
                        strg[i][j] = min + 1;
                    }
                }
            }
        }
        System.out.println(strg[0][strg.length - 1]);
    }

    public static void eggDrop() {
        int eggs = 2;
        int floors = 10;
        int[][] strg = new int[eggs + 1][floors + 1];
        for (int e = 1; e <= eggs; e++) {
            for (int f = 0; f <= floors; f++) {
                if (e == 1 || f == 1 || f == 0) {
                    strg[e][f] = f;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = 1; k <= f; k++) {
                        int maieb = strg[e - 1][k - 1];
                        int maies = strg[e][f - k];
                        int worstCase = Math.max(maies, maieb);
                        min = Math.min(min, worstCase);
                    }
                    strg[e][f] = min + 1;
                }
            }
        }
        System.out.println(strg[eggs][floors]);
    }

    public static void primeNSemiPrime() {
        for (int i = 2; i <= 100; i++) {
            if (cprime(i)) {
                System.out.println(i + " prime");
            }
        }
    }

    public static boolean cprime(long n) {
        if (n == 2 || n == 3) {
            return true;
        }
        if ((double) (((double) (n + 1) / 6) - (int) ((double) (n + 1) / 6)) == 0
                || (double) (((double) (n - 1) / 6) - (int) ((double) (n - 1) / 6)) == 0) {
            return true;
        }
        return false;
    }

}