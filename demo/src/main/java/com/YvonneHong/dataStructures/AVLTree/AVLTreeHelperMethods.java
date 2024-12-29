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
    

    public static int getBalanceFactor(Node node) {
        if(node == null) {
            return 0; 
        }
        return getHeight(node.left) - getHeight(node.right); 
    }

    public static int getMaxValue(Node root) {
        Node current = root; 
        //traverse to the rightmost node, which contains the largest key
        while(current.right != null) {
            current = current.right; 
        }
        return current.key; //return the maximum value found
    }

    public static int getMinValue(Node root) {
        Node current = root; 
        //traverse to the leftmost node, which contains the smallest key
        while(current.left != null) {
            current = current.left; 
        }
        return current.key; //return the minimum value found 
    }

    //helper method to rebalance a node 
    protected static Node rebalance(Node root, int balance) {
        // Case 1: Single right rotation 
        if(balance > 1) {
            return singleRightRotation(root); 
        }
        //Case 2: single left rotation
        if(balance < -1) {
            return singleLeftRotation(root); 
        }
        //case 3: left-right double rotation 
        if((balance > 1) && (getBalanceFactor(root.left) < 0)){
            root.left = singleLeftRotation(root.left); //left rotate the left subtree
            return singleRightRotation(root); //right rotate the root 
        }

        //case 4: right-left double rotation 
        if((balance < -1) && (getBalanceFactor(root.right) > 0)){
            root.right = singleRightRotation(root.right); //right-rotate the right subtree
            return singleLeftRotation(root); //left-rotate the root 
        }
        return root; //return the node after balancing
    } 
    
}
