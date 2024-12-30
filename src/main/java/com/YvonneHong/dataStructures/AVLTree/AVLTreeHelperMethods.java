package com.YvonneHong.dataStructures.AVLTree;

public class AVLTreeHelperMethods {


    public static Node singleRightRotation(Node y) {
        Node x = y.left; //node x is the left child of y 
        Node T2 = x.right; //T2 is the right child of x

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

    public static int getMaxValue(Node root) { //returns an INT (the actual key? or value? stored in the node)
        Node current = root; 
        //traverse to the rightmost node, which contains the largest key
        while(current.right != null) {
            current = current.right; 
        }
        return current.key; //return the maximum value found
    }

    /*differentiate between key and values. accommodate for 
    cases in which the keys are the same as the values, and cases where they're different*/

    public static int getMinValue(Node root) { //returns an INT (the actual key? or VALUE? stored in the node)
        Node current = root; 
        //traverse to the leftmost node, which contains the smallest key
        while(current.left != null) {
            current = current.left; 
        }
        return current.key; //return the minimum value found 
    }

    //Helper function to find the maximum value node in a subtree (returns a NODE)
    protected static Node findMax(Node node) {
        while(node.right != null) {
            node = node.right; 
        }
        return node;
    }

    //Helper function to find the minimum value node in a subtree (returns a NODE)
    protected static Node findMin(Node node) {
        while(node.left != null) {
            node = node.left; 
        }
        return node;
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

    //lookup helper method 
    public static Node lookup(Node root, int key) {
        //traverse the tree to find the node with the specified key 
        if(root == null || root.key == key){
            return root; 
        }
        //if the key is smaller than the root's key, search in the left subtree
        if(key < root.key) {
            return lookup(root.left, key); 
        }
        //if the key is larger than the root's key, search in the right subtree
        return lookup(root.right, key); 
    }
    
}
