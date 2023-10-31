package solutions;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> {

    private final List<E> elements;

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
        while (hasParent(index) && isLess(getParentIndex(index), index)) {
            int parentIndex = getParentIndex(index);
            Collections.swap(this.elements, index, parentIndex);
            index = parentIndex;
        }
    }

    private boolean isLess(int parentIndex, int childIndex) {
        E parent = this.elements.get(parentIndex);
        E child = this.elements.get(childIndex);
        return parent.compareTo(child) < 0;
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
        if(isValidIndex(parentIndex)){
            return parentIndex;
        }
        throw new IndexOutOfBoundsException();
    }



    private boolean isValidIndex(int index){
        return index >= 0 && index < this.size();
    }

    public E peek() {
        if(this.elements.isEmpty()){
            throw new NullPointerException();
        }
        return this.elements.get(0);
    }


    public E poll() {

        E elementToRemove = peek();
        Collections.swap(this.elements, 0, size() - 1);
        this.elements.remove(size() - 1);
        heapifyDown(0);

        return elementToRemove;
    }

    private void heapifyDown(int index) {

        int childIndex = getLeftChildIndex(index);
        if (hasLeftChild(index) && isLess(index, childIndex)) {
            if(hasRightChild(index) && isLess(childIndex, childIndex + 1)){
              childIndex = childIndex + 1;
            }
            Collections.swap(this.elements, index, childIndex);
            index = childIndex;
            heapifyDown(index);
        }

        childIndex = getRightChildIndex(index);
        if (hasRightChild(index) && isLess(index, childIndex)) {
            if(isLess(childIndex, childIndex - 1)){
                childIndex = childIndex - 1;
            }
            Collections.swap(this.elements, index, childIndex);
            index = childIndex;
            heapifyDown(index);
        }



    }


    private int getLeftChildIndex(int parentIndex) {
       return parentIndex * 2 + 1;

    }

    private int getRightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private boolean hasRightChild(int index) {
        return isValidIndex(getRightChildIndex(index));
    }

    private boolean hasLeftChild(int index) {
        return isValidIndex(getLeftChildIndex(index));
    }




}
