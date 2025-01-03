package com.YvonneHong.dataStructures.CartesianTree.MaxHeapCartesianTree;

import com.YvonneHong.dataStructures.CartesianTree.CartesianTreeNode;

public class MaxHeapCTOps {

    // Rotate the node to the right
    protected static CartesianTreeNode rotateRight(CartesianTreeNode node) {
        CartesianTreeNode newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateSize(node);
        updateSize(newRoot);
        return newRoot;
    }

    // Rotate the node to the left
    protected static CartesianTreeNode rotateLeft(CartesianTreeNode node) {
        CartesianTreeNode newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateSize(node);
        updateSize(newRoot);
        return newRoot;
    }

    // Update the size of the current node
    protected static void updateSize(CartesianTreeNode node) {
        if (node != null) {
            node.size = 1 + MaxHeapCTOps.getSize(node.left) + MaxHeapCTOps.getSize(node.right);
        }
    }

    // Insert value recursively maintaining the max-heap and Cartesian tree properties
    protected static CartesianTreeNode insertRecursive(CartesianTreeNode node, int value) {
        if (node == null) {
            return new CartesianTreeNode(value);
        }
        
        // Recursively insert the new value
        if (value > node.value) {
            CartesianTreeNode newNode = new CartesianTreeNode(value);
            newNode.left = node;  // New node becomes the root
            newNode.size = node.size + 1;
            return newNode;
        }

        // If value is smaller, insert into left or right subtree
        if (node.left == null) {
            node.left = insertRecursive(node.left, value);
        } else {
            node.right = insertRecursive(node.right, value);
        }

        // Update size of the current node
        node.size = 1 + getSize(node.left) + getSize(node.right);

        // Maintain max heap property by rotating the tree if necessary
        if (node.left != null && node.left.value > node.value) {
            node = rotateRight(node);
        } else if (node.right != null && node.right.value > node.value) {
            node = rotateLeft(node);
        }

        return node;
    }

    // Recursive function to find and delete a node with a specific value
    protected static CartesianTreeNode deleteRecursive(CartesianTreeNode node, int value) {
        if (node == null) {
            return null;
        }

        if (value > node.value) {
            node.left = deleteRecursive(node.left, value);
        } else if (value < node.value) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // Node to be deleted found
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // If node has both children, replace it with the larger of the two children
            if (node.left.value > node.right.value) {
                node = MaxHeapCTOps.rotateRight(node);
                node.right = deleteRecursive(node.right, value);
            } else {
                node = MaxHeapCTOps.rotateLeft(node);
                node.left = deleteRecursive(node.left, value);
            }
        }

        // Update size and return the modified node
        MaxHeapCTOps.updateSize(node);
        return node;
    }

    // Helper function to get the size of the subtree rooted at a node
    protected static int getSize(CartesianTreeNode node) {
        return node == null ? 0 : node.size;
    }

    // Remove the root (max value) and balance the tree
    protected static CartesianTreeNode removeMax(CartesianTreeNode node) {
        if (node.left == null) {
            return node.right;  // No left child, right child becomes the root
        }
        if (node.right == null) {
            return node.left;   // No right child, left child becomes the root
        }
        
        // Recurse and maintain heap property by rotating left or right as needed
        if (node.left.value > node.right.value) {
            node = rotateRight(node);
            node.right = removeMax(node.right);
        } else {
            node = rotateLeft(node);
            node.left = removeMax(node.left);
        }

        return node;
    }

    protected static void recursivelyPrintTree(CartesianTreeNode node, String indent, boolean isRight) {
        if (node != null) {
            System.out.println(indent + (isRight ? "R----" : "L----") + node.value);
            recursivelyPrintTree(node.left, indent + (isRight ? "     " : "|    "), false);
            recursivelyPrintTree(node.right, indent + (isRight ? "     " : "|    "), true);
        }
    }

}
