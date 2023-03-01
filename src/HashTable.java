import java.util.LinkedList;

public class HashTable {

    private class Entry {
        private int key;
        private String value;

        public Entry(int k, String v) {
            key = k;
            value = v;
        }
    }

    private LinkedList<Entry>[] entries = new LinkedList[5];

    public void put(int k, String v) {
        int index = hash(k);
        if (entries[index] == null) entries[index] = new LinkedList<>();

        for (var entry : entries[index]) {
            if (entry.key == k) {
                entry.value = v;
            }
        }

        entries[index].addLast(new Entry(k, v));
    }

    public String get(int k) {
        var index = hash(k);

        if (entries[index] == null) return null;

        for (var entry : entries[index]) {
            if (entry.key == k) return entry.value;
        }

        return null;
    }

    private int hash(int k) {
        return k % entries.length;
    }
}
