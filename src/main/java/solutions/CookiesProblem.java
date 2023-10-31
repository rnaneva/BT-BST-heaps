package solutions;

import java.util.*;

public class CookiesProblem {
    public Integer solve(int k, int[] cookies) {

        int repetitions = 0;
        int[] sorted = Arrays.stream(cookies).sorted().toArray();

        int allCookiesSweetness = Arrays.stream(cookies).sum();
        Deque<Integer> cookiesDeque = new ArrayDeque<>();
        Arrays.stream(sorted).forEach(cookiesDeque::add);

        while(k < allCookiesSweetness ){



        }


        return -1;
    }
}
