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
        root = MinHeapCTOps.insertRecursive(root, value);
    }

    // Pop the min value from the heap
    public int popMin() {
        if (root == null) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = root.value;
        root = MinHeapCTOps.removeMin(root);
        return min;
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
        return MinHeapCTOps.getSize(root);
    }

    // Delete a specific value from the heap
    public void delete(int value) {
        root = MinHeapCTOps.deleteRecursive(root, value);
    }

    // Print the tree for debugging
    public void printTree() {
        MinHeapCTOps.recursivelyPrintMinHeapTree(root, "", true);
    }

    //search for a node. Check if a specific node/value exists??

    //search for a value (boolean??? or return an int)

}
