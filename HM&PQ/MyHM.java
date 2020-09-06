import java.util.ArrayList;
import java.util.LinkedList;

public class MyHM<K, V> {
    public class pair {
        K key;
        V value;
    }

    LinkedList<pair>[] buckets;
    int size;

    public MyHM() {
        size = 0;
        buckets = new LinkedList[4];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public boolean containsKey(K key) {
        int bi = hashFunc(key);
        int di = dataIndex(key, bi);
        if (di == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void put(K key, V value) {
        int bi = hashFunc(key);
        int di = dataIndex(key, bi);
        if (di == -1) {
            size++;
            pair p = new pair();
            p.key = key;
            p.value = value;
            buckets[bi].add(p);
        } else {
            buckets[bi].get(di).value = value;
        }

        double lambda = size * 1.0 / buckets.length;
        if (lambda > 2.0) {
            rehash();
        }
    }

    public V get(K key) {
        int bi = hashFunc(key);
        int di = dataIndex(key, bi);
        if (di == -1) {
            return null;
        } else {
            return buckets[bi].get(di).value;
        }
    }

    public void remove(K key) {
        int bi = hashFunc(key);
        int di = dataIndex(key, bi);
        if (di == -1) {
            return;
        } else {
            size--;
            buckets[bi].remove(di);
        }
    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for (int bi = 0; bi < buckets.length; bi++) {
            for (pair p : buckets[bi]) {
                keys.add(p.key);
            }
        }
        return keys;
    }

    public int hashFunc(K key) {
        int hc = key.hashCode();
        int bi = Math.abs(hc) % buckets.length;
        return bi;
    }

    public int dataIndex(K key, int bi) {
        int di = 0;
        for (pair p : buckets[bi]) {
            if (p.key.equals(key)) {
                return di;
            }
            di++;
        }
        return -1;
    }

    public void rehash() {
        LinkedList<pair>[] obuckets = buckets;
        buckets = new LinkedList[obuckets.length * 2];
        for (int bi = 0; bi < buckets.length; bi++) {
            buckets[bi] = new LinkedList<>();
        }
        size = 0;
        for (int bi = 0; bi < obuckets.length; bi++) {
            for (pair p : obuckets[bi]) {
                put(p.key, p.value);
            }
        }
    }

    @Override
    public String toString() {
        String myhm="map=[";
        for(LinkedList<pair> b:buckets){
            for(pair p:b){
                myhm+="{"+p.key+","+p.value+"}";
            }
        }
        myhm+="]";
        return myhm;
    }

    public static void main(String[] args) {
        MyHM<Integer,Integer> map= new MyHM();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        System.out.println(map);
    }
}
