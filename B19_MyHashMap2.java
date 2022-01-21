package map;

public class MyHashMap2<K, V> {
    int size = 16;
    Entry[] table;

    MyHashMap2() {
        table = new Entry[size];
    }

    public V put(K key, V value) {
        int hash = key.hashCode();
        int i = hash % table.length;
        for (Entry<K, V> e = table[i]; e != null; e = e.next) {
            if (e.hash == hash && (e.key == key || key.equals(e.key))) {
                e.value = value;
                return e.value;
            }
        }
        Entry<K, V> e = table[i];
        table[i] = new Entry<K, V>(hash, key, value, e);
        return null;
    }

    public V get(Object key) {
        int hash = key.hashCode();
        int index = hash % table.length;
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.hash == hash && (e.key == key || key.equals(e.key)))
                return e.value;
        }
        return null;
    }

    class Entry<K, V> {
        K key;
        V value;
        int hash;
        MyHashMap2<K, V>.Entry<K, V> next;
        public Entry(int hash, K key, V value, MyHashMap2<K, V>.Entry<K, V> e) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = e;
        }
    }
}