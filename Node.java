
class Node<E>{
    private E data;
    private Node<E> next, prev;
    
    public Node(E newObj){
        data = (E) newObj;
        next = null;
        prev = null;
    } 
    
    @SuppressWarnings("unchecked")
    public E get(){
        E e = (E) data;
        return (E) e;
    }
    
    public Node<E> next(){
        return (Node<E>) next;
    }
    
    public Node<E> prev(){
        return (Node<E>) prev;
    }
    

    public void setNext(Node<E> nextObj){
        next = nextObj;
    }

    public void setPrev(Node<E> prevObj){
        prev = prevObj;
    } 

    public void set(E newData){
        data = newData;
    }

    public String toString(){
        return data.toString();
    }

}
