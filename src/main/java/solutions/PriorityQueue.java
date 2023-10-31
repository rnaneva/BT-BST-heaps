package solutions;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private List<E> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
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


    @Override
    public E peek() {
        if (size() > 0) {
            return this.elements.get(0);
        }
        return null;
    }

    @Override
    public E poll() {
        if (this.size() == 0) {
            throw new NullPointerException();
        }

        E element = this.elements.get(0);
        Collections.swap(this.elements, 0, this.size() - 1);
        this.elements.remove(this.size() - 1);

        heapifyDown(0);
        return element;
    }

    private void heapifyDown(int index) {

        while (index < this.size() / 2) {
            int childIndex = getChildOneIndex(index);

            if(childIndex >= size()){
                break;
            }
            if (childIndex + 1 < this.size() && less(childIndex, childIndex + 1)) {
                childIndex = childIndex + 1;
            }

            if(less(childIndex, index)){
                break;
            }

            Collections.swap(this.elements, index, childIndex);
            index = childIndex;

        }
    }

    private boolean less(int childIndex1, int childIndex2) {
        return this.elements.get(childIndex1).compareTo(this.elements.get(childIndex2)) < 0;
    }


    private int getChildOneIndex(int parentIndex) {
        int childIndex = parentIndex * 2 + 1;
        validateIndex(childIndex);
        return childIndex;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
