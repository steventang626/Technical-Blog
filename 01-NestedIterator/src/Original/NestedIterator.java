package Original;

import java.util.*;

interface NestedInteger {
    // @return true if this Dynamic.Original.NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();
    // @return the single integer that this Dynamic.Original.NestedInteger holds, if it holds a single integer
    // Return null if this Dynamic.Original.NestedInteger holds a nested list
    public Integer getInteger();
    // @return the nested list that this Dynamic.Original.NestedInteger holds, if it holds a nested list
    // Return null if this Dynamic.Original.NestedInteger holds a single integer
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
}