import java.util.*;

    public class June22{
    
        public static void main(String[] args) {
        // uncommonChar();
        // System.out.println(validSudoku());
        // uncommonWordsin2String();
        // unionOfArrays();
        // System.out.println(validAnagram());
        // pepCodingNewSys();
        // groupShiftedString();
        // gana();
        // happyNumber();
        // heightOfHeap();
        // inFirstNotSecond();
        // islandParameter();
        // commonEle1();
        // commonEle2();
        // keyboardRow();
        // jewelsAndStones();
        // System.out.println(isomorphic());
        // kLargerInStream();
        // kthLargest();
        // kthClosestToOrigin();
        
    }
    public static void uncommonChar() {
        String s1 = "aksgdsbcgdjacsdk";
        String s2 = "sakgdwqjhbdja";

        int n1 = 0;
        int n2 = 0;

        for (int i = 0; i < s1.length(); i++) {
            n1 = n1 | (1 << (s1.charAt(i) - 'a'));
        }
        for (int i = 0; i < s2.length(); i++) {
            n2 = n2 | (1 << (s2.charAt(i) - 'a'));
        }
        int n = n1 ^ n2;

        for (int i = 0; i < 26; i++) {
            if ((n & (1 << i)) != 0) {
                System.out.print((char) ('a' + i));
            }
        }
    }

    public static boolean validSudoku() {
        int[][] matrix = { { 3, 1, 6, 5, 7, 8, 4, 9, 2 }, { 5, 2, 9, 1, 3, 4, 7, 6, 8 }, { 4, 8, 7, 6, 2, 9, 5, 3, 1 },
                { 2, 6, 3, 4, 1, 5, 9, 8, 7 }, { 9, 7, 4, 8, 6, 3, 1, 2, 5 }, { 8, 5, 1, 7, 9, 2, 6, 4, 3 },
                { 1, 3, 8, 9, 4, 7, 2, 5, 6 }, { 1, 3, 8, 9, 4, 7, 2, 5, 6 }, { 7, 4, 5, 2, 8, 6, 3, 1, 9 } };
        int[] row = new int[9];
        int[] col = new int[9];
        int[][] subm = new int[3][3];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (((row[i] & (1 << matrix[i][j])) != 0) || ((col[j] & (1 << matrix[i][j])) != 0)
                        || ((subm[i / 3][j / 3] & (1 << matrix[i][j])) != 0)) {
                    return false;
                } else {
                    row[i] = row[i] | (1 << matrix[i][j]);
                    col[j] = col[j] | (1 << matrix[i][j]);
                    subm[i / 3][j / 3] = subm[i / 3][j / 3] | (1 << matrix[i][j]);
                }
            }
        }
        return true;
    }

    public static void gana() {
        String[] arr = { "ate", "eat", "tae", "def", "fed", "aab", "baa", "a" };

        HashMap<HashMap<Character, Integer>, ArrayList<String>> ans = new HashMap<>();
        for (String str : arr) {
            HashMap<Character, Integer> fmap = new HashMap<>();
            for (Character ch : str.toCharArray()) {
                fmap.put(ch, fmap.containsKey(ch) ? fmap.get(ch) + 1 : 1);
            }
            if (ans.containsKey(fmap)) {
                ArrayList<String> lis = ans.get(fmap);
                lis.add(str);
                ans.put(fmap, lis);
            } else {
                ArrayList<String> lis = new ArrayList<>();
                lis.add(str);
                ans.put(fmap, lis);
            }
        }
        for (HashMap<Character, Integer> a : ans.keySet()) {
            System.out.println(ans.get(a));
        }
    }

    public static void uncommonWordsin2String() {
        String s1 = "ajskd sakhddj askdh sakhddj";
        String s2 = "ahdks sdiw oefnsb";
        HashSet<String> set = new HashSet<>();
        for (String str : s1.split(" ")) {
            set.add(str);
        }
        for (String str : s2.split(" ")) {
            set.add(str);
        }
        System.out.println(set);
    }

    public static void unionOfArrays() {
        int[] arr1 = { 2, 6, 7, 8, 3, 1, 2, 4, 8, 1, 2 };
        int[] arr2 = { 8, 7, 3, 2, 8, 3, 1, 2, 4, 8, 2, 8, 7, 3, 8, 5 };
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr1) {
            set.add(i);
        }
        for (int i : arr2) {
            set.add(i);
        }
        System.out.println(set);
    }

    public static boolean validAnagram() {
        String s1 = "sah";
        String s2 = "has";
        HashMap<Character, Integer> map = new HashMap<>();

        for (Character c : s1.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }
        for (Character c : s2.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c) - 1 : 1);
        }

        for (Character c : map.keySet()) {
            if (map.get(c) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void pepCodingNewSys() {
        String[] arr = { "robin", "robin", "rahul", "robin", "sahil", "rahul" };
        HashMap<String, Integer> map = new HashMap<>();

        for (String str : arr) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
                if (map.get(str) > 1) {
                    System.out.println(str + " " + map.get(str) + " time");
                }
            } else {
                map.put(str, 1);
                System.out.println(str + " verified");
            }
        }
    }

    public static void groupShiftedString() {
        String[] arr = { "abc", "bcd", "xyz", "abd", "cdf", "mnp", "yzb", "zab", "az", "ba" };
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        for (String str : arr) {
            ArrayList<Integer> diff = new ArrayList<>();
            for (int i = 0; i < str.length() - 1; i++) {
                int d = str.charAt(i + 1) - str.charAt(i);
                if (d < 0) {
                    d += 26;
                }
                diff.add(d);
            }
            int key = 0;
            for (int i = 0; i < diff.size(); i++) {
                key += Math.pow(26, i) * diff.get(i);
            }
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                ArrayList<String> lis = new ArrayList<>();
                lis.add(str);
                map.put(key, lis);
            }
        }
        for (int i : map.keySet()) {
            System.out.println(map.get(i));
        }
    }

    public static void happyNumber() {
        int n = 19;
        HashSet<Integer> set = new HashSet<>();
        while (true) {
            int temp = n;
            int ans = 0;
            while (temp > 0) {
                int rem = temp % 10;
                ans += Math.pow(rem, 2);
                temp /= 10;
            }
            n = ans;
            if (n == 1 || set.contains(n)) {
                break;
            }
            set.add(n);
        }
        if (n != 1) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }

    public static void heightOfHeap() {
        int n = 8;// arr size
        int x = 0;
        while ((1 << x) < n + 1) {// (getceil) log base 2 (N+1) --> height of heap
            // while((1<<x)<=n)-> x-1 (getfloor)
            x++;
        }
        System.out.println(x);
    }

    public static void inFirstNotSecond() {
        int[] one = { 3, 5, 6, 9, 1 };
        int[] two = { 1, 9, 7, 7, 6, 10, 9, 1 };
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int i : one) {
            set.add(i);
        }
        for (int i : two) {
            set.add(i);
        }
        System.out.println(set);
    }

    public static void islandParameter() {
        int[][] arr = { { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 } };
        int paramtr = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    if (i + 1 < arr.length && arr[i + 1][j] == 1) {
                        paramtr -= 2;
                    }
                    if (j + 1 < arr[i].length && arr[i][j + 1] == 1) {
                        paramtr -= 2;
                    }
                    paramtr += 4;
                }
            }
        }
        System.out.println(paramtr);
    }

    public static void commonEle1() { // 1 2 5
        int[] a1 = { 1, 1, 2, 2, 2, 5 };
        int[] a2 = { 1, 1, 1, 2, 2, 4, 5 };
        HashSet<Integer> set = new HashSet<>();
        for (int i : a1) {
            set.add(i);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (int i : a2) {
            set2.add(i);
        }
        for (Object i : set.toArray()) {
            if (set2.contains(i)) {
                System.out.println(i);
            }
        }
    }

    public static void commonEle2() { // 1 1 2 2 5
        int[] a1 = { 1, 1, 2, 2, 2, 5 };
        int[] a2 = { 1, 1, 1, 2, 2, 4, 5 };
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : a1) {
            map.put(i, map.containsKey(i) ? map.get(i) + 1 : 1);
        }
        for (int i : a2) {
            if (map.containsKey(i)) {
                if (map.get(i) > 1) {
                    map.put(i, map.get(i) - 1);
                } else {
                    map.remove(i);
                }
                System.out.print(i + " ");
            }
        }
    }

    public static void keyboardRow() {
        String str = "peter";
        int[] fmap = new int[26];
        char ch;
        for (int i = 0; i < 26; i++) {
            ch = (char) (i + 'a');
            if (ch == 'q' || ch == 'w' || ch == 'e' || ch == 'r' || ch == 't' || ch == 'y' || ch == 'u' || ch == 'i'
                    || ch == 'o' || ch == 'p') {
                fmap[ch - 'a'] = 1;
            }
            if (ch == 'a' || ch == 's' || ch == 'd' || ch == 'f' || ch == 'g' || ch == 'h' || ch == 'h' || ch == 'j'
                    || ch == 'k' || ch == 'l') {
                fmap[ch - 'a'] = 2;
            }
            if (ch == 'z' || ch == 'x' || ch == 'c' || ch == 'v' || ch == 'b' || ch == 'n' || ch == 'm') {
                fmap[ch - 'a'] = 3;
            }
        }
        HashMap<Integer, ArrayList<Character>> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (map.containsKey(fmap[c - 'a'])) {
                map.get(fmap[c - 'a']).add(c);
            } else {
                ArrayList<Character> lis = new ArrayList<>();
                lis.add(c);
                map.put(fmap[c - 'a'], lis);
            }
        }

        if (map.size() == 1) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    public static void jewelsAndStones() {
        int[] j = { 2, 3, 5 };
        int[] s = { 9, 2, 1, 0, 4, 1, 3, 4, 7, 8, 3, 4 };
        HashSet<Integer> set = new HashSet<>();
        for (int i : j) {
            set.add(i);
        }
        for (int i : s) {
            if (set.contains(i)) {
                System.out.println(i + " is a jewel");
            }
        }
    }

    public static boolean isomorphic() {
        char[] one = { 'a', 'b', 'c', 'b', 'a' };
        char[] two = { 'z', 'm', 'n', 'm', 'z' };
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < one.length; i++) {
            if (map.containsKey(one[i])) {
                if (map.get(one[i]) == two[i]) {
                    // ok
                } else {
                    return false;
                }
            } else {
                map.put(one[i], two[i]);
            }
        }
        for (int i = 0; i < one.length; i++) {
            if (map.containsKey(two[i])) {
                if (map.get(two[i]) == one[i]) {
                    // ok
                } else {
                    return false;
                }
            } else {
                map.put(two[i], one[i]);
            }
        }
        return true;
    }

    public static void kLargerInStream() {
        int[] arr = { 2, 4, 3, 5, 1, 6, 8, 7, 6, 5, 4, 7, 5, 7 };
        int k = 3;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > pq.peek()) {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        while (pq.size() > 0) {
            System.out.println(pq.poll());
        }
    }

    public static void kthLargest() {
        int[] arr = { 13, 456, 7, 8, 5, 3, 26, 4, 46, 17, 89, 0 };
        int k = 5;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > pq.peek()) {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        System.out.println(pq.peek());
    }

    public static class Pair implements Comparable<Pair> {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            return ((this.x * this.x) + (this.y * this.y)) - ((o.x * o.x) + (o.y * o.y));
        }

    }

    public static void kthClosestToOrigin() {
        int[] xarr = { 1, 2, 3, 3, 4, 4 };
        int[] yarr = { 1, 2, 2, 1, 1, 3 };
        int k = 2;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(new Pair(xarr[i], yarr[i]));
        }
        for (int i = k; i < xarr.length; i++) {
            Pair p = new Pair(xarr[i], yarr[i]);
            Pair rem = pq.peek();
            if (p.compareTo(rem) < 1) {
                pq.remove();
                pq.add(p);
            }
        }
        while (pq.size() > 0) {
            System.out.println("{" + pq.peek().x + "," + pq.peek().y + "}");
            pq.remove();
        }
    }

}