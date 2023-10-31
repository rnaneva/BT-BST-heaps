package solutions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CookiesProblem {
    public Integer solve(int requiredSweetness, int[] cookies) {

        class MinHeap {
            private List<Integer> cookiesMinHeap;

            public MinHeap() {
                this.cookiesMinHeap = new ArrayList<>();
                for (int cookie : cookies) {
                    cookiesMinHeap.add(cookie);
                }
            }


            public void add(Integer element) {
                cookiesMinHeap.add(element);
                heapifyUp(this.size() - 1);
            }

            private int getParentIndex(int childIndex) {
                return (childIndex - 1) / 2;
            }


            private void heapifyUp(int index) {

                while (hasParent(index) && isGreater(getParentIndex(index), index)) {

                    Collections.swap(this.cookiesMinHeap, getParentIndex(index), index);
                    index = getParentIndex(index);
                }
            }

            private boolean hasParent(int index) {
                int parentIndex = getParentIndex(index);
                return indexIsValid(parentIndex);
            }

            public  Integer peek() {
                if (cookiesMinHeap.size() == 0) {
                    throw new NullPointerException();
                }
                return cookiesMinHeap.get(0);
            }



            public Integer poll() {

                Integer elementToRemove = peek();
                Collections.swap(this.cookiesMinHeap, 0, size() - 1);
                this.cookiesMinHeap.remove(size() - 1);
                heapifyDown(0);

                return elementToRemove;
            }

            private void heapifyDown(int index) {

                int childIndex = getLeftChildIndex(index);
                if (hasLeftChild(index) && isGreater(index, childIndex)) {
                    if(hasRightChild(index) && isGreater(childIndex, childIndex + 1)){
                        childIndex = childIndex + 1;
                    }
                    Collections.swap(this.cookiesMinHeap, index, childIndex);
                    index = childIndex;
                    heapifyDown(index);
                }

                childIndex = getRightChildIndex(index);
                if (hasRightChild(index) && isGreater(index, childIndex)) {
                    if(isGreater(childIndex, childIndex - 1)){
                        childIndex = childIndex - 1;
                    }
                    Collections.swap(this.cookiesMinHeap, index, childIndex);
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

            private boolean indexIsValid(int index) {
                return index >= 0 && index < size();
            }


            private int getLeftChildIndex(int parentIndex) {
                return parentIndex * 2 + 1;
            }

            private int getRightChildIndex(int parentIndex) {
                return parentIndex * 2 + 2;
            }

            private boolean isGreater(int parentIndex, int childIndex) {
                Integer parent = this.cookiesMinHeap.get(parentIndex);
                Integer child = this.cookiesMinHeap.get(childIndex);

                return parent.compareTo(child) > 0;

            }

            private int size(){
                return this.cookiesMinHeap.size();
            }

        }


        int repetitions = 0;
        MinHeap minHeap = new MinHeap();
        while(minHeap.size() >= 2 && minHeap.peek() < requiredSweetness){
            int mostSweet = minHeap.poll();
            int secondSweet = minHeap.poll();
            minHeap.add(mostSweet + 2 * secondSweet);
            repetitions ++;
        }

        if(minHeap.peek() >= requiredSweetness){
            return repetitions;
        }

        return -1;
    }
}
