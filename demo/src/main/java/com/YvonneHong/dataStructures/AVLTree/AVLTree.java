package com.YvonneHong.dataStructures.AVLTree;

public class AVLTree {

    //The AVL tree is a self-balancing binary search tree (BST), which means that 
    //the nodes must follow the binary search tree property, where for any given node:

    //1) All nodes in the left subtree of a node are less than the node.
    //2) All nodes in the right subtree of a node are greater than the node.

    //methods/operations to add: 
    // 1) insertion
    public Node insert(Node root, int key) {
        if(root == null) {
            return new Node(key); 
        }
        if(key < root.key) {
            root.left = insert(root.left, key); 
        } else if(key > root.key) {
            root.right = insert(root.right, key); 
        } else {
            return root; //duplicate keys are not allowed 
        }

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1; //error 
        

    }


    // 2) deletion 


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
