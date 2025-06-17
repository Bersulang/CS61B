import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class BSTNode {  //封装的内部类
        public K key;
        public V value;
        public BSTNode left;
        public BSTNode right;

        public BSTNode(K key, V value) {  //初始化只需要指定节点值
            this.key = key;
            this.value = value;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }
    
    public BSTNode putHelp(BSTNode bst, K key, V value) {
        if (bst == null) {
            size++;
            return new BSTNode(key, value);
        }
        if (key.compareTo(bst.key) == 0) {
            bst.value = value;
        }
        if (key.compareTo(bst.key) > 0) {
            bst.right = putHelp(bst.right, key, value);
        }
        if (key.compareTo(bst.key) < 0) {
            bst.left = putHelp(bst.left, key, value);
        }
        return bst;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        root = putHelp(root, key, value);
    }

    public V getHelp(BSTNode bst, K key) {
        if (bst == null) {
            return null;
        }
        if (key.compareTo(bst.key) > 0) {
            return getHelp(bst.right, key);
        }
        if (key.compareTo(bst.key) < 0) {
            return getHelp(bst.left, key);
        }
        return bst.value;
    }


    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return getHelp(root, key);
    }


    public boolean containsHelp(BSTNode bst, K key) {
        if (bst == null) {
            return false;
        }
        if (key.compareTo(bst.key) > 0) {
            return containsHelp(bst.right, key);
        }
        if (key.compareTo(bst.key) < 0) {
            return containsHelp(bst.left, key);
        }
        return true;
    }
    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return containsHelp(root, key);
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator(){
        return null;
    }

}
