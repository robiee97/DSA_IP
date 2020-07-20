import java.util.*;
public class June26 {
    public static void main(String[] args) {
        // revealCardsIncOrder();
        // abcShift();
        // System.out.println(longPressedName());
        // maxCutsToSortedArr();
        // boldTag();
        // longestConsecutiveOnes();
        // magicSquare();
        // System.out.println(intToEng(567412237, 0));
        // nextCloseTime();
        
    }
    
    public static void revealCardsIncOrder() {
        int[] arr = { 17, 13, 11, 2, 3, 5, 7 };
        Arrays.sort(arr);
        int[] sa = new int[arr.length];
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            q.addLast(i);
        }
        int i = 0;
        while (q.size() > 0) {
            sa[q.removeFirst()] = arr[i];
            i++;
            if (q.size() > 1) {
                q.addLast(q.removeFirst());
            } else {
                sa[q.removeFirst()] = arr[i];
            }
        }
        for (int j : sa) {
            System.out.print(j + " ");
        }
    }

    public static void abcShift() {
        char[] characters = { 'a', 'b', 'c' };
        int[] arr = { 3, 5, 9 };
        for (int i = arr.length - 2; i >= 0; i--) {// suffix sum arr
            arr[i] += arr[i + 1];
        }
        for (int i = 0; i < characters.length; i++) {
            System.out.print((char) (((characters[i] - 'a') + arr[i] % 26) + 'a') + " ");
        }
    }

    // 0 casese are not handled here
    public static String[] got = { "hundread", "thousands", "million", "billion", "trillion" };
    public static String[] teens = { " ", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
    public static String[] tens = { "", " ", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty",
            "ninety" };

    public static String intToEng2(int n, int i) {
        String str = "";
        if (n % 100 >= 20) {
            if (i == 0) {
                str += teens[n / 100] + " " + got[0] + " " + tens[((n % 100) - (n % 10)) / 10] + " " + teens[n % 10];
            } else {
                str += teens[n / 100] + " " + got[0] + " " + tens[((n % 100) - (n % 10)) / 10] + " " + teens[n % 10]
                        + " " + got[i];
            }
        } else {
            if (i == 0) {
                str += teens[n / 100] + " " + got[0] + " " + teens[n % 100];
            } else {
                str += teens[n / 100] + " " + got[0] + " " + teens[n % 100] + " " + got[i];
            }
        }
        return str;
    }

    public static String intToEng(int n, int i) {
        if (n == 0) {
            return "";
        }
        return intToEng(n / 1000, i + 1) + intToEng2(n % 1000, i) + " ";
    }

    public static boolean longPressedName() {
        String str1 = "sumeet";
        String str2 = "ssuuummmeeeetttt";
        int i = 0;
        int j = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                if (j > 0 && str2.charAt(j) == str2.charAt(j - 1)) {
                    j++;
                } else {
                    return false;
                }
            }
        }
        while (j < str2.length()) {
            if (j > 0 && str2.charAt(j) == str2.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean magicSquare() {
        int[][] mat = {};

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (i + 1 < mat.length - 1 && j + 1 < mat[i].length - 1 && mat[i + 1][j + 1] == 5) {
                    // check this square
                    if ((mat[i][j] + mat[i][j + 1] + mat[i][j + 2] == 15)
                            && (mat[i + 1][j] + mat[i + 1][j + 1] + mat[i + 1][j + 2] == 15)
                            && (mat[i + 2][j] + mat[i + 2][j + 1] + mat[i + 2][j + 2] == 15)
                            && (mat[i][j] + mat[i + 1][j] + mat[i + 2][j] == 15)
                            && (mat[i][j + 1] + mat[i + 1][j + 1] + mat[i + 2][j + 1] == 15)
                            && (mat[i][j + 2] + mat[i + 1][j + 2] + mat[i + 2][j + 2] == 15)
                            && (mat[i][j] + mat[i + 1][j + 1] + mat[i + 2][j + 2] == 15)
                            && (mat[i][j + 2] + mat[i + 1][j + 1] + mat[i + 2][j] == 15)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void maxCutsToSortedArr() {
        int[] arr = { 4, 2, 3, 1, 8, 6, 9, 7, 11, 10, 12 };
        int[] minsf = new int[arr.length];
        int[] maxsf = new int[arr.length];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int c = 0;
        for (int i = 0, j = arr.length - 1; i < arr.length && j >= 0; i++, j--) {
            if (i == 0 && j == arr.length - 1) {
                minsf[j] = arr[j];
                min = arr[j];
                maxsf[i] = Integer.MIN_VALUE;
                max = Integer.MIN_VALUE;
            } else {
                min = Math.min(min, arr[j]);
                minsf[j] = min;
                max = Math.max(max, arr[i - 1]);
                maxsf[i] = max;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (minsf[i] > maxsf[i]) {
                c++;
            }
        }
        System.out.println(c);
    }

    public static void longestConsecutiveOnes() { // atmost one zero flippedallowed
        int[] arr = { 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0 };
        HashMap<Integer, Integer> map = new HashMap<>();
        int e = 0;
        int s = 0;
        int len = 0;
        while (e < arr.length) {
            if (map.containsKey(0) && map.get(0) > 1) {
                // release
                if (map.get(arr[s]) > 1) {
                    map.put(arr[s], map.get(arr[s]) - 1);
                    s++;
                } else {
                    map.remove(arr[s]);
                    s++;
                }
            } else {
                while (true) {// size==1 or 0's freq 1
                    if (map.containsKey(0) && map.get(0) > 1) {
                        break;
                    }
                    // acquire
                    map.put(arr[e], map.containsKey(arr[e]) ? map.get(arr[e]) + 1 : 1);
                    e++;
                    len = Math.max(len, (e - s - 1));
                }
            }
        }
        System.out.println(len);
    }

    // public static void nextCloseTime() {
    // int[] arr = { 2, 2, 3, 9 };
    // TreeSet<Integer> set = new TreeSet<>();
    // for (int i : arr) {
    // set.add(i);
    // }
    // int i = arr.length - 1;
    // while (i >= 0) {
    // if(set.ceiling(arr[i])!=-1){
    // if(isvalid(arr,set.ceiling(arr[i]),i)){
    // arr[i]=set.ceiling(arr[i]);
    // break;
    // }
    // }else{
    // }
    // i--;
    // }
    // }

    public static void boldTag() {
        String str = "abcdefghijklmnop";
        String[] arr = { "abc", "ghi", "hijkl", "ef" };
        boolean[] map = new boolean[str.length()];
        for (int i = 0; i < map.length; i++) {
            map[i] = false;
        }
        for (String s : arr) {
            int start = str.indexOf(s);
            for (int i = start; i < start + s.length(); i++) {
                map[i] = true;
            }
        }
        int i = 0;
        while (i < map.length) {
            if (map[i]) {
                System.out.print("<b>");
                while (map[i]) {
                    System.out.print(str.charAt(i));
                    i++;
                }
                System.out.print("<b>");
            } else {
                System.out.print(str.charAt(i));
                i++;
            }
        }
    }

}