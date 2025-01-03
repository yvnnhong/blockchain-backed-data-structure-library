package com.YvonneHong.dataStructures.CartesianTree;

public class CartesianTreeNode {
    public int value; //value of the node 
    public CartesianTreeNode left; 
    public CartesianTreeNode right; 
    public int size; //size of the subtree rooted at this node (size = total # of nodes)

    //constructor to initialize the node 
    public CartesianTreeNode(int value) {
        this.value = value; 
        this.left = null;
        this.right = null;
        this.size = 1; //initially, the size of the subtree is 1 (the root node itself)
    }
    
}
