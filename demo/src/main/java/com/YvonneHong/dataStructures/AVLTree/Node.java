package com.YvonneHong.dataStructures.AVLTree;

public class Node {
    int key; 
    Node left; 
    Node right; 
    int height; 

    public Node(int key){
        this.key = key; 
        this.left = null; 
        this.right = null; 
        this.height = 1; //new nodes are initially at a height of 1
    }
}
