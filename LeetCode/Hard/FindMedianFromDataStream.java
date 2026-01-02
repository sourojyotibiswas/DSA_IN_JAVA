import java.util.*;

public class FindMedianFromDataStream {
    /**
     * Brute force/Naive solution:-
     * O(n log n) in sorting 
     */
    // static class MedianFinder {
    //     List<Integer> ls;

    //     public MedianFinder() {
    //         ls = new ArrayList<>();
    //     }

    //     public void addNum(int num) {
    //         ls.add(num);
    //     }

    //     public double findMedian() {
    //         Collections.sort(ls);
    //         int size = ls.size();

    //         if (size % 2 != 0)
    //             return ls.get(size / 2);
    //         else
    //             return (ls.get(size / 2) + ls.get(size / 2 - 1)) / 2.0;
    //     }
    // }

    /**
     * Using Max, Min heap:-
     * O(log n) in insertion + O(1) in median
     */

    static class MedianFinder {
        PriorityQueue<Integer> leftMaxHeap;
        PriorityQueue<Integer> rightMinHeap;
        public MedianFinder() {
            leftMaxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            rightMinHeap = new PriorityQueue<>();
        }
        
        public void addNum(int num) {
            if(leftMaxHeap.isEmpty() || num < leftMaxHeap.peek()) leftMaxHeap.offer(num);
            else rightMinHeap.offer(num);

            /**
            Either the leftMaxHeap will be 1 < rightMinHeap or both will be equal
            */

            if(leftMaxHeap.size() > rightMinHeap.size() + 1) rightMinHeap.offer(leftMaxHeap.poll());
            else if(leftMaxHeap.size() < rightMinHeap.size()) leftMaxHeap.offer(rightMinHeap.poll());
        }
        
        public double findMedian() {
            // even number of elements
            if(leftMaxHeap.size() == rightMinHeap.size()) return (double)(leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
            // odd number of elements
            else return (double)leftMaxHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder obj = null;

        // Simulating input
        String[] ops = {"MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"};
        int[][] values = {{}, {1}, {2}, {}, {3}, {}};

        List<Object> output = new ArrayList<>();

        for (int i = 0; i < ops.length; i++) {
            switch (ops[i]) {
                case "MedianFinder":
                    obj = new MedianFinder();
                    output.add(null);
                    break;

                case "addNum":
                    obj.addNum(values[i][0]);
                    output.add(null);
                    break;

                case "findMedian":
                    output.add(obj.findMedian());
                    break;
            }
        }

        System.out.println(output);
    }
}
