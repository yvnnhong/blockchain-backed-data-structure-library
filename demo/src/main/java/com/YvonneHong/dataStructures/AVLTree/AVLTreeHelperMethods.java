package com.YvonneHong.dataStructures.AVLTree;

public class AVLTreeHelperMethods {


    public static Node singleRightRotation(Node y) {
        Node x = y.left; 
        Node T2 = x.right; 

        //perform the rotation 
        x.right = y; 
        y.left = T2; 

        //update heights
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1; 
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1; 

        return x; //return the new root x
    }

    public static Node singleLeftRotation(Node x) {
        Node y = x.right; 
        Node T2 = y.left;

        //perform rotation 
        y.left = x; 
        x.right = T2; 

        //update heights
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1; 
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1; 

        return y; 

    }

    public static Node leftRightDoubleRotation(Node node) {
        node.left = singleLeftRotation(node.left); //left rotate on left subtree
        return singleRightRotation(node); //right rotate on root 
    }

    public static Node rightLeftDoubleRotation(Node node) {
        node.right = singleRightRotation(node.right); //right rotate on right subtree
        return singleLeftRotation(node); //left rotate on root 

    }

    public static int getHeight(Node node) {
        if(node == null) {
            return 0; 
        }
        return node.height; 
    }
    
}
