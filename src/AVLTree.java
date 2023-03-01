public class AVLTree {
    private class AVLNode {
        public AVLNode left;
        public AVLNode right;
        public int value;
        public int height;

        public AVLNode(int input) {
            value = input;
            height = 0;
        }
    }
    
    private AVLNode root;

    public void insert(int input) {
        root = insert(root, input);
    }

    private AVLNode insert(AVLNode node, int input) {
        if (node == null) return new AVLNode(input);

        if (input > node.value)
            node.right = insert(node.right, input);
        else 
            node.left = insert(node.left, input);

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return balance(node);
    }

    private AVLNode balance(AVLNode node) {
        if (isLeftHeavy(node)) {
            if (balanceFactor(node.left) < 0)
                node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        else if (isRightHeavy(node)) {
            if (balanceFactor(node.right) > 0)
                node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode leftRotate(AVLNode node) {
        var newRoot = node.right;
        
        node.right = newRoot.left;
        newRoot.left = node;

        node.height = getHeight(node);
        newRoot.height = getHeight(newRoot);

        return newRoot;
    }

    private AVLNode rightRotate(AVLNode node) {
        var newRoot = node.left;

        node.left = newRoot.right;
        newRoot.right = node;

        node.height = getHeight(node);
        newRoot.height = getHeight(newRoot);

        return newRoot;
    }

    private boolean isLeftHeavy(AVLNode node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(AVLNode node) {
        return balanceFactor(node) < -1;
    }

    private int balanceFactor(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }

    private int getHeight(AVLNode node) {
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(AVLNode node) {
        if (node == null)
            return true;

        if (isLeftHeavy(node) || isRightHeavy(node))
            return false;
        
        return isBalanced(node.left) && isBalanced(node.right);
    }
}
