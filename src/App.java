import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        
    }

    public static void searches(String[] args) {
        int[] numbers = { 0, 1, 2, 3, 4, 5, 7, 8, 9 };

        System.out.println(Searching.linearSearch(numbers, 3));
        System.out.println(Searching.binarySearch(numbers, 3));
        System.out.println(Searching.binarySearchIterative(numbers, 3));
        System.out.println(Searching.ternarySearch(numbers, 3));
        System.out.println(Searching.jumpSearch(numbers, 3));
        System.out.println(Searching.exponentialSearch(numbers, 9));
    }

    public static void sorts(String[] args) {
        int[] numbers = { 7, 1, 3, 4, 2, 6, 3 };

        System.out.println(Arrays.toString(Sorting.bubbleSort(numbers)));
        System.out.println(Arrays.toString(Sorting.selectionSort(numbers)));
        System.out.println(Arrays.toString(Sorting.insertionSort(numbers)));
        System.out.println(Arrays.toString(Sorting.mergeSort(numbers)));
        System.out.println(Arrays.toString(Sorting.quickSort(numbers)));
        System.out.println(Arrays.toString(Sorting.countSort(numbers)));
        System.out.println(Arrays.toString(Sorting.bucketSort(numbers, 3)));
    }

    public static void getMinimumSpanningTree() {
        var graph = new WeightedGraph();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addEdge("A", "B", 3);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "E", 6);
        graph.addEdge("D", "C", 5);

        graph.getMinimumSpanningTree().print();
    }

    public static Character getFirstNonRepeatedCharacter(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (var c : str.toCharArray()) {
            int value = map.containsKey(c) ? map.get(c) : 0;
            map.put(c, ++value);
        }
        for (var c : str.toCharArray())
            if (map.get(c) == 1) return c;
        return null;
    }

    public static Character getFirstRepeatedCharacter(String str) {
        Set<Character> set = new HashSet<>();
        for (var c : str.toCharArray()) {
            if (set.contains(c)) return c;
            set.add(c);
        }
        return null;
    }

    private static void sortWithHeap() {
        int[] numbers = { 1, 5, 3, 4, 3, 3, 65, 2, 53 };
        var heap = new Heap();

        for (int i : numbers) {
            heap.insert(i);
        }

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = heap.remove();
        }

        System.out.println(Arrays.toString(numbers));
    }
}
