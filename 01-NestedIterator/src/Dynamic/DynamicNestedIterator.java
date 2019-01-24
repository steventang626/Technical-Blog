package Dynamic;

import java.util.*;

interface NestedIntegerInterface {
    // @return true if this Dynamic.NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();
    // @return the single integer that this Dynamic.NestedInteger holds, if it holds a single integer
    // Return null if this Dynamic.NestedInteger holds a nested list
    public Integer getInteger();
    // @return the nested list that this Dynamic.NestedInteger holds, if it holds a nested list
    // Return null if this Dynamic.NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedInteger implements NestedIntegerInterface {
    private Integer i;
    private List<NestedInteger> list;

    public NestedInteger(Integer integer) {
        i = integer;
    }

    public NestedInteger(List<NestedInteger> listNestedInteger) {
        list = listNestedInteger;
    }

    public boolean isInteger(){
        return list == null;
    }

    public Integer getInteger() {
        if(isInteger()) {
            return i;
        } else {
            return null;
        }
    }

    public List<NestedInteger> getList() {
        if(isInteger()) {
            return null;
        } else {
            return list;
        }
    }
}

public class DynamicNestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;
    private List<NestedInteger> list;
    private int next_counter;

    public DynamicNestedIterator(List<NestedInteger> nestedList) {
        list = nestedList;
        stack = new Stack<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            stack.push(list.get(i));
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        next_counter++;
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if(stack.empty()) return false;
        stack.removeAllElements();
        for (int i = list.size() - 1; i >= 0; i--) {
            stack.push(list.get(i));
        }
        for(int i = 0; i < next_counter; i++) {
            next_helper();
        }
        return hasNext_helper();
    }

    public void next_helper() {
        if (hasNext_helper()) {
            stack.pop();
        }
    }

    public boolean hasNext_helper() {
        while(!stack.empty()) {
            NestedInteger top = stack.peek();
            if(top.isInteger()) return true;
            stack.pop();
            List<NestedInteger> list = top.getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
        return false;
    }

    public static void main(String args[]) {
        List<NestedInteger> nestedList = new ArrayList<>();
        List<NestedInteger> first = new ArrayList<>();
        first.add(new NestedInteger(1));
        first.add(new NestedInteger(2));
        nestedList.add(new NestedInteger(first));
        nestedList.add(new NestedInteger(3));
        nestedList.add(new NestedInteger(4));

        DynamicNestedIterator i = new DynamicNestedIterator(nestedList);
        if (i.hasNext()) {
            System.out.println(i.next());
        }
        // 1

        if (i.hasNext()) {
            System.out.println(i.next());
        }
        // 2

        // Before modification, [[1, 2], 3, 4]
        first.add(new NestedInteger(6));
        nestedList.set(0, new NestedInteger(first));
        nestedList.add(1, new NestedInteger(5));
        nestedList.remove(3);
        // After modification, [[1, 2, 6], 5, 3]

        while (i.hasNext()) {
            System.out.println(i.next());
        }
        // 6
        // 5
        // 3
    }
}