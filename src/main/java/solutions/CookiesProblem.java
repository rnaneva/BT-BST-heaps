package solutions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CookiesProblem {
    public Integer solve(int requiredSweetness, int[] cookies) {

        MinHeap<Integer> minHeap = new MinHeap<>();
        for (int cookie : cookies) {
            minHeap.add(cookie);
        }

        int repetitions = 0;
        while (minHeap.size() >= 2 && minHeap.peek() < requiredSweetness) {
            int mostSweet = minHeap.poll();
            int secondSweet = minHeap.poll();
            minHeap.add(mostSweet + 2 * secondSweet);
            repetitions++;
        }

        if (minHeap.peek() >= requiredSweetness) {
            return repetitions;
        }

        return -1;
    }
}
