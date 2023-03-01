import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
    private class Node {
        public char value;
        public HashMap<Character, Node> children = new HashMap();
        public boolean isEndOfWord;

        Node(char ch) {
            this.value = ch;
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        public boolean hasChildren() {
            return children.values().toArray().length > 0;
        }

        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        public Node getChild(char ch) {
            return children.get(ch);
        }

        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }

        public void removeChild(char ch) {
            children.remove(ch);
        }

        @Override
        public String toString() {
            return "value=" + value;
        }
    }

    private Node root = new Node(' ');

    public void insert(String word) {
        var current = root;

        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);

            current = current.getChild(ch);
        }

        current.isEndOfWord = true;
    }

    public boolean contains(String word) {
        if (word == null)
            return false;

        var current = root;

        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                return false;
            
            current = current.getChild(ch);
        }

        return current.isEndOfWord;
    }

    public boolean recursiveContains(String word) {
        if (word == null)
            return false;

        return contains(word, root, 0);
    }

    private boolean contains(String word, Node node, int index) {
        if (node == null)
            return false;

        return index == word.length()
            ? node.isEndOfWord
            : contains(word, node.getChild(word.charAt(index)), index + 1);
    }

    public void remove(String word) {
        if (word == null)
            return;

        remove(word, root, 0);
    }

    private void remove(String word, Node node, int index) {
        if (word.length() == index) {
            node.isEndOfWord = false;
            return;
        }

        var ch = word.charAt(index);
        var child = node.getChild(ch);

        if (child == null)
            return;
        
        remove(word, node.getChild(word.charAt(index)), index + 1);

        if (!child.hasChildren() && !child.isEndOfWord)
            node.removeChild(ch);
    }

    public List<String> findWords(String prefix) {
        List<String> list = new ArrayList<>();
        var node = findLastNodeOf(prefix);

        findWords(prefix, node, list);

        return list;
    }

    private Node findLastNodeOf(String prefix) {
        if (prefix == null)
            return null;

        var node = root;

        for (char ch : prefix.toCharArray()) {
            node = node.getChild(ch);
        }

        return node;
    }

    private void findWords(String word, Node node, List<String> list) {
        if (node == null)
            return;

        if (node.isEndOfWord)
            list.add(word);

        for (Node child : node.getChildren())
            findWords(word + child.value, child, list);
    }

    public int countWords() {
        return countWords(0, root);
    }

    private int countWords(int counter, Node node) {
        for (Node child : node.getChildren())
            counter = countWords(child.isEndOfWord ? counter + 1 : counter, child);

        return counter;
    }

    // public String longestCommonPrefix(String[] words) {
    //     for (var word : words)
    //         for (char ch : word.toCharArray())
                
    // }
}
