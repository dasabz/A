package map;

import java.util.ArrayList;
import java.util.List;

class MyHashMap<K, V> {
    private static final int size = 100;
    ArrayList<List<Entry>> entries;

    MyHashMap() {
        entries = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            entries.add(new ArrayList<>());
        }
    }

    void put(K key, V value) {
        int idx = key.hashCode() % size;
        List<Entry> list = this.entries.get(idx);
        for (Entry i : list) {
            if (key.equals(i.getKey())) {
                i.setValue(value);
                return;
            }
        }
        list.add(new Entry(key, value));
    }

    V get(K key) throws Exception {
        int idx = key.hashCode() % size;
        List<Entry> list = this.entries.get(idx);
        for (Entry i : list) {
            if (key.equals(i.getKey())) {
                return i.getValue();
            }
        }

        throw new Exception("Map does not contain [" + key + "]");
    }

    private class Entry {
        private K key;
        private V value;

        Entry() {
        }

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}

