import java.util.*;

interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();
    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();
    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
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
//        List<NestedInteger> nestedList = new ArrayList<>();
//        List<NestedInteger> first = new ArrayList<>();
//        first.add(new NestedInteger(1));
//        first.add(new NestedInteger(2));
//        nestedList.add(new NestedInteger(first));
//        nestedList.add(new NestedInteger(3));
//        nestedList.add(new NestedInteger(4));
//
//        NestedIterator0 i = new NestedIterator0(nestedList);
//        if (i.hasNext()) {
//            System.out.print(i.next() + " ");
//        }
//        if (i.hasNext()) {
//            System.out.print(i.next() + " ");
//        }
//
//        first.add(new NestedInteger(7));
//        nestedList.set(0, new NestedInteger(first));
//        nestedList.add(1, new NestedInteger(5));
//        nestedList.remove(3);
//        nestedList.set(2, new NestedInteger(6));
//
//        while (i.hasNext()) {
//            System.out.print(i.next() + " ");
//        }
//        System.out.println();
    }
}