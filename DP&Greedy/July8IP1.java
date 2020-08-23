import java.util.*;

public class July8IP1 {

    public static void arithmaticSlice() {// atleast 3 values
        int[] arr = { 2, 4, 6, 8, 10, -1, 3, 7, 11, 15, 0, 1, 2, 3 };
        int[] strg = new int[arr.length];
        for (int i = 2; i < arr.length; i++) {
            if ((arr[i] - arr[i - 1]) == (arr[i - 1] - arr[i - 2])) {
                strg[i] = strg[i - 1] + 1;
            }
        }
        for (int i : strg) {
            System.out.print(i + " ");
        }
    }

    public static void arithmaticSliceSubSeq() {// atleast 3 values
        int[] arr = { 2, 4, 6, 8, 10, -1, 3, 7, 11, 15, 0, 1, 2, 3 };
        HashMap<Integer, Integer>[] map = new HashMap[arr.length];
        for (int i = 0; i < arr.length; i++) {
            map[i] = new HashMap<>();
        }
        int count = 0;
        for (int i = 1; i < map.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (map[j].containsKey(arr[i] - arr[j])) {
                    count += map[j].get(arr[i] - arr[j]);
                    map[i].put(arr[i] - arr[j],
                            map[i].containsKey(arr[i] - arr[j])
                                    ? map[i].get(arr[i] - arr[j]) + map[j].get(arr[i] - arr[j])
                                    : 1);
                } else {
                    map[i].put(arr[i] - arr[j], 1);
                }
            }
        }
        System.out.println(count);
    }

    public static boolean helper(String queStr, String str) {
        int i = 0;
        int j = 0;
        while (true) {
            if (j == str.length()) {
                return true;
            }
            if (i == queStr.length()) {
                return false;
            }

            if (queStr.charAt(i) == str.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
    }

    public static void scrambledString() {
        String org = "great";
        String queStr = "aetrg";
        String[][] strg = new String[org.length()][org.length()];

        for (int g = 0; g < strg.length; g++) {
            for (int i = 0; i < strg.length - g; i++) {
                int j = i + g;
                if (g == 0) {
                    strg[i][j] = Character.toString(org.charAt(i));
                } else {
                    String temp = ".";
                    for (int k = 0; k < g; k++) {
                        String rU = strg[i][i + k];
                        String lU = strg[i + 1 + k][j];
                        System.out.println(rU + lU);
                        System.out.println(lU + rU);
                        // cond
                        if (helper(rU + lU, queStr)) {
                            temp = rU + lU;
                        }
                        if (helper(lU + rU, queStr)) {
                            temp = lU + rU;
                        }
                    }
                    strg[i][j] = temp;
                }
            }
        }
        for (int i = 0; i < strg.length; i++) {
            for (int j = 0; j < strg[0].length; j++) {
                System.out.print(strg[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void cherryPick() {
        // two paths till dest add both
    }

    public static void rodcut() {
        int[] arr = { 0, 3, 5, 6, 15, 10, 25, 12, 24 };
        int[] strg = new int[arr.length];
        for (int i = 0; i < strg.length; i++) {
            if (i == 0 || i == 1) {
                strg[i] = arr[i];
            } else {
                int max = 0;
                int j = 1;
                int k = i - 1;
                while (j <= k) {
                    max = Math.max(max, strg[j] + strg[k]);
                    j++;
                    k--;
                }
                strg[i] = Math.max(max, arr[i]);
            }
        }
        // for(int i:strg){
        // System.out.print(i+" ");
        // }
        System.out.println(strg[strg.length - 1]);
    }

    public static void russianDollEnvelop() {

    }

    public static void bridges() {

    }

    public static void activitySelection() {

    }

    public static void digitComparision() {
        // int n = 21457;
        int n=578461;
        String str = Integer.toString(n);
        int ans = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            int f = str.charAt(i) - '0';
            int s = str.charAt(i + 1) - '0';
            ans += (Math.abs(f - s) * (Math.pow(10, (str.length() - 1) - i)));
        }
        ans += str.charAt(str.length() - 1) - '0';
        System.out.println(ans);
    }

    public static void main(String[] args) {
        // arithmaticSlice();
        // arithmaticSliceSubSeq();
        // scrambledString();
        // cherryPick();
        // rodcut();
        // russianDollEnvelop();
        // bridges();
        // activitySelection();
        // digitComparision();
    }
}