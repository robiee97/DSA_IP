public class July9IP1 {

    public static void paintHouse() {
        int n = 4; // no of houses
        int c = 3; // no of paints
        int[][] mat = { { 1, 5, 3, 1 }, { 5, 8, 2, 2 }, { 7, 4, 9, 4 } };
        int[][] strg = new int[c][n];
        for (int j = 0; j < strg[0].length; j++) {
            for (int i = 0; i < strg.length; i++) {
                if (j == 0) {
                    strg[i][j] = mat[i][j];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = 0; k < strg.length; k++) {
                        if (k == i) {
                            continue;
                        } else {
                            min = Math.min(min, strg[k][j - 1]);
                        }
                    }
                    strg[i][j] = min + mat[i][j];
                }
            }
        }
        int min = strg[0][strg[0].length - 1];
        int idx = 0;
        for (int i = 0; i < strg.length; i++) {
            if (strg[i][strg[0].length - 1] < min) {
                min = strg[i][strg[0].length - 1];
                idx = i;
            }
        }
        System.out.println("min cost " + min + "\npath is");
        pathPaintHouse(strg, mat, idx, strg[0].length - 1, "");
    }

    public static void pathPaintHouse(int[][] strg, int[][] mat, int i, int j, String asf) {
        if (j == 0) {
            System.out.println(mat[i][j] + asf);
            return;
        }
        asf = mat[i][j] + asf;
        int next = strg[i][j] - mat[i][j];
        for (int itr = 0; itr < strg.length; itr++) {
            if (strg[itr][j - 1] == next) {
                pathPaintHouse(strg, mat, itr, j - 1, asf);
            }
        }
    }

    public static void paintFence() {
        int n = 8;
        int k = 3;
        int[][] strg = new int[2][n + 1]; // 2 rows one is same and another is diff

        strg[0][1] = 0;
        strg[1][1] = k;
        for (int j = 2; j < strg[0].length; j++) {
            for (int i = 0; i < strg.length; i++) {
                if (i == 0) {
                    strg[i][j] = strg[i + 1][j - 1];
                } else {
                    strg[i][j] = 2 * (strg[i][j - 1] + strg[i - 1][j - 1]);
                }
            }
        }
        System.out.println(strg[0][n] + strg[1][n]);
    }

    public static void buildingSpacebuilding() {

    }

    public static void countEncoding() {
        int[] arr = { 2, 5, 1, 2, 1, 6, 3, 2, 1, 1, 0, 2, 2, 6, 2, 0, 1, 4 };
        int[] strg = new int[arr.length];
        strg[0] = 1;
        for (int i = 1; i < strg.length; i++) {
            int fact = arr[i - 1] * 10 + arr[i];
            if (fact > 9 && fact <= 26) {
                if (i == 1) {
                    strg[i] += 1;
                } else {
                    strg[i] += strg[i - 2];
                }
            }
            if (arr[i] != 0) {
                strg[i] += strg[i - 1];
            }
        }
        System.out.println(strg[strg.length - 1]);
    }

    public static void bsbINFTFees() {

        int[] arr = { 9, 1, 3, 10, 1, 4, 6, 9 };
        int fees = 2;
        int[][] strg = new int[2][arr.length]; // 0th row buy 1st row sell --fees 2
        strg[0][0] = -1 * arr[0];
        strg[1][0] = 0;
        for (int j = 1; j < strg[0].length; j++) {
            for (int i = 0; i < strg.length; i++) {
                if (i == 0) { // buying moment
                    strg[i][j] = Math.max(strg[i][j - 1], strg[i + 1][j - 1] - arr[j]);
                } else { // selling moment
                    strg[i][j] = Math.max(strg[i][j - 1], strg[i - 1][j - 1] + arr[j] - fees);
                }
            }
        }
        System.out.println(strg[1][strg[0].length - 1]);
    }

    public static void bsbINFTCoolDown() {

    }

    public static void bsb1T() {
        int[] arr = { 9, 2, 3, 7, 8, 3, 10, 11 };
        int msf = Integer.MAX_VALUE;
        int[] strg = new int[arr.length];
        strg[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            msf = Math.min(msf, arr[i]);
            strg[i] = Math.max(strg[i - 1], arr[i] - msf);
        }
        int max = 0;
        for (int i : strg) {
            max = Math.max(max, i);
        }
        System.out.println(max);
    }

    public static void bsb2T() {
        int[] arr = { 9, 2, 3, 7, 8, 3, 10, 11 };
        int misf = arr[0];
        int[] strg1 = new int[arr.length];
        strg1[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            misf = Math.min(misf, arr[i]);
            strg1[i] = Math.max(strg1[i - 1], arr[i] - misf);
        }
        int masf = arr[arr.length - 1];
        int[] strg2 = new int[arr.length];
        strg2[arr.length - 1] = 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            masf = Math.max(masf, arr[i]);
            strg2[i] = Math.max(strg2[i + 1], masf - arr[i]);
        }
        int max = 0;
        for (int i = 0; i < strg1.length - 1; i++) {
            max = Math.max(max, strg1[i] + strg2[i]);
        }
        System.out.println(max);
    }

    public static void bsbKT() {
        int[] arr = { 9, 2, 3, 7, 8, 3, 10, 11 };
        int k = 3;
        int[][] strg = new int[k][arr.length - 1];
        int max = -1 * (arr[0]);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (j == 0) {
                    strg[i][j] = 0;
                } else if (i == 0) {
                    max = Math.max(max, 0 - arr[j]);
                    strg[i][j] = Math.max(strg[i][j - 1], arr[j] + max);
                } else {
                    strg[i][j] = Math.max(strg[i][j - 1], (strg[i - 1][j - 1] + arr[j]));
                }
            }
        }

        for (int[] i : strg) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        // System.out.println(strg[strg.length-1][strg[0].length-1]);
    }

    public static String de(String s) {
        String ans = "";
        int i = 0;
        int j = 0;
        while (Character.isDigit(s.charAt(i))) {
            i++;
        }
        i--;
        if (i + 2 == s.length()) {
            return Character.toString(s.charAt(0));
        }
        j = i+1;
        while (j+2 < s.length()) {
            String trip = s.substring(j, j + 3);
            if (Character.isUpperCase(trip.charAt(0)) && Character.isLowerCase(trip.charAt(1))
                    && trip.charAt(2) == '*') {
                ans += Character.toString(trip.charAt(1)) + Character.toString(trip.charAt(0));
                j += 3;
            } else if (s.charAt(j) == '0') {
                if (Character.isDigit(s.charAt(i))) {
                    ans += Character.toString(s.charAt(i));
                    i--;
                    j++;
                }
            } else {
                ans += Character.toString(s.charAt(j));
                j++;
            }
        }
        while(j<s.length()){
            if(Character.isDigit(s.charAt(j))){
                ans += Character.toString(s.charAt(i));
                i--;
                j++;
            }else{
                ans+=Character.toString(s.charAt(j));
                j++;
            }
        }
        // System.out.println(j);
        System.out.println(ans);

        return ans;

    }

    public static void main(String[] args) {
        // paintHouse();
        // paintFence();
        // countEncoding();
        // bsbINFTFees();
        // bsb1T();
        // bsb2T();
        // bsbKT();
        // System.out.println(de("1Bl*Kg*u0"));
    }
}