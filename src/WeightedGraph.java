import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.PriorityQueue;

public class WeightedGraph {
    private class Node {
        private String label;
        private ArrayList<Edge> edges = new ArrayList<>();
        
        Node(String label) {
            this.label = label;
        }

        private void addEdge(Node to, int weight) {
            edges.add(new Edge(this, to, weight));
        }

        private Edge[] getEdges() {
            return edges.toArray(new Edge[0]);
        }

        @Override
        public String toString() {
            return this.label;
        }
    }

    private class Edge {
        private Node from;
        private Node to;
        private int weight;

        Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to;
        }
    }

    Map<String, Node> nodes = new HashMap<>();

    public void addNode(String label) {
        var node = new Node(label);

        nodes.putIfAbsent(label, node);
    }

    public void addEdge(String from, String to, int weight) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();
        
        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public void print() {
        for (var node : nodes.values()) {
            var neighbors = node.getEdges();
            if (neighbors.length != 0)
                System.out.println(node + " is connected with " + neighbors);
        }
    }

    private class NodeEntry {
        private Node node;
        private int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    public int getShortestDistance(String from, String to) {
        var fromNode = nodes.get(from);
        Set<Node> visited = new HashSet<>();
        Map<Node, Integer> distances = new HashMap<>();
        Map<Node, Node> previousNode = new HashMap<>();
        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(
            Comparator.comparingInt(ne -> ne.priority)
        );
        
        for (var node : nodes.values())
            distances.put(node, Integer.MAX_VALUE);
        
        queue.add(new NodeEntry(fromNode, 0));
        distances.replace(fromNode, 0);

        while (!queue.isEmpty()) {
            var current = queue.remove().node;

            visited.add(current);

            for (Edge edge : current.getEdges()) {
                var neighbor = edge.to;
                var distance = edge.weight + distances.get(current);
                
                if (visited.contains(neighbor))
                    continue;

                if (distances.get(neighbor) > distance) {
                    distances.replace(neighbor, distance);
                    queue.add(new NodeEntry(neighbor, distances.get(neighbor)));
                    previousNode.put(neighbor, current);
                }
            }
        }

        return distances.get(nodes.get(to));
    }

    public boolean hasCycle() {
        Set<Node> visited = new HashSet<>();

        for (var node : nodes.values()) {
            if (hasCycle(node, null, visited)) return true;
        }

        return false;
    }

    private boolean hasCycle(Node node, Node parent, Set<Node> visited) {
        if (parent != node && visited.contains(node))
            return true;

        visited.add(node);

        for (Edge edge : node.getEdges()) {
            if (edge.to == parent) continue;
            if (hasCycle(edge.to, node, visited)) return true;
        }

        return false;
    }
}
