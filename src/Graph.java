import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
    private class Node {
        String label;

        Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        var node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        
        if (fromNode == null)
            throw new IllegalArgumentException();
            
        Node toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeNode(String label) {
        var node = nodes.get(label);

        if (node == null)
            return;

        for (var source : adjacencyList.keySet())
            adjacencyList.get(source).remove(node);

        nodes.remove(label);
        adjacencyList.remove(node);
    }

    public void removeEdge(String from, String to) {
        var toNode = nodes.get(to);
        var fromNode = nodes.get(from);

        if (toNode == null || fromNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void print() {
        for (var source : adjacencyList.keySet()) {
            var targets = adjacencyList.get(source);
            if (!targets.isEmpty())
                System.out.println(source + " is connected with " + targets);
        }
    }

    public void depthFirstTraversalRecursive(String label) {
        var node = nodes.get(label);

        if (node == null)
            return;

        traverseDeep(node, new HashSet<>());
    }

    private void traverseDeep(Node node, Set<Node> visited) {
        System.out.println(node);
        visited.add(node);

        for (var neighborNode : adjacencyList.get(node))
            if (!visited.contains(node))
                traverseDeep(neighborNode, visited);
    }

    public void depthFirstTraversalIterative(String label) {
        var root = nodes.get(label);

        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        var visited = new HashSet<Node>();

        stack.push(root);

        while (!stack.isEmpty()) {
            var current = stack.pop();

            visit(current, visited);

            for (var node : adjacencyList.get(current))
                if (!visited.contains(node))
                    stack.push(node);
        }
    }

    private void visit(Node node, Set<Node> visited) {
        System.out.println(node);
        visited.add(node);
    }

    public void breadthFirstTraversal(String label) {
        var root = nodes.get(label);

        if (root == null) return;

        Queue<Node> queue = new ArrayDeque<>();
        var visited = new HashSet<Node>();

        queue.add(root);

        while (!queue.isEmpty()) {
            var current = queue.remove();

            if (visited.contains(current))
                continue;

            visit(current, visited);

            for (var node : adjacencyList.get(current))
                if (!visited.contains(node))
                    queue.add(node);
        }
    }

    public List<String> topologicalSort() {
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        for (var node : adjacencyList.keySet()) {
            traverseDeepSort(node, visited, stack);
        }

        List<String> list = new ArrayList<>();

        while (!stack.isEmpty())
            list.add(stack.pop().label);

        return list;
    }

    private void traverseDeepSort(Node node, Set<Node> visited, Stack<Node> stack) {
        if (visited.contains(node))
            return;

        visited.add(node);

        for (Node neighbour : adjacencyList.get(node))
            traverseDeepSort(neighbour, visited, stack);

        stack.add(node);
    }

    public boolean hasCycle() {
        Set<Node> all = new HashSet<>(nodes.values());
        Set<Node> visited = new HashSet<>();
        Set<Node> visiting = new HashSet<>();

        while (!all.isEmpty()) {
            var node = all.iterator().next();
            if (hasCycle(node, all, visited, visiting)) return true;
        }

        return false;
    }

    private boolean hasCycle(Node node, Set<Node> all, Set<Node> visited, Set<Node> visiting) {
        all.remove(node);
        visiting.add(node);
        
        for (Node neighbour : adjacencyList.get(node)) {
            if (visited.contains(neighbour))
                continue;

            if (visiting.contains(node))
                return true;

            if (hasCycle(neighbour, all, visited, visiting)) return true;
        }

        visited.add(node);
        visiting.remove(node);

        return false;
    }
}
