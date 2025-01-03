package com.YvonneHong.dataStructures.CartesianTree.MinHeapCartesianTree;

import com.YvonneHong.dataStructures.CartesianTree.CartesianTreeNode;

public class MinHeapCT {
    private CartesianTreeNode root;  // root of the MinHeap Cartesian Tree

    // Constructor
    public MinHeapCT() {
        this.root = null;
    }

    // Method to insert a value into the MinHeap Cartesian Tree
    public void insert(int value) {
        CartesianTreeNode newNode = new CartesianTreeNode(value);
        root = insertMinHeap(root, newNode);
    }

    // Recursive method to insert the new node in the Min Heap Cartesian Tree
    private CartesianTreeNode insertMinHeap(CartesianTreeNode root, CartesianTreeNode newNode) {
        if (root == null) {
            return newNode;  // If the tree is empty, the new node becomes the root
        }

        // If the new node's value is less than the root's value, we need to swap
        if (newNode.value < root.value) {
            // Rebalance the tree by rotating the new node to the root position
            newNode.left = root.left;
            newNode.right = root.right;

            // Update the root's left and right children
            root.left = null;  // Break the link to the previous left child
            root.right = null; // Break the link to the previous right child

            // Update sizes of subtrees
            newNode.size = getSize(newNode.left) + getSize(newNode.right) + 1;
            root.size = getSize(root.left) + getSize(root.right) + 1;

            return newNode;
        }

        // If the new node's value is greater than the root's value, insert it as a child
        if (root.left == null) {
            root.left = insertMinHeap(root.left, newNode);
        } else {
            root.right = insertMinHeap(root.right, newNode);
        }

        return root;
    }

    // Helper function to get the size of a node
    private int getSize(CartesianTreeNode node) {
        return (node == null) ? 0 : node.size;
    }

    // Method to print the tree in-order for verification (optional)
    public void printInOrder(CartesianTreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.value + " ");
            printInOrder(root.right);
        }
    }

    public CartesianTreeNode getRoot() {
        return root;
    }
}
