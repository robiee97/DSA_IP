import java.util.*;

public class July2 {
    public static void main(String[] args) {
        // flipStrToMonotonic();
        // globalNLocalInv();
        // jumpGame();
        // majorityElement();
        // maxConsecOnes();
        // nextGreaterEleInDigits();
        // kadanes();
        // kkadanes();
        // subArrWithTarSum();
        // eqiliIndex();
        // diffOfPair();
        // maxSubArrOfPosSum();
    }

    public static void flipStrToMonotonic() {
        String str = "01101010011000";

        int[] a = new int[str.length()];// {0...1...}
        int[] b = new int[str.length()];// {0...}
        a[1] = 0;
        b[1] = 1;
        int i = 2;
        while (i < str.length()) {
            if (str.charAt(i) == '1') {
                a[i] = Math.min(a[i - 1], b[i - 1]);
                b[i] = b[i - 1] + 1;
            } else {
                a[i] = Math.min(a[i - 1], b[i - 1]) + 1;
                b[i] = b[i - 1];
            }
            i++;
        }
        System.out.println(a[a.length - 1] + " for 0s..1s.. \nfor 0s... " + b[b.length - 1]);
    }

    public static void globalNLocalInv() {
        int[] arr = { 6, 8, 12, 14, 18, 9 };
        int maxi = 0;
        int maxv = 0;
        int mini = arr.length - 1;
        int minv = 10000;
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            if (maxv < arr[i]) {
                maxv = arr[i];
                maxi = i;
            }
            if (i - maxi > 1) {
                c++;
                break;
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (minv > arr[i]) {
                minv = arr[i];
                mini = i;
            }
            if (mini - i > 1) {
                c++;
                break;
            }
        }
        if (c != 0) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }

    public static void jumpGame() {// logn min step ques of DP
        int[] arr = { 4, 4, 5, 2, 1, 4, 2, 0, 2, 3, 3, 3, 1, 2, 1, 0 };
        int i = 0;
        boolean f = true;
        int c = 1;
        while (true) {
            int range = arr[i] + i;
            int max = 0;
            for (int j = i + 1; j <= range; j++) {
                if (arr[j] + j >= arr.length - 1) {
                    f = !f;
                    break;
                }
                if (max < arr[j] + j) {
                    max = arr[j] + j;
                }
            }
            c++;
            if (!f) {
                System.out.println(c);
                break;
            }
            i = range;
            range = max;
        }
    }

    public static void majorityElement() {
        int[] arr = { 2, 5, 2, 6, 2, 2, 3, 2, 4, 2, 2, 6, 2, 7, 8, 2, 2, 9, 2 };
        int ele = arr[0];
        int c = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != ele) {
                if (c > 1) {
                    c--;
                } else {
                    ele = arr[i];
                    c = 1;
                }
            } else {
                c++;
            }
        }
        int ctr = 0;
        for (int i : arr) {
            if (i == ele) {
                ctr++;
            }
        }
        if (ctr >= arr.length / 2 + 1) {
            System.out.println("majority element is " + ele + " with freq : " + ctr);
        } else {
            System.out.println("Not found any");
        }
    }

    public static void maxConsecOnes() {// can flip k zeros
        int[] arr = { 1, 0, 0, 0, 1, 1, 0, 1, 0 };
        int s = 0;
        int e = 0;
        int k = 3;
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (e < arr.length) {
            // settle till valid
            if (map.containsKey(0) && map.get(0) > k) {
                // Release
                if (map.get(arr[s]) > 1) {
                    map.put(arr[s], map.get(arr[s]) - 1);
                    s++;
                } else {
                    map.remove(arr[s]);
                    s++;
                }
            } else {
                while (true) {
                    // invalid
                    if (map.containsKey(0) && map.get(0) > k) {
                        break;
                    }
                    // aquire
                    map.put(arr[e], map.containsKey(arr[e]) ? map.get(arr[e]) + 1 : 1);
                    e++;
                    len = Math.max(len, e - s - 1);
                }
            }
        }
        System.out.println(len);
    }

    public static String nextEleStr(char o, String str) {
        String ans = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) > o) {
                ans += str.charAt(i);
                for (int j = str.length() - 1; j > i; j--) {
                    ans += str.charAt(j);
                }
                ans += o;
                for (int j = i; j >= 1; j--) {
                    ans += str.charAt(j);
                }
                return ans;
            }
        }
        return "";
    }

    public static void nextGreaterEleInDigits() {
        String n = "23594865321";
        int i = n.length() - 1;
        // right to left first dip~inc to dec (not allowed)
        while (i >= 0 && (n.charAt(i - 1) - '0') > (n.charAt(i) - '0')) {
            i--;
        }
        // replace this dip element with its ceil to the right
        // reverse from dip[i+1] ...till last index
        String ans = nextEleStr(n.charAt(i - 1), n.substring(i - 1));
        System.out.println(n.substring(0, i) + ans);
    }

    public static void maxSubArrOfPosSum() {

    }

    public static int kadanes(int[] arr) {
        // int[] arr={4,2,-3,-4,6,1,7,-12,-1,4,15,-4,2};
        int cmax = 0;
        int omax = 0;
        for (int i = 0; i < arr.length; i++) {
            cmax = cmax > 0 ? cmax + arr[i] : arr[i];
            omax = cmax > omax ? cmax : omax;
        }
        // System.out.println(omax);
        return omax;
    }

    public static void kkadanes() {
        int[] arr = { 4, 2, -3, -4, 6, 1, 7, -12, -1, 4, 15, -4, 2 };
        int k = 5;
        int max = 0;
        if (kadanes(arr) > 0) {
            max = kadanes(arr) * (k - 2);
        }
        int[] narr = new int[arr.length * 2];
        for (int i = 0; i < narr.length; i++) {
            if (i < narr.length / 2) {
                narr[i] = arr[i];
            } else {
                narr[i] = arr[i - arr.length];
            }
        }
        System.out.println(max + kadanes(narr));
    }

    public static void subArrWithTarSum() {// without space
        int[] arr = { 2, 8, 2, 5, 9, 5, 6 };
        int tar = 12;
        int s = 0;
        int e = 0;
        int sum = 0;
        while (e < arr.length) {
            if (sum == tar) {
                System.out.println(sum + " -> " + s + "," + e);
                break;
            }
            if (sum > tar) {// invalid
                // release
                while (s < arr.length && sum > tar) {
                    sum -= arr[s];
                    s++;
                }
            } else {
                while (e < arr.length && sum < tar) {// till valid
                    // aquire
                    sum += arr[e];
                    e++;
                }
            }
        }
    }

    public static void eqiliIndex() {
        int[] arr = { 2, 6, 7, 9, 4, 8, 3 };
        int rsum = 0;
        int lsum = 0;
        for (int i : arr) {
            rsum += i;
        }
        for (int i = 0; i < arr.length; i++) {
            if (rsum == lsum) {
                System.out.println("equilibrium at " + i);
                return;
            }
            rsum -= arr[i];
            if (i != 0) {
                lsum += arr[i - 1];
            }
        }
        System.out.println("Not found");
    }

    public static void diffOfPair() {
        int[] arr = { 2, 7, 3, 5, 1, 8, 4, 9 };
        int tar = 6;
        Arrays.sort(arr);
        int i = 0;
        int j = 1;
        while (arr[i] + arr[j] != tar) {
            if (arr[i] + arr[j] < tar) {
                j++;
            } else {
                i++;
            }
        }
        System.out.println("arr{" + i + "," + j + "} = " + tar);
    }
}