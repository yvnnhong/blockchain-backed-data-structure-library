package com.YvonneHong.dataStructures.AVLTree;

public class AVLTreeNode {
    int key; 
    AVLTreeNode left; 
    AVLTreeNode right; 
    int height; 

    //constructor for the Node class 
    public AVLTreeNode(int key){
        this.key = key; 
        this.left = null; 
        this.right = null; 
        this.height = 1; //new nodes are initially at a height of 1
    }
}
