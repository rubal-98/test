import java.util.*;

public class Solution {

    public static int minReplacements(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : arr) {
            if (freq.containsKey(x)) {
                freq.put(x, freq.get(x) + 1);
            } else {
                freq.put(x, 1);
            }
        }

        int distinct = freq.size();
        if (distinct <= k) return 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(freq.values());

        int replacements = 0;
        int toRemove = distinct - k;
        while (toRemove-- > 0) {
            replacements += minHeap.poll();
        }
        return replacements;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 2, 3, 3, 4, 5};
        System.out.println("Test 1: " + minReplacements(arr1, 3)); // Expected: 2

        int[] arr2 = {1, 1, 2, 2, 3, 3};
        System.out.println("Test 2: " + minReplacements(arr2, 3)); // Expected: 0

        int[] arr3 = {1, 1, 2, 2};
        System.out.println("Test 3: " + minReplacements(arr3, 3)); // Expected: 0

        int[] arr4 = {1, 1, 1, 1, 2, 2, 3};
        System.out.println("Test 4: " + minReplacements(arr4, 1)); // Expected: 3

        int[] arr5 = {5, 5, 5, 5};
        System.out.println("Test 5: " + minReplacements(arr5, 2)); // Expected: 0

        int[] arr6 = {1, 2, 3, 4};
        System.out.println("Test 6: " + minReplacements(arr6, 2)); // Expected: 2
    }
}
