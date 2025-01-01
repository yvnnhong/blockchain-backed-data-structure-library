package com.YvonneHong.dataStructures.BinarySearchTree;

public class BSTHelperMethods {

    //base case: if the tree is empty or we reach a leaf node, insert the value
    protected static BSTNode insertRecursive(BSTNode node, int value){
        if(node == null) {
            return new BSTNode(value);
        }
        //recursively insert into the left or right subtree
        if(value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if(value > node.value) {
            node.right = insertRecursive(node.right, value); 
        }
        return node; 
    }

    protected static boolean searchRecursive(BSTNode node, int value) {
        //base case: if the node is null, the value is not found
        if(node == null) {
            return false;
        }

        //return true if the value is found
        if(value == node.value) {
            return true; 
        }

        //recurse left or right based on the value
        return value < node.value ? searchRecursive(node.left, value) : searchRecursive(node.right, value); 
    }

    protected static BSTNode deleteRecursive(BSTNode node, int value) {
        //base case: if the node is null, return null (value not found)
        if(node == null) {
            return null; 
        }

        //recursively search for the node to delete
        if(value < node.value) {
            node.left = deleteRecursive(node.left, value); 
        } else if(value > node.value) {
            node.right = deleteRecursive(node.right, value);
        } else { //we have found the node that we want to delete

            //case 1: no child (leaf node)
            if((node.left == null) && (node.right == null)){
                return null; 
            }

            //case 2: one child 
            if(node.left == null) {
                return node.right;
            } else if(node.right == null) {
                return node.left; 
            }

            //case 3: two children
            //get the smallest value in the right subtree (in-order successor)
            node.value = findMin(node.right).value; 
            //delete the in-order successor
            node.right = deleteRecursive(node.right, node.value); 
        }
        return node; 
    }

    //find the minimum value node in the tree 
    private static BSTNode findMin(BSTNode node) {
        while(node.left != null) {
            node = node.left; 
        }
        return node; 
    }

    //recursively calculate size 
    protected static int sizeRecursive(BSTNode node) {
        if(node == null) {
            return 0; 
        }
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right); 
    }

    //recursively compute height
    protected static int heightRecursive(BSTNode node) {
        if(node == null) {
            return -1; 
        }
        int leftHeight = heightRecursive(node.left);
        int rightHeight = heightRecursive(node.right);
        return Math.max(leftHeight, rightHeight) + 1; 
    }

    protected static void inorderTraversalRecursive(BSTNode node){
        if(node != null) {
            inorderTraversalRecursive(node.left);
            System.out.print(node.value + " "); 
            inorderTraversalRecursive(node.right);
        }
    }

    protected static void preorderTraversalRecursive(BSTNode node){
        if(node != null) {
            System.out.print(node.value + " "); 
            preorderTraversalRecursive(node.left);
            preorderTraversalRecursive(node.right);
        }
    }

    protected static void postOrderTravelRecursive(BSTNode node) {
        if(node != null) {
            postOrderTravelRecursive(node.left);
            postOrderTravelRecursive(node.right);
            System.out.print(node.value + " "); 
        }
    }
    
}
