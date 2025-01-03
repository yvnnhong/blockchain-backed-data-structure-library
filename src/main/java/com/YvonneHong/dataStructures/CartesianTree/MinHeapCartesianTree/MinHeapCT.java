package com.YvonneHong.dataStructures.CartesianTree.MinHeapCartesianTree;

import com.YvonneHong.dataStructures.CartesianTree.CartesianTreeNode;

public class MinHeapCT {
    private CartesianTreeNode root;

    // Constructor
    public MinHeapCT() {
        this.root = null;
    }

    // Insert a new value into the MinHeap Cartesian Tree
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    // Insert value recursively maintaining the min-heap and Cartesian tree properties
    private CartesianTreeNode insertRecursive(CartesianTreeNode node, int value) {
        if (node == null) {
            return new CartesianTreeNode(value);
        }

        // Recursively insert the new value
        if (value < node.value) {
            CartesianTreeNode newNode = new CartesianTreeNode(value);
            newNode.left = node;  // New node becomes the root
            newNode.size = node.size + 1;
            return newNode;
        }

        // If value is larger, insert into left or right subtree
        if (node.left == null) {
            node.left = insertRecursive(node.left, value);
        } else {
            node.right = insertRecursive(node.right, value);
        }

        // Update size of the current node
        node.size = 1 + getSize(node.left) + getSize(node.right);

        // Maintain min heap property by rotating the tree if necessary
        if (node.left != null && node.left.value < node.value) {
            node = rotateRight(node);
        } else if (node.right != null && node.right.value < node.value) {
            node = rotateLeft(node);
        }

        return node;
    }

    // Helper function to get the size of the subtree rooted at a node
    private int getSize(CartesianTreeNode node) {
        return node == null ? 0 : node.size;
    }

    // Rotate the node to the right
    private CartesianTreeNode rotateRight(CartesianTreeNode node) {
        CartesianTreeNode newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateSize(node);
        updateSize(newRoot);
        return newRoot;
    }

    // Rotate the node to the left
    private CartesianTreeNode rotateLeft(CartesianTreeNode node) {
        CartesianTreeNode newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateSize(node);
        updateSize(newRoot);
        return newRoot;
    }

    // Update the size of the current node
    private void updateSize(CartesianTreeNode node) {
        if (node != null) {
            node.size = 1 + getSize(node.left) + getSize(node.right);
        }
    }

    // Pop the min value from the heap
    public int popMin() {
        if (root == null) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = root.value;
        root = removeMin(root);
        return min;
    }

    // Remove the root (min value) and balance the tree
    private CartesianTreeNode removeMin(CartesianTreeNode node) {
        if (node.left == null) {
            return node.right;  // No left child, right child becomes the root
        }
        if (node.right == null) {
            return node.left;   // No right child, left child becomes the root
        }

        // Recurse and maintain heap property by rotating left or right as needed
        if (node.left.value < node.right.value) {
            node = rotateRight(node);
            node.right = removeMin(node.right);
        } else {
            node = rotateLeft(node);
            node.left = removeMin(node.left);
        }

        return node;
    }

    // Peek at the root value (min value)
    public int peek() {
        if (root == null) {
            throw new IllegalStateException("Heap is empty");
        }
        return root.value;
    }

    // Get the size of the heap
    public int size() {
        return getSize(root);
    }

    // Delete a specific value from the heap
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    // Recursive function to find and delete a node with a specific value
    private CartesianTreeNode deleteRecursive(CartesianTreeNode node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // Node to be deleted found
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // If node has both children, replace it with the smaller of the two children
            if (node.left.value < node.right.value) {
                node = rotateRight(node);
                node.right = deleteRecursive(node.right, value);
            } else {
                node = rotateLeft(node);
                node.left = deleteRecursive(node.left, value);
            }
        }

        // Update size and return the modified node
        updateSize(node);
        return node;
    }

    // Print the tree for debugging
    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(CartesianTreeNode node, String indent, boolean isRight) {
        if (node != null) {
            System.out.println(indent + (isRight ? "R----" : "L----") + node.value);
            printTree(node.left, indent + (isRight ? "     " : "|    "), false);
            printTree(node.right, indent + (isRight ? "     " : "|    "), true);
        }
    }
}
