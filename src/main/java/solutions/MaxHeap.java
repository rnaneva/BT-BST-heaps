package solutions;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> {

    private List<E> elements;

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    public int size() {
        return elements.size();
    }


    public void add(E element) {

        this.elements.add(element);

        heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int index) {
        while (hasParent(index) && isLess(getParent(index), elements.get(index))) {
            int parentIndex = getParentIndex(index);
            Collections.swap(this.elements, index, parentIndex);
            index = parentIndex;
        }
    }

    private boolean isLess(E parent, E element) {
        return parent.compareTo(element) < 0;
    }

    private boolean hasParent(int index) {
        E parent = getParent(index);
       return parent != null;
    }

    private E getParent (int index){
        int parentIndex = getParentIndex(index);
        return this.elements.get(parentIndex);
    }

    private int getParentIndex(int childIndex) {
        int parentIndex = (childIndex - 1) / 2;
        validateIndex(parentIndex);
        return parentIndex;
    }


    private void validateIndex(int index) {
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    public E peek() {
        if(this.elements.isEmpty()){
            throw new NullPointerException();
        }
        return this.elements.get(0);
    }



}
