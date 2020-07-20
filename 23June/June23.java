import java.util.*;
public class June23 {
    public static void main(String[] args) {
        // longestConsecutiveSubsequence();
        // minimumRooms1();
        // minimumRooms2();
        // maxBWTwoSameChar();
        // longestSubArrKOnes()
        // longestSubArrZerosAndOnes();
        // longestPallindromicSubsequence();
        // loggerRateLimiter();
        // equivalentArr();
        // System.out.println(wordPattern());
        
    }
    public static void longestConsecutiveSubsequence() {
        int[] arr = { 1, 9, 12, 11, 6, 2, 8, 3, 5, 10 };
        int max = 0;
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, true);
        }
        for (int i : map.keySet()) {
            if (map.containsKey(i - 1)) {
                map.put(i, false);
            }
        }
        for (int i : map.keySet()) {
            if (map.get(i) == true) {
                int len = 1;
                while (map.containsKey(i + len)) {
                    len++;
                }
                max = Math.max(max, len);
            }
        }
        System.out.println(max);
    }

    public static void minimumRooms1() { // nlogn no space
        int[] startTime = { 2, 3, 4, 6, 8, 9 };
        int[] endTime = { 3, 5, 6, 9, 9, 10 };
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        int i = 0;
        int j = 0;
        int c = 0;
        int max = 0;

        while (i < startTime.length) {
            if (startTime[i] < endTime[j]) {
                i++;
                c++;
            } else {
                j++;
                c--;
            }
            max = Math.max(max, c);
        }
        System.out.println(max);
    }

    public static void minimumRooms2() {// o(n) but more space
        int[] startTime = { 2, 3, 4, 6, 8, 9 };
        int[] endTime = { 3, 5, 6, 9, 9, 10 };
        int max = 0;
        for (int i = 0; i < startTime.length; i++) {
            max = Math.max(max, Math.max(startTime[i], endTime[i]));
        }
        int[] map = new int[max + 1];
        for (int i = 0; i < startTime.length; i++) {
            map[startTime[i]]++;
            map[endTime[i]]--;
        }
        int sum = 0;
        for (int i = 1; i < map.length; i++) {
            map[i] += map[i - 1];
            sum = Math.max(sum, map[i]);
        }
        System.out.println(sum);
    }

    public static void maxBWTwoSameChar() {
        String str = "abcdabcbdacbdfd";
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (!map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), i);
            } else {
                max = Math.max(max, (i - map.get(str.charAt(i)) + 1));
            }
        }
        System.out.println(max);
    }

    public static void longestSubArrZerosAndOnes() {
        int[] arr = { 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 };
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }
        int tar = 0;
        int psum = 0;
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            psum += arr[i];
            if (map.containsKey(tar - psum)) {
                len = Math.max(len, (i - map.get(tar - psum)) + 1);
            }
            if (map.containsKey(psum)) {

            } else {
                map.put(psum, i);
            }
        }
        System.out.println(len);
    }

    public static void longestSubArrKOnes() {
        int[] arr = { 1, 1, 3, 2, 8, 1, 8, 1, 2, 2, 8, 1, 6, 8, 1, 2, 8, 1, 2, 8, 1, 3, 6, 2, 1, 8, 7, 2 };
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 1) {
                arr[i] = -1;
            }
        }
        int k = 4;
        int psum = 0;
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            psum += arr[i];
            if (map.containsKey(k - psum)) {
                len = Math.max(len, (i - map.get(k - psum)) + 1);
            }
            if (map.containsKey(psum)) {

            } else {
                map.put(psum, i);
            }
        }
        System.out.println(len);
    }

    public static void longestTarSubArr() {
        int[] arr = { 7, 8, 9, 1, 2, 3, 4, 6, 8, 9, 7, 3, 6, 4, 1, 9, 2, 8 };
        int tar = 5;
        int psum = 0;
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            psum += arr[i];
            if (map.containsKey(tar - psum)) {
                len = Math.max(len, (i - map.get(tar - psum)) + 1);
            }
            if (map.containsKey(psum)) {
            } else {
                map.put(psum, i);
            }
        }
        System.out.println(len);
    }

    public static void longestPallindromicSubsequence() { // only length any how not DP ques
        String str = "abababccccbddadad";
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character c : str.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }
        int len = 0;
        for (char c : map.keySet()) {
            if (map.get(c) % 2 == 0) {
                len += map.get(c);
            } else {
                len += map.get(c) - 1;
            }
        }
        len += 1;
        System.out.println(len);
    }

    public static void loggerRateLimiter() {
        String str = "abcabacbc";
        int[] arr = { 2, 8, 10, 11, 14, 16, 18, 20, 22 };
        int diff = 10;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (!map.containsKey(str.charAt(i))) {
                System.out.print(str.charAt(i));
                map.put(str.charAt(i), arr[i]);
            } else {
                if (arr[i] - map.get(str.charAt(i)) >= diff) {
                    System.out.print(str.charAt(i));
                    map.put(str.charAt(i), arr[i]);
                }
            }
        }
    }

    public static void equivalentArr() {
        String str = "abcbadabcbabbaddac";
        HashSet<Character> set = new HashSet<>();
        for (Character c : str.toCharArray()) {
            set.add(c);
        }
        int e = 0;
        int s = 0;
        int len = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (e < str.length()) {
            if (map.size() == set.size()) {
                len += str.length() - e;
                while (map.size() >= set.size()) {
                    if (map.get(str.charAt(s)) > 1) {
                        map.put(str.charAt(s), map.get(str.charAt(s)) - 1);
                        s++;
                    } else {
                        map.remove(str.charAt(s));
                        s++;
                    }
                    len += str.length() - e;
                }
            }
            while (map.size() < set.size()) {
                map.put(str.charAt(e), map.containsKey(str.charAt(e)) ? map.get(str.charAt(e)) + 1 : 1);
                e++;
            }
        }
        System.out.println(len);
    }

    public static boolean wordPattern() {
        String[] one = { "a", "b", "a", "b" };
        String[] two = { "pep", "coding", "pep", "coding" };
        HashMap<String, String> map = new HashMap<>();
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

}