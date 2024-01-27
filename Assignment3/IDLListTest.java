public class IDLListTest {
    public static void main(String[] args) {
        IDLList<Integer> list = new IDLList <Integer> ();
        System.out.println(list.size());
        list.add(5);
        list.add(6);
        list.add(3);
        list.add(4);
        list.removeLast();
        list.removeAt(0);
        list.remove(3);
        list.add(1,1);
        list.append(5);
        list.add(0,88);
        list.add(0,77);
        list.add(6);
        list.add(7);
        list.append(4);
        list.add(1,66);
        list.add(2,68);
        System.out.println(list.toString());
        System.out.println("Element removed at head: "+list.remove()+", and size is "+ list.size());
        System.out.println("Element removed at tail: "+list.removeLast()+", and size is "+ list.size());
        System.out.println(list.toString());
        System.out.println("Element removed: "+list.remove(6)+", and size is "+ list.size());
        System.out.println(list.toString());
        list.append(5);
        list.add(0,88);
        list.add(0,77);
        list.add(6);
        list.add(7);
        list.add(9,22);
        System.out.println(list.toString());
        System.out.println("Element at head: "+list.getHead());
        System.out.println("Element at tail: "+list.getLast());
        System.out.println("Element at index: "+list.get(6));
        System.out.println(list.toString());
    }
}
