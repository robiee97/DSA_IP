
public class July5 {
    // group of factorisation &seive
    public static void miniMovesToPrintNxs() {
        int n = 21;
        int total = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                total += i;
                n /= i;
            }
        }
        System.out.println(total);
    }

    public static void seiveVariation() {
        int n = 81;
        int[] s = new int[n + 1];
        int[] f = new int[n + 1];
        for (int i = 0; i < s.length; i++) {
            s[i] = i;
        }
        for (int i = 2; i * i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (s[j] % i == 0) {
                    s[j] = i;
                }
            }
        }

        for (int i = 2; i * i <= n; i++) {
            while (n != 1) {
                f[s[n]]++;
                n /= s[n];
            }
        }
        for (int i = 0; i < f.length; i++) {
            if (f[i] != 0) {
                System.out.println(i + " -> " + f[i]);
            }
        }
    }

    // group of steps, paths, mcp, goldmine
    public static void steps() {
        int[] arr = { 4, 2, 0, 0, 2, 4, 6, 3, 1, 0, 1, 2, 3, 1, 1 };
        int[] strg = new int[arr.length];
        strg[arr.length - 1] = 0;
        for (int i = strg.length - 2; i >= 0; i--) {
            int min = arr.length;
            for (int j = i + 1; j < arr.length && j <= i + arr[i]; j++) {
                min = Math.min(min, strg[j]);
            }
            strg[i] = min + 1;
        }
        System.out.println(strg[0] + " steps");
        printSteps(arr, strg, 0, "");
    }

    public static void printSteps(int[] arr, int[] strg, int i, String psf) {
        if (i == arr.length - 1) {
            System.out.println(psf);
            return;
        }
        psf += i + ",";
        int min = arr.length;
        for (int j = i + 1; j < arr.length && j <= i + arr[i]; j++) {
            min = Math.min(min, strg[j]);
        }
        for (int j = i + 1; j < arr.length && j <= i + arr[i]; j++) {
            if (strg[j] == min) {
                printSteps(arr, strg, j, psf);
            }
        }
    }

    public static void paths() {
        int[] arr = { 4, 2, 0, 0, 2, 4, 6, 3, 1, 0, 1, 2, 3, 1, 1 };
        int[] strg = new int[arr.length];
        strg[arr.length - 1] = 1;
        for (int i = strg.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < arr.length && j <= i + arr[i]; j++) {
                strg[i] += strg[j];
            }
        }
        System.out.println(strg[0] + " paths");
        printPaths(arr, strg, 0, "");
    }

    public static void printPaths(int[] arr, int[] strg, int i, String psf) {
        if (i == arr.length - 1) {
            System.out.println(psf);
            return;
        }
        psf += arr[i] + ",";
        for (int j = i + 1; j < arr.length && j <= i + arr[i]; j++) {
            printPaths(arr, strg, j, psf);
        }
    }

    public static void minCost() {
        int[][] lo = { { 2, 7, 3, 8, 0, 3, 7 }, { 4, 0, 1, 2, 4, 3, 4 }, { 3, 8, 5, 9, 0, 8, 1 },
                { 1, 6, 0, 4, 5, 5, 2 }, { 6, 2, 9, 5, 7, 0, 6 }, { 0, 8, 7, 9, 6, 3, 0 }, { 1, 3, 5, 0, 5, 1, 0 } };
        int[][] go = new int[lo.length][lo[0].length];
        for (int i = go.length - 1; i >= 0; i--) {
            for (int j = go[0].length - 1; j >= 0; j--) {
                if (i == go.length - 1 && j == go[0].length - 1) {
                    go[i][j] = lo[i][j];
                } else if (i == go.length - 1) {
                    go[i][j] = lo[i][j] + go[i][j + 1];
                } else if (j == go[0].length - 1) {
                    go[i][j] = lo[i][j] + go[i + 1][j];
                } else {
                    go[i][j] = lo[i][j] + Math.min(go[i + 1][j], go[i][j + 1]);
                }
            }
        }
        System.out.println(go[0][0]);
        printMCP(go, 0, 0, "");
    }

    public static void printMCP(int[][] go, int i, int j, String psf) {
        if (i == go.length - 1 && j == go[0].length - 1) {
            System.out.println(psf);
            return;
        }
        if (i == go.length - 1) {
            printMCP(go, i, j + 1, psf + "v");
        } else if (j == go[0].length - 1) {
            printMCP(go, i + 1, j, psf + "h");
        } else {
            if (go[i + 1][j] > go[i][j + 1]) {
                printMCP(go, i, j + 1, psf + "v");
            } else if (go[i + 1][j] < go[i][j + 1]) {
                printMCP(go, i + 1, j, psf + "h");
            } else {
                printMCP(go, i + 1, j, psf + "h");
                printMCP(go, i, j + 1, psf + "v");
            }
        }
    }

    public static void goldmine() {
        int[][] lo = { { 1, 5, 1, 0, 3 }, { 1, 4, 0, 2, 3 }, { 4, 0, 1, 3, 2 }, { 2, 4, 0, 4, 1 }, { 1, 2, 3, 2, 0 }, };
        int[][] go = new int[lo.length][lo[0].length];
        for (int j = lo[0].length - 1; j >= 0; j--) {
            for (int i = 0; i < lo.length; i++) {
                if (j == go[0].length - 1) {
                    go[i][j] = lo[i][j];
                } else if (i == 0) {
                    go[i][j] = lo[i][j] + Math.max(go[i + 1][j + 1], go[i][j + 1]);
                } else if (i == go.length - 1) {
                    go[i][j] = lo[i][j] + Math.max(go[i - 1][j + 1], go[i][j + 1]);
                } else {
                    go[i][j] = lo[i][j] + Math.max(go[i - 1][j + 1], Math.max(go[i + 1][j + 1], go[i][j + 1]));
                }
            }
        }
        int max = 0;
        int ind = 0;
        for (int i = 0; i < go.length; i++) {
            if (go[i][0] > max) {
                max = go[i][0];
                ind = i;
            }
        }
        System.out.println(max);
        goldMinePath(lo, go, ind, 0, "");
    }

    public static void goldMinePath(int[][] lo, int[][] go, int i, int j, String psf) {
        if (j == lo[0].length - 1) {
            System.out.println(psf);
            return;
        }
        psf += lo[i][j] + ",";
        if (i == 0) {
            if (go[i][j + 1] > go[i + 1][j + 1]) {
                goldMinePath(lo, go, i, j + 1, psf);
            } else if (go[i][j + 1] < go[i + 1][j + 1]) {
                goldMinePath(lo, go, i + 1, j + 1, psf);
            } else {
                goldMinePath(lo, go, i, j + 1, psf);
                goldMinePath(lo, go, i + 1, j + 1, psf);
            }
        } else if (i == go.length - 1) {
            if (go[i][j + 1] > go[i - 1][j + 1]) {
                goldMinePath(lo, go, i, j + 1, psf);
            } else if (go[i][j + 1] < go[i - 1][j + 1]) {
                goldMinePath(lo, go, i - 1, j + 1, psf);
            } else {
                goldMinePath(lo, go, i, j + 1, psf);
                goldMinePath(lo, go, i - 1, j + 1, psf);
            }
        } else {
            int max = 0;
            max = Math.max(go[i][j + 1], Math.max(go[i + 1][j + 1], go[i - 1][j + 1]));
            if (go[i][j + 1] == max) {
                goldMinePath(lo, go, i, j + 1, psf);
            }
            if (go[i + 1][j + 1] == max) {
                goldMinePath(lo, go, i + 1, j + 1, psf);
            }
            if (go[i - 1][j + 1] == max) {
                goldMinePath(lo, go, i - 1, j + 1, psf);
            }
        }
    }

    // group of tss,perm,comb,ks
    public static void targetSumSubset() {
        int[] arr = { 3, 2, 5, 1, 8 };
        int tar = 9;
        boolean[][] strg = new boolean[arr.length][tar + 1];
        for (int i = 0; i < strg.length; i++) {
            for (int j = 0; j < strg[0].length; j++) {
                if (i == 0) {
                    if (j == 0 || j == arr[0]) {
                        strg[i][j] = true;
                    }
                } else if (j == 0) {
                    strg[i][j] = true;
                } else {
                    if (strg[i - 1][j] == true) {
                        strg[i][j] = true;
                    } else if (j >= arr[i]) {
                        if (strg[i - 1][j - arr[i]] == true) {
                            strg[i][j] = true;
                        }
                    }
                }
            }
        }
        System.out.println(strg[strg.length - 1][strg[0].length - 1]);
        printTssPaths(strg, arr, strg.length - 1, strg[0].length - 1, "");
    }

    public static void printTssPaths(boolean[][] strg, int[] arr, int i, int j, String psf) {
        if (i == 0) {
            if (j == 0) {
                System.out.println(psf);
            } else if (j == arr[0]) {
                System.out.println(psf + arr[0]);
            }
            return;
        } else {
            if (strg[i - 1][j] == true) {
                printTssPaths(strg, arr, i - 1, j, psf);
            } else if (j >= arr[i]) {
                if (strg[i - 1][j - arr[i]] == true) {
                    printTssPaths(strg, arr, i - 1, j - arr[i], psf + arr[i] + " ");
                }
            }
        }
    }

    public static void coinChangePerm() {
        int[] arr = { 2, 3, 5, 6 };
        int tar = 7;
        int[] strg = new int[tar + 1];
        strg[0] = 1;
        for (int i = 1; i < strg.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i >= arr[j]) {
                    strg[i] += strg[i - arr[j]];
                }
            }
        }
        System.out.println(strg[strg.length - 1]);
    }

    public static void coinChangeComb() {
        int[] arr = { 2, 3, 5, 6 };
        int tar = 7;
        int[] strg = new int[tar + 1];
        strg[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < strg.length; j++) {
                if (j >= arr[i]) {
                    strg[j] += strg[j - arr[i]];
                }
            }
        }
        System.out.println(strg[strg.length - 1]);
    }

    public static void ZOKS() {// duplicates not allowed
        int[] wts = { 2, 5, 1, 3, 4 };
        int[] prices = { 15, 14, 10, 45, 30 };
        int cap = 7;
        int[][] strg = new int[wts.length][cap + 1];
        for (int i = 0; i < strg.length; i++) {
            for (int j = 0; j < strg[0].length; j++) {
                if (i == 0) {
                    if (j >= wts[0]) {
                        strg[i][j] = prices[0];
                    }
                } else {
                    strg[i][j] = strg[i - 1][j];
                    if (j >= wts[i]) {
                        int fact = strg[i - 1][j - wts[i]] + prices[i];
                        strg[i][j] = Math.max(strg[i][j], fact);
                    }
                }
            }
        }
        System.out.println(strg[strg.length - 1][strg[0].length - 1]);
    }

    public static void UBKS() {// duplicates allowed
        int[] wts = { 2, 5, 1, 3, 4 };
        int[] prices={ 15, 14, 10, 45, 30 };
        int cap = 7;
        int[] strg= new int[cap+1];
        strg[0]=0;
        for(int i=1;i<strg.length;i++){
            int max=0;
            for(int j=0;j<wts.length;j++){  
                if(i>=wts[j]){
                    max=Math.max(max, strg[i-wts[j]]+prices[j]);
                }
            }
            strg[i]=max;
        }
        System.out.println(strg[strg.length-1]);
    }

    public static void main(String[] args) {
        // miniMovesToPrintNxs();
        // seiveVariation();
        // steps();
        // paths();
        // minCost();
        // goldmine();
        // targetSumSubset();
        // coinChangePerm();
        // coinChangeComb();
        // ZOKS();
        // UBKS();
    }

}