package solutions;

import interfaces.Heap;
import model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MinHeapTest {
    private MinHeap<Product> minHeap;

    @Before
    public void setUp() {
        this.minHeap = new MinHeap<>();
        List<Integer> elements = new ArrayList<>(List.of(15, 25, 6, 9, 5, 8, 17, 16));
        for (Integer element : elements) {
            this.minHeap.add(new Product(element));
        }
    }


    @Test
    public void testHeapifyUpAddOne() {
        MinHeap<Product> heap = new MinHeap<>();
        heap.add(new Product(13));
        assertEquals(13, heap.peek().getPrice());
    }

    @Test
    public void testHeapifyUpAddMany() {
        assertEquals(5, minHeap.peek().getPrice());
    }

    @Test
    public void testSizeShouldBeCorrect() {
        assertEquals(8, this.minHeap.size());
    }

    @Test
    public void testDecreaseSingleElement() {
        MinHeap<Product> heap = new MinHeap<>();

        heap.add(new Product(3));

        heap.decrease(new Product(3));

        assertEquals(2, heap.peek().getPrice());
    }

    @Test
    public void testPoll() {

        Product poll = minHeap.poll();
        int price = poll.getPrice();
        assertEquals(5, price);
        assertEquals(7, minHeap.size());
        assertEquals(6, minHeap.peek().getPrice());
        minHeap.poll();
        assertEquals(8, minHeap.peek().getPrice());
        minHeap.poll();
        assertEquals(9, minHeap.peek().getPrice());
        minHeap.poll();
        assertEquals(15, minHeap.peek().getPrice());


    }
}



//    5 6 8 16 9 15 17 25


//         5
//     6      8
//  16  9   15  17
//25

