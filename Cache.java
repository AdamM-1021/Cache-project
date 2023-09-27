import java.util.LinkedList;

public class Cache <K, V extends KeyInterface<K>>{
    
    private LinkedList<V> cache;
    private int size;
    private int references;
    private int hits;

    public Cache(int size) {
        this.size = size;
        cache = new LinkedList<V>();
    }

    //Cache functions

    public V get(K key) {
        V val = null;
        references++;
        
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i).getKey().equals(key)) {
                hits++;
                val = cache.get(i);
                cache.remove(i);
                cache.addFirst(val);
                return val;
            }
        }

        return null;
    }

    public V add(V value) {
        V removed = null;
        if (cache.size() == size) {
            removed = cache.removeLast();
            cache.addFirst(value);
        }
        else {
            cache.addFirst(value);
        }

        return removed;
    }

    public V remove(K key) {
        V removed = null;

        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i).getKey().equals(key)) {
                removed = cache.remove(i);
            }
        }

        return removed;
    }

    public void clear() {
        cache.clear();
        return;
    }

    public String toString() {
        String string = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

        string += ("Cache with " + size + " entries has been created\n"
            + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        
        string += ("Total number of references: " + references + "\n");
        string += ("Total number of cache hits: " + hits + "\n");
        string += ("Cache hit percent:          " +String.format("%.2f", (double)Math.round(((double)hits/references) * 10000.0f)/100) + "%\n");

        return string;
    }

}