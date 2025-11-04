
class MyHashSet<E> {
    //Instance Variables.
    private Object[] hashArray;
    private DLList<E> tempDLList;
    //Constructor(s)
    public MyHashSet() {
        hashArray = new Object[20000];
        tempDLList = new DLList<E>();
    }
    // Method(s)
    public boolean add(E e) {
        int location = e.hashCode();
        if( hashArray[location] == null) {
            hashArray[location] = e;
            tempDLList.add(e);
            return true;
        } else {
            
            return false;
        }

    }
    public void clear() {
        for (int i=0; i<hashArray.length; i++) {
            tempDLList.remove((E)(hashArray[i]));
            hashArray[i]=null;
            
        }
    }
    public boolean contains(Object o) {
        if (hashArray[o.hashCode()]==null) {
                    return false;
        }

        // for (int i=0; i<hashArray.length; i++) {
        //     if (o.hashCode()==hashArray[i].hashCode()) {
        //         return false;
        //     }
        // }   
        return true;   
    }
    public boolean remove(E o) {
        if(hashArray[o.hashCode()] != null) {
            System.out.println("REMOVING: "+o);
            hashArray[o.hashCode()] = null;
            tempDLList.remove(o);
            System.out.println("list: "+this.toString());
            return true;
            
        }
        //System.out.println("didnt remove: "+o+", "+o.hashCode());
        return false;
    }
    public int size() {
        int size = tempDLList.size();

        // for (int i=0; i<hashArray.length; i++) {
        //     if(hashArray[i] != null) {
        //         size++;
        //     }
        // }
        return size;
    }
    public DLList<E> toDLList() {
        return tempDLList;
    }

    public String toString(){
        return tempDLList.toString();
    }
}

