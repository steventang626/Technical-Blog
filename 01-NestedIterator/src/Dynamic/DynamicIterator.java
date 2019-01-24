package Dynamic;

import java.util.*;

// Not fully dynamic, just can deal with set(), but remove() and add() can only be operated after the iterator cursor.
public class DynamicIterator implements Iterator<Integer> {
    int cursor;       // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such
    List<Integer> list;

    public DynamicIterator(List<Integer> integerList) {
        list = integerList;
    }

    public Integer next() {
        int i = cursor;
        if (i >= list.size())
            throw new NoSuchElementException();
        cursor = i + 1;
        return list.get(lastRet = i);
    }

    public boolean hasNext() {
        return cursor != list.size();
    }

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        DynamicIterator iter = new DynamicIterator(list);
        if (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        // 1
        if (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        // 2

        // Add an element to the original list
        list.add(2, 6);

        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        // 6
        // 3
    }
}
