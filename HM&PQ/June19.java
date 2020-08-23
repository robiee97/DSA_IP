import java.util.*;

public class June19 {
    public static void greatestSmallerOnLeft() {
        int[] ar = { 4, 2, 5, 6, 8, 7, 3, 9, 1 };
        int[] ans = new int[ar.length];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < ar.length; i++) {
            set.add(ar[i]);
            if (set.lower(ar[i]) != null) {
                ans[i] = set.lower(ar[i]);
            } else {
                ans[i] = -1;
            }
        }
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    public static void fakeProfile() {
        String str = "kurbciuwbkugj";
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        if (set.size() % 2 == 0) {
            System.out.println("GIRL");
        } else {
            System.err.println("BOY");
        }
    }

    public static void distinctElementsAfterOp() {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>();

        while (t-- > 0) {
            int val = s.nextInt();
            char op = s.next().charAt(0);
            if (op == 'A') {
                if (map.containsKey(val)) {
                    map.put(val, map.get(val) + 1);
                } else {
                    map.put(val, 1);
                }
                System.out.println(map.size());
            } else {
                if (map.containsKey(val)) {
                    map.remove(val);
                }
                System.out.println(map.size());
            }
        }
    }

    public static void gapBWHandLFreq() {
        int[] arr = { 2, 9, 8, 1, 3, 3, 9, 7, 9, 7, 8, 2 };
        int max = Integer.MIN_VALUE;
        int maxele = 0;
        int min = Integer.MAX_VALUE;
        int minele = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
            max = Math.max(max, map.get(i));
            min = Math.min(min, map.get(i));
        }
        for (int i : map.keySet()) {
            if (map.get(i) == max) {
                maxele = i;
            }
            if (map.get(i) == min) {
                minele = i;
            }
        }
        System.out.println(maxele + " " + minele);
        System.out.println(maxele - minele);
    }

    public static void duplicatesInArr() {
        int[] arr = { 2, 3, 0, 8, 9, 7, 4, 7, 5, 0, 8, 3, 0, 2 };
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                System.out.println(i);
            } else {
                map.put(i, 1);
            }
        }
    }

    public static class pairOfEleFreq implements Comparable<pairOfEleFreq> {
        char c;
        int f;

        pairOfEleFreq(char c, int f) {
            this.c = c;
            this.f = f;
        }

        @Override
        public int compareTo(pairOfEleFreq p) {
            return p.f - this.f;
        }
    }

    public static void noAdjEleSame() {
        char[] arr = { 'a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'd' };
        HashMap<Character, Integer> map = new HashMap<>();
        boolean flag = true;
        for (char c : arr) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (char c : map.keySet()) {
            if (map.get(c) > (arr.length + 1 / 2)) {
                System.out.println("Cannot be placed in adjacent manner without same");
                flag = false;
                break;
            }
        }
        if (flag) {
            PriorityQueue<pairOfEleFreq> pq = new PriorityQueue<>();
            for (char ch : map.keySet()) {
                pq.add(new pairOfEleFreq(ch, map.get(ch)));
            }
            while (pq.size() > 1) {
                pairOfEleFreq p1 = pq.remove();
                pairOfEleFreq p2 = pq.remove();
                System.out.print(p1.c + "," + p2.c + ",");
                if (p1.f > 1) {
                    pq.add(new pairOfEleFreq(p1.c, p1.f - 1));
                }
                if (p2.f > 1) {
                    pq.add(new pairOfEleFreq(p2.c, p2.f - 1));
                }
            }
            pairOfEleFreq np = pq.remove();
            System.out.print(np.c);
        }
    }

    public static void ksmallestInSameOrder() {
        int[] arr = { 8, 9, 1, 2, 3, 3, 0, 4, 2, 8, 4, 0, 2, 1, 7, 4, 9 };
        int k = 4;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (pq.peek() > arr[i]) {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        for (int i : arr) {
            if (i < pq.peek()) {
                System.out.println(i);
            } else if (i == pq.peek()) {
                System.out.println(pq.peek());
                pq.remove();
            }
        }
    }

    public static class info {
        int f;
        int s;
        int e;

        info(int f, int s, int e) {
            this.f = f;
            this.s = s;
            this.e = e;
        }
    }

    public static void smallestSubArrAllOccOfHFC() {
        int[] arr = { 4, 2, 3, 1, 2, 1, 4, 2, 4, 3, 1 };
        int bval = 0;
        int bfreq = 0;
        int bst = 0;
        int ben = 0;
        HashMap<Integer, info> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], new info(map.get(arr[i]).f + 1, map.get(arr[i]).s, i));
            } else if (!map.containsKey(arr[i])) {
                map.put(arr[i], new info(1, i, i));
            }
            if (map.get(arr[i]).f > bfreq) {
                bval = arr[i];
                bfreq = map.get(arr[i]).f;
                bst = map.get(arr[i]).s;
                ben = map.get(arr[i]).e;
            } else if ((map.get(arr[i]).e - map.get(arr[i]).s) < (ben - bst)) {
                bval = arr[i];
                bfreq = map.get(arr[i]).f;
                bst = map.get(arr[i]).s;
                ben = map.get(arr[i]).e;
            }
        }
        for (int i = bst; i <= ben; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static class sortPair implements Comparable<sortPair> {
        int ele;
        int f;

        sortPair(int ele, int f) {
            this.ele = ele;
            this.f = f;
        }

        @Override
        public int compareTo(sortPair p) {
            return p.f - this.f;
        }

    }

    public static void sortEleOnFreq() {
        int[] arr = { 2, 8, 1, 9, 4, 3, 7, 4, 3, 2, 1, 6, 4, 7, 2, 3, 4, 1, 2, 7, 5, 4, 4, 3, 9, 2, 9, 2, 1 };
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        PriorityQueue<sortPair> pq = new PriorityQueue<>();
        for (int i : map.keySet()) {
            pq.add(new sortPair(i, map.get(i)));
        }
        while (pq.size() > 0) {
            System.out.println(pq.poll().ele);
        }
    }

    public static class subPair implements Comparable<subPair> {
        String s;
        int f;

        subPair(String s, int f) {
            this.s = s;
            this.f = f;
        }

        @Override
        public int compareTo(subPair p) {
            return p.f - this.f;
        }
    }

    public static void subdomain() {
        String[] arr = { "mail.google.com", "wiki.org", "google.com", "mail.wiki.com" };
        HashMap<String, Integer> map = new HashMap<>();

        for (String s : arr) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        PriorityQueue<subPair> pq = new PriorityQueue<>();
        for (String i : map.keySet()) {
            pq.add(new subPair(i, map.get(i)));
        }

        while (pq.size() > 0) {
            subPair p = pq.remove();
            System.out.println(p.s + " -> " + p.f);
        }

    }

    // ------------------subArrayProblems-------------------------//
    public static void countTarSumSubArr() {
        int[] arr = { 1, 9, -2, 7, -4, 1, 9, 0, 4, 2, -7, 9, 3, 8, -7, 8, 0, -9, 2, 3, 8 };
        int tar = 7;
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - tar)) {
                count += map.get(sum - tar);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        System.out.println(count);
    }

    public static void countTarSumSubArrOfZero() {
        int[] arr = { 1, 9, -2, 7, -4, 1, 9, 0, 4, 2, -7, 9, 3, 8, -7, 8, 0, -9, 2, 3, 8 };
        int tar = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - tar)) {
                count += map.get(sum - tar);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        System.out.println(count);
    }

    public static void countSubArrOf0s1s() {
        int[] arr = { 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1 };
        int[] narr = new int[arr.length];
        for (int i = 0; i < narr.length; i++) {
            if (arr[i] == 0) {
                narr[i] = -1;
            } else {
                narr[i] = arr[i];
            }
        }
        int tar = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < narr.length; i++) {
            sum += narr[i];
            if (map.containsKey(sum - tar)) {
                count += map.get(sum - tar);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        System.out.println(count);
    }

    public static void countBinarySubArrSumTar() {
        int[] arr = { 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1 };
        int tar = 6;
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - tar)) {
                count += map.get(sum - tar);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        System.out.println(count);
    }

    public static void countSubArrMulOfK() {
        int[] arr = { 1, 9, -2, 7, -4, 1, 9, 0, 4, 2, -7, 9, 3, 8, -7, 8, 0, -9, 2, 3, 8 };
        int k = 5;
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int mod = sum % k;
            if (mod < 0) {
                mod += k;
            }
            if (map.containsKey(mod)) {
                count += map.get(mod);
                map.put(mod, map.get(mod) + 1);
            } else {
                map.put(mod, 1);
            }
        }
        System.out.println(count);
    }
    // ---------------------------------------------------------------------//

    // --------------------smallest Window in array------------------------//

    public static void smallestWindowOfRinSJustExist() {

        /*
         * algo: hs of text start aquiring str if -hm.size!=set.size- ie inc end inc the
         * hm of str else you got first ans store in bs be now for bettr ans start
         * releasing st point if size maintained strore current best ans apperars in btw
         */

        String str = "abcaadddbccadbac";
        String text = "acbbda";
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < text.length(); i++) {
            set.add(text.charAt(i));
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int bs = 0;
        int be = str.length() - 1;
        int s = 0;
        int e = 0;
        while (true) {
            if (map.size() != set.size()) {
                map.put(str.charAt(e), map.containsKey(str.charAt(e)) ? map.get(str.charAt(e)) + 1 : 1);
                e++;
            } else {
                if ((be - bs) > (e - s)) {
                    bs = s;
                    be = e - 1;
                }
                while (map.size() == set.size()) {
                    if (map.get(str.charAt(s)) > 1) {
                        map.put(str.charAt(s), map.get(str.charAt(s)) - 1);
                        s++;
                        if ((be - bs) > (e - s)) {
                            bs = s;
                            be = e - 1;
                        }
                    } else {
                        map.remove(str.charAt(s));
                        s++;
                    }
                }
            }
            if (e == str.length()) {
                break;
            }
        }

        System.out.println(bs + " " + be);
        for (int i = bs; i <= be; i++) {
            System.out.print(str.charAt(i));
        }
    }

    public static void smallestWindowOfRinSExactSame() {

        /*
         * algo: hm of text start aquiring str ie inc end inc the hm of str if-freq<=of
         * other mc++ else nothing one day mc== lengthof txt for bettr ans start
         * releasing st point if freq<other mc-- else nothing and store if best ans
         * apperars in btw
         */

        String str = "abcaadddbccdaabacdac";
        String text = "abbcad";
        HashMap<Character, Integer> textmap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            textmap.put(text.charAt(i), textmap.containsKey(text.charAt(i)) ? textmap.get(text.charAt(i)) + 1 : 1);
        }

        HashMap<Character, Integer> map = new HashMap<>();
        int mc = 0;
        int bs = 0;
        int be = str.length() - 1;
        int s = 0;
        int e = 0;

        while (true) {
            if (mc == text.length()) {
                if ((be - bs) > (e - s)) {
                    bs = s;
                    be = e;
                }
                while (mc == text.length()) {
                    map.put(str.charAt(s), map.get(str.charAt(s)) - 1);
                    if (map.get(str.charAt(s)) < textmap.get(str.charAt(s))) {
                        mc--;
                    }
                    if ((be - bs) > (e - s)) {
                        bs = s;
                        be = e;
                    }
                    s++;
                }
            } else {
                map.put(str.charAt(e), map.containsKey(str.charAt(e)) ? map.get(str.charAt(e)) + 1 : 1);
                if (map.get(str.charAt(e)) <= textmap.get(str.charAt(e))) {
                    mc++;
                }
                e++;
            }
            if (e == str.length()) {
                break;
            }
        }

        System.out.println(bs + " " + be);
        for (int i = bs; i <= be; i++) {
            System.out.print(str.charAt(i));
        }
    }

    public static void allDistinctCharOfSinR() {
        String str = "abcaadddbccadbac";
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int bs = 0;
        int be = str.length() - 1;
        int s = 0;
        int e = 0;
        while (true) {
            if (map.size() != set.size()) {
                map.put(str.charAt(e), map.containsKey(str.charAt(e)) ? map.get(str.charAt(e)) + 1 : 1);
                e++;
            } else {
                if ((be - bs) > (e - s - 1)) {
                    bs = s;
                    be = e - 1;
                }
                while (map.size() == set.size()) {
                    if (map.get(str.charAt(s)) > 1) {
                        map.put(str.charAt(s), map.get(str.charAt(s)) - 1);
                        s++;
                        if ((be - bs) > (e - s - 1)) {
                            bs = s;
                            be = e - 1;
                        }
                    } else {
                        map.remove(str.charAt(s));
                        s++;
                    }
                }
            }
            if (e == str.length()) {
                break;
            }
        }

        for (int i = bs; i <= be; i++) {
            System.out.print(str.charAt(i));
        }
    }

    public static boolean compare(HashMap<Character, Integer> hm1, HashMap<Character, Integer> hm2) {
        for (Character c : hm1.keySet()) {
            if (!hm2.containsKey(c)) {
                return false;
            } else {
                if (hm1.get(c) != hm2.get(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void allAnagrams() {
        String s = "abcaabacab";
        String p = "abc";
        ArrayList<Integer> arr = new ArrayList<>();
        HashMap<Character, Integer> hm1 = new HashMap<>();
        HashMap<Character, Integer> hm2 = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            hm1.put(p.charAt(i), hm1.containsKey(p.charAt(i)) ? hm1.get(p.charAt(i)) + 1 : 1);
        }
        if (s.length() < p.length()) {
            System.out.println("NULL");
        } else {
            for (int i = 0; i < p.length(); i++) {
                hm2.put(s.charAt(i), hm2.containsKey(s.charAt(i)) ? hm2.get(s.charAt(i)) + 1 : 1);
            }
            for (int i = p.length(); i < s.length(); i++) {
                if (compare(hm1, hm2)) {
                    arr.add(i - p.length());
                }
                hm2.put(s.charAt(i), hm2.containsKey(s.charAt(i)) ? hm2.get(s.charAt(i)) + 1 : 1);
                hm2.put(s.charAt(i - p.length()),
                        hm2.containsKey(s.charAt(i - p.length())) ? hm2.get(s.charAt(i - p.length())) - 1 : 0);
            }
            if (compare(hm1, hm2)) {
                arr.add(s.length() - p.length());
            }
        }
        System.out.println(arr);
    }

    public static void allAnagrams2() {

    }

    public static void kAnagrams() {

    }

    public static void countSubArr0s1s2s() {

    }

    public static void sieveOfEronthesis() {

    }

    
    // -------------------------------------------------------------------//
    public static void main(String[] args) {
        // greatestSmallerOnLeft();
        // fakeProfile();
        // distinctElementsAfterOp();
        // gapBWHandLFreq();
        // duplicatesInArr();
        // noAdjEleSame();
        // ksmallestInSameOrder();
        // smallestSubArrAllOccOfHFC();
        // sortEleOnFreq();
        // subdomain();
        // countTarSumSubArr();
        // countTarSumSubArrOfZero();
        // countSubArrOf0s1s();
        // countBinarySubArrSumTar();
        // countSubArrMulOfK();
        // smallestWindowOfRinSJustExist();
        // smallestWindowOfRinSExactSame();
        // allDistinctCharOfSinR();
        // allAnagrams();
        // kAnagrams();
        // countSubArr0s1s2s();
        // sieveOfEronthesis();
    }
}