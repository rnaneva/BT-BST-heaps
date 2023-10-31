package solutions;

import interfaces.Decrease;
import interfaces.Heap;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MaxHeapTest  {
    private MaxHeap<Integer> maxHeap;

    @Before
    public void setUp() {
        this.maxHeap = new MaxHeap<>();
        List<Integer> elements = new ArrayList<>(List.of(15, 25, 6, 9, 5, 8, 17, 16));
        for (Integer element : elements) {
            this.maxHeap.add(element);
        }
    }

    @Test
    public void testHeapifyUpAddOne() {
        MaxHeap<Integer> integerHeap = new MaxHeap<>();
        integerHeap.add(13);
        assertEquals(Integer.valueOf(13), integerHeap.peek());
    }

    @Test
    public void testHeapifyUpAddMany() {
        assertEquals(Integer.valueOf(25), maxHeap.peek());
    }

    @Test
    public void testSizeShouldBeCorrect() {
        assertEquals(8, this.maxHeap.size());
    }

//    15, 25, 6, 9, 5, 8, 17, 16

//       25
//    16   17
//  15  5   6   8
// 9
//    25, 16, 17, 15, 5, 6, 8
//       9
//    16      8
//  15  5   6
//
    @Test
    public void testPoll(){
        Integer poll = maxHeap.poll();
        assertEquals(Integer.valueOf(25), poll);
        assertEquals(7, maxHeap.size());
        assertEquals( Integer.valueOf(17),maxHeap.peek());
        maxHeap.poll();
        assertEquals( Integer.valueOf(16),maxHeap.peek());
        maxHeap.poll();
        assertEquals( Integer.valueOf(15),maxHeap.peek());
        maxHeap.poll();
        assertEquals( Integer.valueOf(9),maxHeap.peek());



    }


}