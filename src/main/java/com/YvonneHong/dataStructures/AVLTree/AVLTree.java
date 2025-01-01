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
    public AVLTreeNode insert(AVLTreeNode root, int key) {
         //first, perform standard binary search tree (BST) insertion
        if(root == null) {
            return new AVLTreeNode(key); //create a new node if the tree is empty, or find position for new node 
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
    public AVLTreeNode delete(AVLTreeNode root, int key) {
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
            root.key = AVLTreeHelperMethods.getMinValue(root.right); 
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
    public AVLTreeNode lookup(AVLTreeNode root, int key) {
        //traverse the tree to find the key 
        if((root == null) || (root.key == key)) {
            return root; //means we found the key or reached a leaf 
        }
        if(key < root.key){
            return lookup(root.left, key); //search in left subtree
        } else {
            return lookup(root.right, key); //search in right subtree
        }
    }


    // 4) inorder traversal (print out all the nodes in ascending order)
    public void inorder(AVLTreeNode root) {
        if(root != null){
            inorder(root.left); //traverse left subtree
            System.out.print(root.key + " "); //visit current node
            inorder(root.right); //traverse right subtree
        }
    }


    // 5) preorder traversal (print out all the nodes in root-left-right order)
    public void preorder(AVLTreeNode root){
        if(root != null) {
            System.out.print(root.key + " "); //visit current node
            preorder(root.left); //traverse left subtree
            preorder(root.right); //traverse right subtree 
        }
    }

    // 6) delete min value 
    public AVLTreeNode deleteMin(AVLTreeNode root){
        if(root == null) {
            return root; //if the tree is empty, return null
        }
        //if there is no left child, root is the minimum node 
        if(root.left == null) {
            return root.right; //replace root with its right child 
        }

        //Recurse left to find the minimum node
        root.left = deleteMin(root.left); 

        //Update height and balance factor of the node 
        root.height = Math.max(AVLTreeHelperMethods.getHeight(root.left), AVLTreeHelperMethods.getHeight(root.right)) + 1;
        int balance = AVLTreeHelperMethods.getBalanceFactor(root); 

        //rebalance the tree if necessary 
        return AVLTreeHelperMethods.rebalance(root, balance); 
    }


    // 7) delete max value
    public AVLTreeNode deleteMax(AVLTreeNode root) {
        if(root==null){
            return root; //if the tree is empty, return null
        }
        //if there is no right child, root is the maximum node 
        if(root.right == null) {
            return root.left; //replace root with its left child
        }
        //recurse right to find the maximum node 
        root.right = deleteMax(root.right); 

        //update height and balance factor of the node 
        root.height = Math.max(AVLTreeHelperMethods.getHeight(root.left), AVLTreeHelperMethods.getHeight(root.right)) + 1;
        int balance = AVLTreeHelperMethods.getBalanceFactor(root); 

        //rebalance the tree if necessary
        return AVLTreeHelperMethods.rebalance(root, balance); 
    }


    // 8) clone the tree 
    public static AVLTreeNode cloneAVLTree(AVLTreeNode root) {
        if(root == null) {
            return null; //if the tree is empty, return null
        }
        //create a node with the same key, and recursively clone left and right subtrees 
        AVLTreeNode node = new AVLTreeNode(root.key); 
        node.left = cloneAVLTree(root.left); 
        node.right = cloneAVLTree(root.right); 

        //update height for the cloned node
        node.height = Math.max(AVLTreeHelperMethods.getHeight(node.left), AVLTreeHelperMethods.getHeight(node.right)) + 1; 
        return node; //return the root node of the new (cloned) tree 
    }


    // 9) find successor of a node: The successor of a node is the node with the smallest key greater than the given node's key.
    public static AVLTreeNode findSuccessor(AVLTreeNode root, int key){
        //Find the node that matches the key
        AVLTreeNode node = AVLTreeHelperMethods.lookup(root, key); 
        if(node == null) {
            return null; //key not found in the tree
        }
        //case 1: if the node has a right child, the successor is the leftmost node in the right subtree
        if(node.right != null) {
            return AVLTreeHelperMethods.findMin(node.right); //find the minimum in the right subtree
        }
        //case 2: if the node does not have a right child, go up to find the first ancestor
        //that is the left child of its parent (that parent will be the successor)
        AVLTreeNode parent = root; 
        AVLTreeNode successor = null; 

        while(parent != null){
            if(key < parent.key) {
                successor = parent; //this is a potential successor 
                parent = parent.left; 
            } else if (key > parent.key){
                parent = parent.right; 
            } else {
                break; //?? maybe we should not use a break 
            }
        }
        return successor; 
    }


    // 10) find predecessor: The predecessor of a node is the node with the largest key smaller than the given node's key.
    public static AVLTreeNode findPredecessor(AVLTreeNode root, int key) {
        //find the node that matches the key
        AVLTreeNode node = AVLTreeHelperMethods.lookup(root, key); 
        if(node == null) {
            return null; //key not found in the tree
        }
        //case 1: if the node has a left child, the predecessor is the rightmost node in the left subtree
        if(node.left != null){
            return AVLTreeHelperMethods.findMax(node.left); //find the maximum in the left subtree
        }
        //case 2: if the node does not have a left child, go up to find the first ancestor
        //that is the right child of its parent (that parent will be the predecessor)
        AVLTreeNode parent = root; 
        AVLTreeNode predecessor = null; 

        while(parent != null) {
            if(key < parent.key) {
                parent = parent.left; 
            } else if(key > parent.key) {
                predecessor = parent; //this is a potential predecessor
                parent = parent.right; 
            } else {
                break; 
            }
        }
        return predecessor; 

    }
    
}
