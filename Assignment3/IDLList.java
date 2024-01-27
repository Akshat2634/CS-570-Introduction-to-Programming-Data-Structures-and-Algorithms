import java.util.ArrayList;
import java.util.NoSuchElementException;

public class IDLList<E>{

    private class Node<E>{
        E data;
        Node<E> next;
        Node<E> prev;
        Node(E elem){
            this.data = elem;
        }
        Node(E elem, Node<E> prev, Node<E> next){
            this.data = elem;
            this.next=next;
            this.prev=prev;
        }

    }
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private ArrayList<Node<E>> indices;

    public IDLList(){ //Creates an empty double-linked list
        this.indices = new ArrayList<Node<E>>();
        this.head= null;
        this.tail=null;
        this.size=0;
    }

    public boolean add(int index, E elem){ //insert element at position index

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid index");
        }

        Node<E> newData = new Node<E>(elem);
        if(head==null){
            head=newData;
            tail=newData;
            size++;
            indices.add(index,newData);
            return true;
        }else if(index==0){
            return this.add(elem); // insertion at head
        }
        else if(index==size){
            return this.append(elem); //insertion at tail
        }
        else{
            Node<E> nodeAtIndex = indices.get(index);
            newData.prev = nodeAtIndex.prev;
            newData.next = nodeAtIndex;
            nodeAtIndex.prev.next = newData;
            nodeAtIndex.prev = newData;
            size++;
            indices.add(index, newData);
            return true;
        }
    }
    public boolean add (E elem){ //insert at head
        Node<E> newData = new Node<E>(elem,null,head);
        newData.next = head;
        newData.prev=null;
        if(head!=null){
            head.prev=newData;
        }
        if(tail==null){
            tail=newData;
        }
        head=newData;
        size++;
        indices.add(0,newData);
        return true;
    }
    public boolean append (E elem){ // insert at tail

        Node<E> newData = new Node<E>(elem,tail,null);
        newData.prev = tail;
        newData.next=null;

        if(tail!=null){
            tail.next=newData;
        }
        if(head==null){
            head=newData;
        }
        tail=newData;
        size++;
        indices.add(size-1,newData);
        return true;
    }

    public E get (int index){ //Returns the object at position index from the head
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return this.indices.get(index).data;
    }

    public E getHead (){ //Returns the object at the head
        if (size==0)
        {
            throw new NoSuchElementException("Empty List");
        }
        return indices.get(0).data;
    }

    public E getLast (){ //Returns the object at the tail
        if (size == 0) {
            throw new NoSuchElementException("Empty List");
        }
        return indices.get(size - 1).data;
    }
    public int size(){ //Returns the list size
        return size;
    }

    public E remove() { // Removes and returns the element at the head
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> removedNode = head;

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        indices.remove(0);
        return removedNode.data;
    }

    public E removeLast (){ //Removes and returns the element at the tail
        Node<E> todel = indices.get(size-1);
        Node<E> prev = indices.get(size-2);
        prev.next=null;
        tail = prev;
        indices.remove(size-1);
        size--;
        return todel.data;
    }

    public E removeAt (int index){ //Removes and returns the element at the given index
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (size == 0) {
                throw new NoSuchElementException();
        }
        if (index == 0) { // Remove at head
            return remove();
        } else if (index == size - 1) { // Remove at tail
            return removeLast();
        } else {
            Node<E> todel = indices.get(index);
            todel.prev.next = todel.next;
            todel.next.prev = todel.prev;
            size--;
            indices.remove(index);
            return todel.data;
        }
    }
    public boolean remove (E elem){ //Removes the first occurrence of in the list and returns true
        if(head==null){
            throw new NoSuchElementException("List is empty");
        }
        Node<E> temp = head;
        int index=0;
        while(temp!=null){
            if(temp.data.equals(elem)){
                this.removeAt(index);
                return true;
            }
            temp=temp.next;
            index++;
        }

        return false;
    }
    public String toString(){ //Presents a string representation of the list

        if (head == null) {
            return "List: empty";
        }
        StringBuilder result = new StringBuilder("List: ");
        Node<E> current = head;
        while (current != null) {
            result.append(current.data).append(" ");
            current = current.next;
        }
        return result.toString();
    }

}
