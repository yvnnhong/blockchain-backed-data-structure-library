package com.YvonneHong.dataStructures.BinarySearchTree;

public class BinarySearchTree {

    private Node root; 

    public BinarySearchTree() {
        this.root = null; 
    }

    //method to insert a node into the tree 
    public void insert(int value) {
        root = BSTHelperMethods.insertRecursive(root, value); 
    }

    //method to seach for a value in the tre 
    public boolean search(int value) {
        return BSTHelperMethods.searchRecursive(root, value); 
    }

    //method to delete a node from the tree 
    public void delete(int value) {
        root = BSTHelperMethods.deleteRecursive(root, value);
    }

    //get the size of the tree -- tells us how many total nodes are in the tree 
    public int size() {
        return BSTHelperMethods.sizeRecursive(root); 
    }

    //get the height of the tree
    public int height() {
        return BSTHelperMethods.heightRecursive(root);
    }

    //method for in-order traversal (left, right, root)
    public void inorderTraversal() {
        BSTHelperMethods.inorderTraversalRecursive(root);
    }

    //method for pre-order traversal (root, left, right)
    public void preorderTraversal() {
        BSTHelperMethods.preorderTraversalRecursive(root);
    }

    //post-order traversal (left, right, root)
    public void postorderTraversal() {
        BSTHelperMethods.postOrderTravelRecursive(root);
    }

    
}
