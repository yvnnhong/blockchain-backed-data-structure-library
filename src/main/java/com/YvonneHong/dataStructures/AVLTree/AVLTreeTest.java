package com.YvonneHong.dataStructures.AVLTree;

public class AVLTreeTest {

    public static void main(String[] args) {
        AVLTreeTest test = new AVLTreeTest();
        test.runTests();
    }

    public void runTests() {
        System.out.println("Running AVL Tree Tests...");

        // Test 1: Insert elements into the AVL Tree
        Node root = null;
        AVLTree tree = new AVLTree();

        root = tree.insert(root, 30);
        root = tree.insert(root, 20);
        root = tree.insert(root, 40);
        root = tree.insert(root, 10);
        root = tree.insert(root, 25);
        root = tree.insert(root, 35);
        root = tree.insert(root, 50);

        System.out.println("In-order traversal after insertions:");
        tree.inorder(root); // Expected: 10 20 25 30 35 40 50

        // Test 2: Lookup operation (search for nodes)
        System.out.println("\nLooking up nodes:");
        System.out.println("Lookup 25: " + (tree.lookup(root, 25) != null ? "Found" : "Not Found")); // Expected: Found
        System.out.println("Lookup 60: " + (tree.lookup(root, 60) != null ? "Found" : "Not Found")); // Expected: Not Found

        // Test 3: Delete an element
        System.out.println("\nDeleting 40...");
        root = tree.delete(root, 40);
        tree.inorder(root); // Expected: 10 20 25 30 35 50

        // Test 4: Delete minimum value
        System.out.println("\nDeleting minimum value...");
        root = tree.deleteMin(root);
        tree.inorder(root); // Expected: 20 25 30 35 50

        // Test 5: Delete maximum value
        System.out.println("\nDeleting maximum value...");
        root = tree.deleteMax(root);
        tree.inorder(root); // Expected: 20 25 30 35

        // Test 6: Clone the tree
        System.out.println("\nCloning the tree...");
        Node clonedRoot = AVLTree.cloneAVLTree(root);
        System.out.print("In-order traversal of the cloned tree: ");
        tree.inorder(clonedRoot); // Expected: 20 25 30 35

        // Test 7: Find successor
        System.out.println("\nFinding successor of 25...");
        Node successor = AVLTree.findSuccessor(root, 25);
        System.out.println("Successor of 25: " + (successor != null ? successor.key : "None")); // Expected: 30

        // Test 8: Find predecessor
        System.out.println("\nFinding predecessor of 25...");
        Node predecessor = AVLTree.findPredecessor(root, 25);
        System.out.println("Predecessor of 25: " + (predecessor != null ? predecessor.key : "None")); // Expected: 20

        // Test 9: Edge case for empty tree lookup
        System.out.println("\nLookup on an empty tree:");
        Node emptyRoot = null;
        System.out.println("Lookup 10 in empty tree: " + (tree.lookup(emptyRoot, 10) != null ? "Found" : "Not Found")); // Expected: Not Found

        // Test 10: Edge case for delete in empty tree
        System.out.println("\nDeleting 10 in an empty tree...");
        emptyRoot = tree.delete(emptyRoot, 10); // Tree should stay empty
        System.out.println("Tree after deletion in empty tree: ");
        tree.inorder(emptyRoot); // Expected: (no output, tree is empty)
    }
}
