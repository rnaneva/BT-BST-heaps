package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {
    private List<E> elements;

    public MinHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        elements.add(element);
        heapifyUp(this.size() - 1);
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean indexIsValid(int index) {
        return index >= 0 && index < this.size();
    }

    private void heapifyUp(int index) {

        while (hasParent(index) && isGreater(getParentIndex(index), index)) {

            Collections.swap(this.elements, getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private boolean isGreater(int parentIndex, int childIndex) {
        E parent = this.elements.get(parentIndex);
        E child = this.elements.get(childIndex);

        return parent.compareTo(child) > 0;

    }

    private boolean hasParent(int index) {
        int parentIndex = getParentIndex(index);
        return indexIsValid(parentIndex);
    }

    @Override
    public E peek() {
        if (this.size() == 0) {
            throw new NullPointerException();
        }
        return this.elements.get(0);
    }

    @Override
    public E poll() {

        E elementToRemove = peek();
        Collections.swap(this.elements, 0, size() - 1);
        this.elements.remove(size() - 1);
        heapifyDown(0);

        return elementToRemove;
    }

    private void heapifyDown(int index) {

        int childIndex = getLeftChildIndex(index);
        if (hasLeftChild(index) && isGreater(index, childIndex)) {
            if (hasRightChild(index) && isGreater(childIndex, childIndex + 1)) {
                childIndex = childIndex + 1;
            }
            Collections.swap(this.elements, index, childIndex);
            index = childIndex;
            heapifyDown(index);
        }

        childIndex = getRightChildIndex(index);
        if (hasRightChild(index) && isGreater(index, childIndex)) {
            if (isGreater(childIndex, childIndex - 1)) {
                childIndex = childIndex - 1;
            }
            Collections.swap(this.elements, index, childIndex);
            index = childIndex;
            heapifyDown(index);
        }


    }


    private boolean hasRightChild(int index) {
        return indexIsValid(getRightChildIndex(index));
    }

    private boolean hasLeftChild(int index) {
        return indexIsValid(getLeftChildIndex(index));
    }


    private int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    @Override
    public void decrease(E element) {
        E removed = removeElement(element);

// set new lower generic value todo
        add(removed);

    }

    public E removeElement(E element) {

        int index = elements.indexOf(element);

        Collections.swap(this.elements, index, size() - 1);
        this.elements.remove(size() - 1);
        heapifyDown(index);

        return element;
    }


}
