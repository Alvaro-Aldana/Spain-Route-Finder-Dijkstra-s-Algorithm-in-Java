public class MyHashMap<K,V> {
    // Instance Variables
    private Object[] hashArray;
    private int size;
    private MyHashSet<K> keySet;
    // Constructor
    public MyHashMap() {
        hashArray = new Object[20000];
        size = 0;
        keySet = new MyHashSet<>();
    }
    // Methods
    public V put(K k, V v) {
        int hashCode = k.hashCode(); 
        if (hashArray[hashCode]==null) {
            size++;
        }
        V returnV = (V) hashArray[hashCode];
        hashArray[hashCode] = v;
        keySet.add(k);
        // System.out.println("key: "+k+"\t hashcode: "+k.hashCode());
        // System.out.println("hashArray[hashCode]: "+hashArray[hashCode]);
        // System.out.println("value: "+v);
        return returnV;
    }
    public V get(Object o) {
        // System.out.println("o: "+o);
        // System.out.println("hashArray[o.hashCode()]: "+hashArray[o.hashCode()]);
        // System.out.println("o.hashCode(): "+o.hashCode());
        return (V) hashArray[o.hashCode()];

    }
    public V remove(Object o) {
        V removeV = (V) hashArray[o.hashCode()];
        if (removeV!=null) {
            size--;
        }
        hashArray[o.hashCode()]= null;
        keySet.remove((K) o);
        return removeV;
    }
    public void clear() {
        hashArray = new Object[20000];
        size = 0;
        keySet = new MyHashSet<>();
        // for (int i=0; i<keySet.toDLList().size(); i++) {
        //     hashArray[(int)(keySet.toDLList().get(i))] = null;
        //      keySet.remove(keySet.toDLList().get(i));
        // }
    }
    public DLList<K> toList() {
        return keySet.toDLList();
    }
    public int size() {
        return size;
    }
    public MyHashSet<K> keySet() {
        return keySet;
    }
}

