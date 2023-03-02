import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {
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
