package com.YvonneHong.dataStructures.AVLTree;

public class AVLTree {

    //The AVL tree is a self-balancing binary search tree (BST), which means that 
    //the nodes must follow the binary search tree property, where for any given node:

    //1) All nodes in the left subtree of a node are less than the node.
    //2) All nodes in the right subtree of a node are greater than the node.

    //note: we're only comparing keys of nodes, the values literally have zero meaning and are just 
    //the info that is stored in a node. We use the keys to access their corresponding values in nodes 

    //methods/operations to add: 
    // 1) insertion
    public Node insert(Node root, int key) {
         //first, perform standard binary search tree (BST) insertion
        if(root == null) {
            return new Node(key); //create a new node if the tree is empty, or find position for new node 
        }
        if(key < root.key) {
            root.left = insert(root.left, key); 
        } else if(key > root.key) {
            root.right = insert(root.right, key); 
        } else {
            return root; //duplicate keys are not allowed 
        }

        root.height = Math.max(AVLTreeHelperMethods.getHeight(root.left), AVLTreeHelperMethods.getHeight(root.right)) + 1; //error 
        
        int balance = AVLTreeHelperMethods.getBalanceFactor(root); 
    
        //case 1: single right rotation
        if((balance > 1) && (key < root.left.key)) {
            return AVLTreeHelperMethods.singleRightRotation(root); 
        }

        //case 2: single left rotation 
        if((balance < -1) && (key > root.right.key)) {
            return AVLTreeHelperMethods.singleLeftRotation(root); 
        }

        //case 3: left-right double rotation 
        if((balance > 1) && (key > root.left.key)) {
            root.left = AVLTreeHelperMethods.singleLeftRotation(root.left); 
            return AVLTreeHelperMethods.singleRightRotation(root); 
        }

        //case 4: right-left double rotation 
        if((balance < -1) && (key < root.right.key)) {
            root.right = AVLTreeHelperMethods.singleRightRotation(root.right); 
            return AVLTreeHelperMethods.singleLeftRotation(root); 
        }
        return root; 
    }


    // 2) deletion 
    public Node delete(Node root, int key) {
        //first, perform standard binary search tree (BST) deletion 
        if(root == null) {
             return root; //base case: the tree is empty 
        }
        //find the node to delete 
        if(key < root.key) {
            root.left = delete(root.left, key); 
        } else if (key > root.key) {
            root.right = delete(root.right, key); 
        } else { //when the key matches the root 
            if(root.left == null) {
                return root.right; //if node has no left child, replace with right child
            } else if(root.right == null) {
                return root.left; //if node has no right child, replace with left child 
            }

            //node has two children, find the inorder successor (smallest in right subtree)
            root.key = AVLTreeHelperMethods.minValue(root.right); 
            root.right = delete(root.right, root.key);
        }

        //update the height of the current node 
        root.height = Math.max(AVLTreeHelperMethods.getHeight(root.left), AVLTreeHelperMethods.getHeight(root.right)) + 1; 

        //get the balance factor 
        int balance = AVLTreeHelperMethods.getBalanceFactor(root); 

        //next, perform rotations if the node became unbalanced 

        //case 1: single right rotation
        if((balance > 1) && (AVLTreeHelperMethods.getBalanceFactor(root.left) >= 0)) {
            return AVLTreeHelperMethods.singleRightRotation(root); 
        }

        //case 2: single left rotation 
        if((balance < -1) && (AVLTreeHelperMethods.getBalanceFactor(root.right) <= 0)) {
            return AVLTreeHelperMethods.singleLeftRotation(root); 
        }

        //case 3: left-right double rotation 
        if((balance > 1) && (AVLTreeHelperMethods.getBalanceFactor(root.left) < 0)) {
            root.left = AVLTreeHelperMethods.singleLeftRotation(root.left); 
            return AVLTreeHelperMethods.singleRightRotation(root); 
        }

        //case 4: right-left double rotation 
        if((balance < -1) && (AVLTreeHelperMethods.getBalanceFactor(root.right) > 0)) {
            root.right = AVLTreeHelperMethods.singleRightRotation(root.right); 
            return AVLTreeHelperMethods.singleLeftRotation(root); 
        }
        return root; 
    }



    // 3) lookup (searching)


    // 4) find max value 


    // 5) find min value 


    // 6) inorder traversal (print out all the nodes)


    // 7) preorder traversal (print out all the nodes)


    // 8) return height of tree / height calculation


    // 9) balance factor calculation


    // 10) delete min value 


    // 11) delete max value


    // 12) clone the tree 


    // 13) find successor of a node: The successor of a node is the node with the smallest key greater than the given node's key.


    // 14) find predecessor: The predecessor of a node is the node with the largest key smaller than the given node's key.

    
}
