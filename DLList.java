

class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    public boolean add(E data) {
        Node<E> newNode = new Node<>(data);
        Node<E> prevNode = tail.prev();

        prevNode.setNext(newNode);
        newNode.setPrev(prevNode);
        newNode.setNext(tail);
        tail.setPrev(newNode);

        size++;
        return true;
    }

    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> newNode = new Node<>(data);
        if (index == size) {
            add(data);
            return;
        }

        Node<E> current = getNode(index);
        Node<E> prevNode = current.prev();

        prevNode.setNext(newNode);
        newNode.setPrev(prevNode);
        newNode.setNext(current);
        current.setPrev(newNode);

        size++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> rem = getNode(index);
        Node<E> before = rem.prev();
        Node<E> after = rem.next();

        before.setNext(after);
        after.setPrev(before);

        rem.setNext(null);
        rem.setPrev(null);

        size--;
        return rem.get();
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getNode(index).get();
    }


    @SuppressWarnings("unchecked")
    public boolean remove(E e){
        @SuppressWarnings("rawtypes")
        Node<E> current = head.next();
        for(int i=0; i<size; i++) {
            if (current.get().equals(e)) {
                Node<E> before = current.prev();
                Node<E> after = current.next();
                before.setNext(after);
                after.setPrev(before);
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
        // Node before = head;
        // Node after = head.next().next();
        // while(after != tail){
        //     if(before.next().get().equals(e)){
        //         System.out.println(before.next().get());
        //         System.out.println(e);
        //         before.setNext(after);
        //         after.setPrev(before);
        //         System.out.println("removed");
        //         return true;
        //     }
        //     before = before.next();
        //     after = after.next();
        // }
        // return false;
    }

    // public void remove(Object remObj) {
    //     Node<E> current = head.next();

    //     while (current != tail) {
    //         if (current.get().equals(remObj)) {
    //             Node<E> before = current.prev();
    //             Node<E> after = current.next();

    //             before.setNext(after);
    //             after.setPrev(before);

    //             current.setNext(null);
    //             current.setPrev(null);

    //             size--;
    //             System.out.println("Removed: " + remObj + ". Size is now: " + size);
    //             return;
    //         }
    //         current = current.next();
    //     }
    // }

    public Node<E> getNode(int index) {
        Node<E> current = head.next();
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        getNode(index).set(element);
        return get(index);
    }

    public String toString() {
        Node<E> current = head.next();
        String output = "";

        for (int i = 0; i < size; i++){
            output+=current.get() + "\n ";
            current = current.next();
        }

        return output;
        
        // StringBuilder output = new StringBuilder();
        // Node<E> current = head.next();
        // int index = 1;
        // while (current != tail) {
        //     output.append(index).append(". ").append(current.get().toString()).append("\n");
        //     current = current.next();
        //     index++;
        // }
        // return output.toString();
    }
}
