import java.util.ArrayList;
import java.util.List;

public class Tree {
    private class Node {
        public int value;
        public Node leftChild;
        public Node rightChild;
        
        public Node(int v) {
            value = v;
        }
    }

    private Node root = null;

    public void insert(int input) {
        if (root == null) root = new Node(input);

        var n = find(input);
        if (n.value < input) n.rightChild = new Node(input);
        else if (n.value > input) n.leftChild = new Node(input);
    }

    public Node find(int input) {
        var current = root;
        Node prev = null;
        
        while (current != null) {
            prev = current;
            current = current.value < input
                    ? current.rightChild
                    : current.leftChild;
        }

        return prev;
    }

    public void setChild(int value, int input) {
        var node = find(value);

        node.leftChild = new Node(input);
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root) {
        if (root == null)
            return;

        System.out.println(root.value);
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node root) {
        if (root == null)
            return;

        traverseInOrder(root.leftChild);
        System.out.println(root.value);
        traverseInOrder(root.rightChild);
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root) {
        if (root == null)
            return;

        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.println(root.value);
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null)
            return -1;

        if (root.rightChild == null && root.leftChild == null)
            return 0;

        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    public boolean equalsTo(Tree foreign) {
        if (foreign == null) return false;

        return equals(root, foreign.root);
    }

    private boolean equals(Node self, Node foreign) {
        if (self == null && foreign == null) return true;

        if (self != null && foreign != null) 
            return self.value == foreign.value
                && equals(self.leftChild, foreign.leftChild)
                && equals(self.rightChild, foreign.rightChild);

        return false;
    }

    public boolean isValidTree() {
        if (root == null) throw new IllegalStateException();

        return validateNode(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validateNode(Node node, Integer minValue, Integer maxValue) {
        if (node == null) return true;

        return node.value > minValue && node.value < maxValue
            && validateNode(node.leftChild, minValue, node.value)
            && validateNode(node.rightChild, node.value, maxValue);
    }

    public void printValuesAtLevel(int level) {
        int levelValue = level;

        if (levelValue < 0) levelValue = 0;
        
        printValuesAtLevel(root, levelValue);
    }

    private void printValuesAtLevel(Node node, int distance) {
        if (node == null) return;

        if (distance == 0) {
            System.out.println(node.value);
            return;
        }

        printValuesAtLevel(node.leftChild, distance - 1);
        printValuesAtLevel(node.rightChild, distance - 1);
    }

    public int size() {
        return getNodeSize(root);
    }

    private int getNodeSize(Node node) {
        if (node == null) return 0;

        return getNodeSize(node.leftChild) + 1 + getNodeSize(node.rightChild);
    }

    public int leafSize() {
        if (root == null) return 0;

        return countLeaf(root);
    }

    private int countLeaf(Node node) {
        if (node == null) return 0;
        if (isLeaf(node)) return 1;

        return countLeaf(node.leftChild) + countLeaf(node.rightChild);
    }

    private boolean isLeaf(Node node) {
        return node.rightChild == null && node.leftChild == null;
    }

    public int maxOnInvalidTree() {
        return getMax(root);
    }

    private int getMax(Node node) {
        if (isLeaf(node)) return node.value;

        if (node.leftChild == null) return Math.max(getMax(node.rightChild), node.value);

        if (node.rightChild == null) return Math.max(getMax(node.leftChild), node.value);

        return Math.max(Math.max(getMax(node.leftChild), getMax(node.rightChild)), node.value);
    }

    public int maxOnValidTree() {
        return getMostRightChild(root);
    }

    private int getMostRightChild(Node node) {
        if (node.rightChild == null) return node.value;

        return getMostRightChild(node.rightChild);
    }

    public boolean contains(int input) {
        return valueEquals(root, input);
    }

    private boolean valueEquals(Node node, int input) {
        if (node == null) return false;

        if (node.value == input) return true;

        Node nodeToFindIn = input > node.value ? node.rightChild : node.leftChild;

        return valueEquals(nodeToFindIn, input);
    }

    public boolean areSiblings(int first, int second) {
        return areSiblings(root, first, second);
    }

    private boolean areSiblings(Node node, int first, int second) {
        if (node == null) return false;
        if (isLeaf(node)) return false;

        if (
            (node.leftChild.value == first && node.rightChild.value == second) ||
            (node.leftChild.value == second && node.rightChild.value == first)
        )
            return true;

        return areSiblings(node.leftChild, first, second) || areSiblings(node.rightChild, first, second);
    }

    public List<Integer> getAncestors(int input) {
        return findAncestor(root, input);
    }

    private List<Integer> findAncestor(Node node, int input) {
        List<Integer> ancestors = new ArrayList<>();

        if (node.value == input) {
            ancestors.add(input);

            return ancestors;
        }

        var nextChildNode = input > node.value ? node.rightChild : node.leftChild;

        ancestors = findAncestor(nextChildNode, input);

        ancestors.add(node.value);
        
        return ancestors;
    }
}
